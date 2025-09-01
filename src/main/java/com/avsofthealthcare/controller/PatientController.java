package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.PatientRegisterRequest;
import com.avsofthealthcare.dto.PatientResponseDto;
import com.avsofthealthcare.service.PatientService;
import com.avsofthealthcare.util.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerMapping;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/auth/patient")
@RequiredArgsConstructor
public class PatientController {

	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

	private final PatientService patientService;
	private final FileStorageService fileStorageService;

	@Value("${app.file.upload-dir}")
	private String uploadDir;

	private final Path fileStorageLocation = Paths.get("/patients/photos").toAbsolutePath().normalize();


	@PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> register(@Valid @ModelAttribute PatientRegisterRequest request) throws IOException {
        patientService.register(request, request.getPhoto());
        return ResponseEntity.ok("Patient registered successfully");
    }

    // READ (Get patient by ID)
    @PreAuthorize("hasRole('PATIENT') or hasRole('ADMIN')")
	@GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getById(id));
    }

    // READ (Get all active patients)
    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
    public ResponseEntity<List<PatientResponseDto>> getAll() {
        return ResponseEntity.ok(patientService.getAllActive());
    }

    //update
    @PreAuthorize("hasRole('PATIENT') or hasRole('ADMIN')")
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PatientResponseDto> update(
		    @Valid
			@PathVariable Long id,
            @ModelAttribute PatientRegisterRequest request
    ) throws IOException {
        return ResponseEntity.ok(patientService.update(id, request, request.getPhoto()));
    }


    // DELETE (Soft delete)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        patientService.softDelete(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }

	@GetMapping("/photo")
	public ResponseEntity<Resource> downloadPhoto(@RequestParam String path) throws MalformedURLException {
		Path basePath = Paths.get(uploadDir).toAbsolutePath().normalize();
		Path file = basePath.resolve(path).normalize();

		if (!file.startsWith(basePath) || !Files.exists(file) || !Files.isReadable(file)) {
			return ResponseEntity.notFound().build();
		}

		Resource resource = new UrlResource(file.toUri());

		String contentType;
		try {
			contentType = Files.probeContentType(file);
		} catch (IOException e) {
			contentType = null;
		}

		if (contentType == null) {
			contentType = path.toLowerCase().endsWith(".avif") ? "image/avif" : "application/octet-stream";
		}

		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + file.getFileName() + "\"")
				.body(resource);
	}

}
