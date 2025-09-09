package com.avsofthealthcare.controller.master;

import com.avsofthealthcare.dto.master.AvailableTestRequestDto;
import com.avsofthealthcare.dto.master.AvailableTestResponseDto;
import com.avsofthealthcare.entity.master.AvailableTests;
import com.avsofthealthcare.mapper.master.AvailableTestMapper;
import com.avsofthealthcare.repository.master.AvailableTestsRepository;
import com.avsofthealthcare.service.master.AvailableTestsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/master/available-tests")
@Tag(name = "Available Tests", description = "CRUD for Available Tests")
public class AvailableTestsController {

    @Autowired
    private AvailableTestsService availableTestsService;

    @Autowired
    private AvailableTestsRepository availableTestsRepository;


	@PreAuthorize("@permissionChecker.hasPermission('Available-Tests','CREATE')")
	@PostMapping
    public ResponseEntity<AvailableTestResponseDto> createAvailableTest( @Validated @RequestBody AvailableTestRequestDto dto) {
        AvailableTests saved = availableTestsRepository.save(AvailableTestMapper.toEntity(dto));
        return ResponseEntity.ok(AvailableTestMapper.toDto(saved));
    }


	@PreAuthorize("@permissionChecker.hasPermission('Available-Tests','VIEW')")
	@GetMapping
    public List<AvailableTestResponseDto> getAllAvailableTest() {
        return availableTestsService.findAllByIsDeletedFalse().stream()
                .map(AvailableTestMapper::toDto)
                .collect(Collectors.toList());
    }

	@PreAuthorize("@permissionChecker.hasPermission('Available-Tests','VIEW')")
	@GetMapping("/{id}")
    public ResponseEntity<AvailableTestResponseDto> getById(@PathVariable Integer id) {
        AvailableTests test = availableTestsService.findByIdAndIsDeletedFalse(id);
        return ResponseEntity.ok(AvailableTestMapper.toDto(test));
    }


	@PreAuthorize("@permissionChecker.hasPermission('Available-Tests','UPDATE')")
	@PutMapping("/{id}")
    public ResponseEntity<AvailableTestResponseDto> update(
            @PathVariable Integer id,
		    @Validated @RequestBody AvailableTestRequestDto dto) {

        AvailableTests updated = availableTestsService.update(id, dto);
        return ResponseEntity.ok(AvailableTestMapper.toDto(updated));
    }


	@PreAuthorize("@permissionChecker.hasPermission('Available-Tests','DELETE')")
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        availableTestsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
