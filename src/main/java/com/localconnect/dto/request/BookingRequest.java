package com.localconnect.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingRequest {

    @NotNull(message = "Service ID is required")
    private Integer serviceId;

    @NotNull(message = "Booking date is required")
    private LocalDate bookingDate;

    @NotBlank(message = "Time slot is required")
    private String timeSlot;

    @NotBlank(message = "Address is required")
    private String address;

    private String city;
}