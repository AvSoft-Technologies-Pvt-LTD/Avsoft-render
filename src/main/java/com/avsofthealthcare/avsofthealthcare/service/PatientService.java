package com.avsofthealthcare.avsofthealthcare.service;

import com.avsofthealthcare.avsofthealthcare.dto.PatientRegisterRequest;
import java.io.IOException;

public interface PatientService {
    String register(PatientRegisterRequest req) throws IOException;
}