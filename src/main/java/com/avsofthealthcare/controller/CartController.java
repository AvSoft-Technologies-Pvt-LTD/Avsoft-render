package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.CartRequestDTO;
import com.avsofthealthcare.dto.CartResponseDTO;
import com.avsofthealthcare.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lab/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartResponseDTO> addToCart(@RequestBody CartRequestDTO request) {
        return ResponseEntity.ok(cartService.addToCart(request));
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable Long patientId) {
        return ResponseEntity.ok(cartService.getCart(patientId));
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> clearCart(@PathVariable Long patientId) {
        cartService.clearCart(patientId);
        return ResponseEntity.noContent().build();
    }
}
