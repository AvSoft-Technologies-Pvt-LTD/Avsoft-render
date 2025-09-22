package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.LabAppointmentRequestDTO;
import com.avsofthealthcare.dto.LabAppointmentResponseDTO;

import java.util.List;

public interface LabAppointmentService {
    LabAppointmentResponseDTO bookAppointment(LabAppointmentRequestDTO request);

    List<LabAppointmentResponseDTO> getAppointments(String patientId);
}
