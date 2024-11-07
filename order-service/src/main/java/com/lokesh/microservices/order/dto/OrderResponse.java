package com.lokesh.microservices.order.dto;

import java.math.BigDecimal;

public record OrderResponse(Long id, String orderNumber, String skuCode, BigDecimal price, Integer quantity) {
}
