package com.avsofthealthcare.service;

import com.avsofthealthcare.dto.CartRequestDTO;
import com.avsofthealthcare.dto.CartResponseDTO;

public interface CartService {
    CartResponseDTO addToCart(CartRequestDTO request);
    CartResponseDTO getCart(Long patientId);
    void clearCart(Long patientId);
}
