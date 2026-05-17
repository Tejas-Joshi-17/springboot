package com.sarvatra.service;

import com.sarvatra.clients.InventoryOpenFeignClient;
import com.sarvatra.dto.OrderRequestDto;
import com.sarvatra.dto.OrderRequestItemDto;
import com.sarvatra.entity.OrderItem;
import com.sarvatra.entity.Orders;
import com.sarvatra.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final InventoryOpenFeignClient inventoryOpenFeignClient;

    public List<OrderRequestDto> getAllOrders() {
        log.info("Fetching all orders");
        final List<Orders> orders = ordersRepository.findAll();
        List<OrderRequestDto> requestedOrders = new ArrayList<>();
        for (Orders orders1 : orders) {
            OrderRequestDto orderedItem = new OrderRequestDto();
            List<OrderRequestItemDto> orderRequestItemDto = new ArrayList<>();

            for(OrderItem items : orders1.getItems()) {
                OrderRequestItemDto orderRequestItemDto1 = new OrderRequestItemDto();
                orderRequestItemDto1.setId(items.getId());
                orderRequestItemDto1.setQuantity(items.getQuantity());
                orderRequestItemDto1.setProductId(items.getProductId());
                orderRequestItemDto.add(orderRequestItemDto1);
            }

            orderedItem.setItems(orderRequestItemDto);
            orderedItem.setId(orders1.getId());
            orderedItem.setTotalPrice(BigDecimal.valueOf(orders1.getTotalPrice()));
            requestedOrders.add(orderedItem);
        }

        return requestedOrders;
    }

    public OrderRequestDto getOrderById(Long id) {
        log.info("Fetching order with id :- {}", id);
        final Orders order = ordersRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        List<OrderRequestItemDto> orderRequestItemDto = new ArrayList<>();
        OrderRequestItemDto orderRequestItemDto1 = new OrderRequestItemDto();

        for(OrderItem items : order.getItems()) {
            orderRequestItemDto1.setId(items.getId());
            orderRequestItemDto1.setQuantity(items.getQuantity());
            orderRequestItemDto1.setProductId(items.getProductId());
            orderRequestItemDto.add(orderRequestItemDto1);
        }

        return new OrderRequestDto(order.getId(), orderRequestItemDto, BigDecimal.valueOf(order.getTotalPrice()));
    }

}
