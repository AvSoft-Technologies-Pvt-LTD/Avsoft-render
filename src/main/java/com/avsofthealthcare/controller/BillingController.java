package com.avsofthealthcare.controller;

import com.avsofthealthcare.dto.BillingRequest;
import com.avsofthealthcare.entity.Billing;
import com.avsofthealthcare.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingRepository billingRepository;

    // Fetch all invoices or by status
    @GetMapping
    public List<Billing> getInvoices(@RequestParam(required = false) String status) {
        if (status != null) {
            return billingRepository.findByStatus(status);
        }
        return billingRepository.findAll();
    }

    // Fetch invoice by ID
    @GetMapping("/{id}")
    public Billing getInvoiceById(@PathVariable Long id) {
        return billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    // Create new invoice
    @PostMapping
    public Billing createInvoice(@RequestBody BillingRequest request) {
        Billing billing = new Billing();
        billing.setInvoiceId(request.getInvoiceId());
        billing.setDate(request.getDate());
        billing.setDoctorName(request.getDoctorName());
        billing.setService(request.getService());
        billing.setBilledAmount(request.getBilledAmount());
        billing.setPaidAmount(request.getPaidAmount());
        billing.setStatus(request.getStatus());
        return billingRepository.save(billing);
    }

    // Update invoice
    @PutMapping("/{id}")
    public Billing updateInvoice(@PathVariable Long id, @RequestBody BillingRequest request) {
        Billing billing = billingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));

        billing.setInvoiceId(request.getInvoiceId());
        billing.setDate(request.getDate());
        billing.setDoctorName(request.getDoctorName());
        billing.setService(request.getService());
        billing.setBilledAmount(request.getBilledAmount());
        billing.setPaidAmount(request.getPaidAmount());
        billing.setStatus(request.getStatus());

        return billingRepository.save(billing);
    }

    // Delete invoice
    @DeleteMapping("/{id}")
    public String deleteInvoice(@PathVariable Long id) {
        billingRepository.deleteById(id);
        return "Invoice deleted successfully";
    }
}
