package com.avsofthealthcare.avsofthealthcare.service;

import com.avsofthealthcare.avsofthealthcare.dto.HospitalRegisterRequest;
import java.io.IOException;

public interface HospitalService {
    String register(HospitalRegisterRequest req) throws IOException;
}