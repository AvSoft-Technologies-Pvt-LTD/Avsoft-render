package com.avsofthealthcare.avsofthealthcare.service.impl;

import com.avsofthealthcare.avsofthealthcare.dto.DoctorRegisterRequest;
import com.avsofthealthcare.avsofthealthcare.entity.DoctorDetails;
import com.avsofthealthcare.avsofthealthcare.entity.User;
import com.avsofthealthcare.avsofthealthcare.mapper.DoctorMapper;
import com.avsofthealthcare.avsofthealthcare.mapper.UserMapper;
import com.avsofthealthcare.avsofthealthcare.repository.DoctorDetailsRepository;
import com.avsofthealthcare.avsofthealthcare.repository.UserRepository;
import com.avsofthealthcare.avsofthealthcare.service.DoctorService;
import com.avsofthealthcare.avsofthealthcare.util.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorDetailsRepository doctorRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    @Transactional
    public String register(DoctorRegisterRequest req) throws IOException {
        // First, validate all required fields are present and not empty
        String validationError = validateRequiredFields(req);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        // Validate field formats
        validationError = validateFieldFormats(req);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        // Check if passwords match
        if (!req.getPassword().equals(req.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        // Check for existing registrations
        validationError = checkExistingRegistrations(req);
        if (validationError != null) {
            throw new IllegalArgumentException(validationError);
        }

        // Save user first
        User user = userMapper.toUserEntity("DOCTOR", req.getEmail(), req.getPhone(), req.getPassword(), req.getConfirmPassword());
        user = userRepository.save(user);

        // Handle file uploads
        String photoPath = null;
        String documentsPaths = null;
        
        try {
            // Handle photo upload if present
            photoPath = handlePhotoUpload(req);

            // Handle documents upload if present
            documentsPaths = handleDocumentsUpload(req);

            // Save doctor details
            DoctorDetails details = doctorMapper.toEntity(req, user.getId(), photoPath, documentsPaths);
            doctorRepository.save(details);

            return "Doctor registered successfully";

        } catch (IOException e) {
            throw new IOException("Failed to process file upload: " + e.getMessage());
        } catch (Exception e) {
            throw new IllegalArgumentException("Registration failed: " + e.getMessage());
        }
    }

    private String validateRequiredFields(DoctorRegisterRequest req) {
        if (req == null) {
            return "Registration request is null";
        }

        // Required fields only
        if (isNullOrEmpty(req.getFirstName())) return "First name is required";
        if (isNullOrEmpty(req.getLastName())) return "Last name is required";
        if (isNullOrEmpty(req.getEmail())) return "Email is required";
        if (isNullOrEmpty(req.getPhone())) return "Phone number is required";
        if (isNullOrEmpty(req.getPassword())) return "Password is required";
        if (isNullOrEmpty(req.getConfirmPassword())) return "Password confirmation is required";
        if (isNullOrEmpty(req.getGender())) return "Gender is required";
        if (isNullOrEmpty(req.getRegistrationNumber())) return "Registration number is required";
        if (isNullOrEmpty(req.getSpecialization())) return "Specialization is required";
        if (isNullOrEmpty(req.getPracticeType())) return "Practice type is required";

        // Validate DOB
        if (req.getDob() == null) return "Date of birth is required";

        // Validate boolean field (if using primitive boolean, no null check needed)
        if (!req.isAgreeDeclaration()) return "You must agree to the declaration";

        // Optional fields (no validation required):
        // - middleName
        // - aadhaar
        // - photo
        // - documents
        // - qualification
        // - location

        return null; // No validation errors
    }

    private String validateFieldFormats(DoctorRegisterRequest req) {
        // Validate email format
        if (!isValidEmail(req.getEmail())) {
            return "Invalid email format";
        }

        // Validate phone number format
        if (!isValidPhoneNumber(req.getPhone())) {
            return "Invalid phone number format";
        }

        // Validate password strength
        if (!isValidPassword(req.getPassword())) {
            return "Password must be at least 8 characters long and contain at least one digit, one lowercase and one uppercase letter";
        }

        // Validate Aadhaar format if provided
        if (req.getAadhaar() != null && !req.getAadhaar().isEmpty() && !isValidAadhaar(req.getAadhaar())) {
            return "Invalid Aadhaar format. It should be 12 digits";
        }

        return null;
    }

    private String checkExistingRegistrations(DoctorRegisterRequest req) {
        // Check if email already registered
        if (userRepository.existsByEmail(req.getEmail())) {
            return "Email already registered";
        }

        // Check if phone already registered
        if (userRepository.existsByPhone(req.getPhone())) {
            return "Phone already registered";
        }

        // Check for duplicate registration number
        Optional<DoctorDetails> existingDoctor = doctorRepository.findByRegistrationNumber(req.getRegistrationNumber());
        if (existingDoctor.isPresent()) {
            return "Medical Registration number already registered";
        }

        return null;
    }

    private boolean isValidFileType(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            return false;
        }
        String extension = originalFilename.toLowerCase();
        return extension.endsWith(".jpg") || extension.endsWith(".jpeg") || extension.endsWith(".png");
    }

    private boolean isValidDocumentType(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null || originalFilename.isEmpty()) {
            return false;
        }
        String extension = originalFilename.toLowerCase();
        return extension.endsWith(".pdf") || extension.endsWith(".doc") || extension.endsWith(".docx");
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email.trim()).matches();
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (isNullOrEmpty(phoneNumber)) return false;
        String phoneRegex = "^[+]?[0-9]{10,13}$";
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber.trim()).matches();
    }

    private boolean isValidPassword(String password) {
        if (isNullOrEmpty(password)) return false;
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        Pattern pattern = Pattern.compile(passwordRegex);
        return pattern.matcher(password).matches();
    }

    private boolean isValidAadhaar(String aadhaar) {
        if (isNullOrEmpty(aadhaar)) return true; // Optional field
        return aadhaar.matches("^[0-9]{12}$");
    }

    private String handlePhotoUpload(DoctorRegisterRequest req) throws IOException {
        try {
            // Photo is optional
            if (req.getPhoto() != null && !req.getPhoto().isEmpty()) {
                System.out.println("Processing photo upload: " + req.getPhoto().getOriginalFilename());
                return fileStorageService.storeFile(req.getPhoto(), "doctors/photos");
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error uploading photo: " + e.getMessage());
            e.printStackTrace();
            throw new IOException("Failed to upload photo: " + e.getMessage());
        }
    }

    private String handleDocumentsUpload(DoctorRegisterRequest req) throws IOException {
        try {
            // Documents are optional
            if (req.getDocuments() != null && !req.getDocuments().isEmpty()) {
                System.out.println("Processing " + req.getDocuments().size() + " documents");
                List<String> paths = new ArrayList<>();
                for (MultipartFile doc : req.getDocuments()) {
                    if (doc != null && !doc.isEmpty()) {
                        System.out.println("Processing document: " + doc.getOriginalFilename());
                        String path = fileStorageService.storeFile(doc, "doctors/documents");
                        paths.add(path);
                    }
                }
                return String.join(",", paths);
            }
            return null;
        } catch (IOException e) {
            System.err.println("Error uploading documents: " + e.getMessage());
            e.printStackTrace();
            throw new IOException("Failed to upload documents: " + e.getMessage());
        }
    }
}