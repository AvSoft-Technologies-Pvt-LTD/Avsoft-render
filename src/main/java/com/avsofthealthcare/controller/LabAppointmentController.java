package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.LabAppointmentRequestDTO;
import com.avsofthealthcare.dto.LabAppointmentResponseDTO;
import com.avsofthealthcare.service.LabAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lab/appointments")
@RequiredArgsConstructor
public class LabAppointmentController {

    private final LabAppointmentService appointmentService;

    @PostMapping("/book")
    public ResponseEntity<LabAppointmentResponseDTO> bookAppointment(
            @RequestBody LabAppointmentRequestDTO request) {
        return ResponseEntity.ok(appointmentService.bookAppointment(request));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<List<LabAppointmentResponseDTO>> getAppointments(
            @PathVariable String patientId) {
        return ResponseEntity.ok(appointmentService.getAppointments(patientId));
    }
}
