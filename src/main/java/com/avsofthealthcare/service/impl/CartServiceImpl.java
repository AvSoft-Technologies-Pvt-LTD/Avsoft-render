package com.avsofthealthcare.service.impl;

import com.avsofthealthcare.dto.CartRequestDTO;
import com.avsofthealthcare.dto.CartResponseDTO;
import com.avsofthealthcare.entity.Cart;
import com.avsofthealthcare.entity.LabTest;
import com.avsofthealthcare.entity.LabScan;
import com.avsofthealthcare.repository.CartRepository;
import com.avsofthealthcare.repository.LabTestRepository;
import com.avsofthealthcare.repository.LabScanRepository;
import com.avsofthealthcare.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final LabTestRepository testRepository;
    private final LabScanRepository scanRepository;

    @Override
    public CartResponseDTO addToCart(CartRequestDTO request) {
        List<LabTest> selectedTests = request.getTestIds() != null
                ? testRepository.findAllById(request.getTestIds())
                : List.of();

        List<LabScan> selectedScans = request.getScanIds() != null
                ? scanRepository.findAllById(request.getScanIds())
                : List.of();

        Cart cart = cartRepository.findByPatientId(request.getPatientId())
                .orElse(new Cart());

        cart.setPatientId(request.getPatientId());
        cart.setTests(selectedTests);
        cart.setScans(selectedScans);

        Cart saved = cartRepository.save(cart);

        return buildResponse(saved);
    }

    @Override
    public CartResponseDTO getCart(Long patientId) {
        Cart cart = cartRepository.findByPatientId(patientId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        return buildResponse(cart);
    }

    @Override
    public void clearCart(Long patientId) {
        cartRepository.deleteByPatientId(patientId);
    }

    private CartResponseDTO buildResponse(Cart cart) {
        double total = cart.getTests().stream().mapToDouble(LabTest::getPrice).sum()
                + cart.getScans().stream().mapToDouble(LabScan::getPrice).sum();

        CartResponseDTO dto = new CartResponseDTO();
        dto.setPatientId(cart.getPatientId());
        dto.setTests(cart.getTests().stream().map(LabTest::getTestname).collect(Collectors.toList()));
        dto.setScans(cart.getScans().stream().map(LabScan::getScanname).collect(Collectors.toList()));
        dto.setTotalAmount(total);

        return dto;
    }
}
