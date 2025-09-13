package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.HospitalRegisterRequest;
import java.io.IOException;

public interface HospitalService {
    String register(HospitalRegisterRequest req) throws IOException;
}