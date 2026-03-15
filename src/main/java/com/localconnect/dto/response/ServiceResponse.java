package com.localconnect.dto.response;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ServiceResponse {
    private Integer id;
    private String title;
    private String description;
    private BigDecimal price;
    private String city;
    private Boolean isAvailable;
    private String categoryName;
    private String providerName;
    private Integer providerId;
}