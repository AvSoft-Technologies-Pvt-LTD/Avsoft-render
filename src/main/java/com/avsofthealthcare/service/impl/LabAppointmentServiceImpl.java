package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.LabAppointmentRequestDTO;
import com.avsofthealthcare.dto.LabAppointmentResponseDTO;
import com.avsofthealthcare.entity.LabAppointment;
import com.avsofthealthcare.entity.LabTest;
import com.avsofthealthcare.entity.LabScan;
import com.avsofthealthcare.repository.LabAppointmentRepository;
import com.avsofthealthcare.repository.LabTestRepository;
import com.avsofthealthcare.repository.LabScanRepository;
import com.avsofthealthcare.service.LabAppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LabAppointmentServiceImpl implements LabAppointmentService {

    private final LabAppointmentRepository appointmentRepository;
    private final LabTestRepository testRepository;
    private final LabScanRepository scanRepository;

    @Override
    public LabAppointmentResponseDTO bookAppointment(LabAppointmentRequestDTO request) {
        List<LabTest> selectedTests = request.getTestIds() != null
                ? testRepository.findAllById(request.getTestIds())
                : List.of();

        List<LabScan> selectedScans = request.getScanIds() != null
                ? scanRepository.findAllById(request.getScanIds())
                : List.of();

        LabAppointment appointment = new LabAppointment();
        appointment.setAppointmentId("APT" + System.currentTimeMillis());
        appointment.setPatientId(request.getPatientId());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setStatus("Paid"); // placeholder
        appointment.setTests(selectedTests);
        appointment.setScans(selectedScans);

        LabAppointment saved = appointmentRepository.save(appointment);

        double totalAmount =
                selectedTests.stream().mapToDouble(LabTest::getPrice).sum()
                        + selectedScans.stream().mapToDouble(LabScan::getPrice).sum();

        LabAppointmentResponseDTO response = new LabAppointmentResponseDTO();
        response.setAppointmentId(saved.getAppointmentId());
        response.setPatientId(saved.getPatientId());
        response.setAppointmentDate(saved.getAppointmentDate());
        response.setStatus(saved.getStatus());
        response.setTests(selectedTests.stream().map(LabTest::getTestname).collect(Collectors.toList()));
        response.setScans(selectedScans.stream().map(LabScan::getScanname).collect(Collectors.toList()));
        response.setTotalAmount(totalAmount);

        return response;
    }

    @Override
    public List<LabAppointmentResponseDTO> getAppointments(String patientId) {
        return appointmentRepository.findByPatientId(patientId).stream().map(appointment -> {
            LabAppointmentResponseDTO dto = new LabAppointmentResponseDTO();
            dto.setAppointmentId(appointment.getAppointmentId());
            dto.setPatientId(appointment.getPatientId());
            dto.setAppointmentDate(appointment.getAppointmentDate());
            dto.setStatus(appointment.getStatus());
            dto.setTests(appointment.getTests().stream().map(LabTest::getTestname).collect(Collectors.toList()));
            dto.setScans(appointment.getScans().stream().map(LabScan::getScanname).collect(Collectors.toList()));
            dto.setTotalAmount(
                    appointment.getTests().stream().mapToDouble(LabTest::getPrice).sum()
                            + appointment.getScans().stream().mapToDouble(LabScan::getPrice).sum()
            );
            return dto;
        }).collect(Collectors.toList());
    }
}
