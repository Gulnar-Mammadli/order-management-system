package com.mammadli.order_management_system.controller;

import com.mammadli.order_management_system.dto.OrderLineDto;
import com.mammadli.order_management_system.model.OrderLine;
import com.mammadli.order_management_system.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderLines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @PostMapping
    OrderLine addOrderLine(@RequestBody OrderLineDto orderLineDto){
        return orderLineService.createOrderLine(orderLineDto);
    }
}
