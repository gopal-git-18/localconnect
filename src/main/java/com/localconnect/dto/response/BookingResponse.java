package com.localconnect.dto.response;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class BookingResponse {
    private Integer id;
    private String customerName;
    private String providerName;
    private String serviceTitle;
    private LocalDate bookingDate;
    private String timeSlot;
    private String status;
    private BigDecimal totalAmount;
    private String address;
    private String city;
}