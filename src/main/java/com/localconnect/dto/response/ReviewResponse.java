package com.localconnect.dto.response;

import lombok.Data;

@Data
public class ReviewResponse {
    private Integer id;
    private String customerName;
    private String providerName;
    private Integer rating;
    private String comment;
}