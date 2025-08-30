package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.DoctorRegisterRequest;
import com.avsofthealthcare.dto.DoctorResponseDto;
import com.avsofthealthcare.service.DoctorService;
import com.avsofthealthcare.util.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/auth/doctor")
@RequiredArgsConstructor
public class DoctorController {

	private static final Logger logger = LoggerFactory.getLogger(DoctorController.class);

	private final DoctorService doctorService;
	private final FileStorageService fileStorageService;

	@Value("${app.file.upload-dir}")
	private String uploadDir;

	private final Path fileStorageLocation = Paths.get("/doctors/photos").toAbsolutePath().normalize();

	// REGISTER
	@PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<String> register(@Valid @ModelAttribute DoctorRegisterRequest request) throws IOException {
		doctorService.register(request, request.getPhoto());
		return ResponseEntity.ok("Doctor registered successfully");
	}

	// READ (Get doctor by ID)
	@GetMapping("/{id}")
	public ResponseEntity<DoctorResponseDto> getById(@PathVariable Long id) {
		return ResponseEntity.ok(doctorService.getById(id));
	}

	// READ (Get all active doctors)
	@GetMapping("/all")
	public ResponseEntity<List<DoctorResponseDto>> getAll() {
		return ResponseEntity.ok(doctorService.getAllActive());
	}

	// UPDATE
	@PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<DoctorResponseDto> update(
			@PathVariable Long id,
			@Valid@ModelAttribute DoctorRegisterRequest request
	) throws IOException {
		return ResponseEntity.ok(doctorService.update(id, request, request.getPhoto()));
	}

	// DELETE (Soft delete)
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		doctorService.softDelete(id);
		return ResponseEntity.ok("Doctor deleted successfully");
	}

	// DOWNLOAD PHOTO
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
