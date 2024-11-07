package com.lokesh.microservices.inventory.controller;

import com.lokesh.microservices.inventory.dto.InventoryRequest;
import com.lokesh.microservices.inventory.dto.InventoryResponse;
import com.lokesh.microservices.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public InventoryResponse isInStock(InventoryRequest inventoryRequest) {
        return inventoryService.isInStock(inventoryRequest);
    }

}