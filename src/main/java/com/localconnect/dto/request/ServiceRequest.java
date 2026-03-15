package com.localconnect.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServiceRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotNull(message = "Category is required")
    private Integer categoryId;

    @NotBlank(message = "City is required")
    private String city;
}