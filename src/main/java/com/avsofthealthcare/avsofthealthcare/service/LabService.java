package com.avsofthealthcare.avsofthealthcare.service;

import com.avsofthealthcare.avsofthealthcare.dto.LabRegisterRequest;
import java.io.IOException;

public interface LabService {
    String register(LabRegisterRequest req) throws IOException;
}
