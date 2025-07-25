package com.avsofthealthcare.avsofthealthcare.service;

import com.avsofthealthcare.avsofthealthcare.dto.DoctorRegisterRequest;
import java.io.IOException;

public interface DoctorService {
    String register(DoctorRegisterRequest req) throws IOException;
}
