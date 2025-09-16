package com.avsofthealthcare.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "billing")
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoiceId;
    private LocalDate date;
    private String doctorName;
    private String service;
    private Integer billedAmount;
    private Integer paidAmount;
    private String status; // e.g., "Paid" or "Pending"

    public Billing() {}

    // Getters and setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getInvoiceId() {
        return invoiceId;
    }
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getDoctorName() {
        return doctorName;
    }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }
    public String getService() {
        return service;
    }
    public void setService(String service) {
        this.service = service;
    }
    public Integer getBilledAmount() {
        return billedAmount;
    }
    public void setBilledAmount(Integer billedAmount) {
        this.billedAmount = billedAmount;
    }
    public Integer getPaidAmount() {
        return paidAmount;
    }
    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
