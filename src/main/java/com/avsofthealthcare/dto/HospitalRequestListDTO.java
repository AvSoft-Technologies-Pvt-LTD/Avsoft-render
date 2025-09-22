package com.avsofthealthcare.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class HospitalRequestListDTO {
    @NotBlank(message = "hospitalName is required")
    private String hospitalName;

    @NotBlank(message = "address is required")
    private String address;

    @NotBlank(message = "city is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "city must include only letters")
    private String city;

    @NotBlank(message = "state is required")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "state must include only letters")
    private String state;

    @NotBlank(message = "pincode is required")
    @Pattern(regexp = "^[1-9][0-9]{5}$", message = "pincode must be a valid 6 digit number")
    private String pinCode;

    private String ppnNonPpn;
}
