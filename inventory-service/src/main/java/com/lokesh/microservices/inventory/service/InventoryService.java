package com.lokesh.microservices.inventory.service;

import com.lokesh.microservices.inventory.dto.InventoryRequest;
import com.lokesh.microservices.inventory.dto.InventoryResponse;
import com.lokesh.microservices.inventory.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryResponse isInStock(InventoryRequest inventoryRequest) {
        boolean isInStock = inventoryRepository.existsBySkuCodeAndQuantityIsGreaterThanEqual(
                inventoryRequest.skuCode(),
                inventoryRequest.quantity());
        return new InventoryResponse(isInStock);
    }
}
