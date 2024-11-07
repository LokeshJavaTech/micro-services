package com.lokesh.microservices.inventory.dto;

public record InventoryRequest(Long id, String skuCode, Integer quantity) {
}
