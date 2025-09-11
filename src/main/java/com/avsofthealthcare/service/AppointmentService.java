package com.avsofthealthcare.service;

import com.avsofthealthcare.entity.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment bookAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();
}
