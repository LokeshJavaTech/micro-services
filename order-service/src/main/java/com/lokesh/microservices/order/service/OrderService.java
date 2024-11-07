package com.lokesh.microservices.order.service;

import com.lokesh.microservices.order.dto.OrderRequest;
import com.lokesh.microservices.order.dto.OrderResponse;
import com.lokesh.microservices.order.model.Order;
import com.lokesh.microservices.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderResponse placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .skuCode(orderRequest.skuCode())
                .price(orderRequest.price())
                .quantity(orderRequest.quantity())
                .build();
        orderRepository.save(order);
        log.info("Order {} created successfully", order.getId());
        return new OrderResponse(order.getId(), order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity());
    }

}
