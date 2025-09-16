package com.avsofthealthcare.dto;

import java.time.LocalDate;

public class BillingRequest {

    private String invoiceId;
    private LocalDate date;
    private String doctorName;
    private String service;
    private Integer billedAmount;
    private Integer paidAmount;
    private String status;

    // Getters and setters

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
