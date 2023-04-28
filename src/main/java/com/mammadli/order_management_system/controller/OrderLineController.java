package com.mammadli.order_management_system.controller;

import com.mammadli.order_management_system.dto.OrderLineDto;
import com.mammadli.order_management_system.model.OrderLine;
import com.mammadli.order_management_system.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orderLines")
public class OrderLineController {

    private final OrderLineService orderLineService;

    @PostMapping
    OrderLine addOrderLine(@RequestBody OrderLineDto orderLineDto){
        return orderLineService.createOrderLine(orderLineDto);
    }

    @PutMapping
    OrderLine updateProductQuantity(@RequestParam(name = "quantity") int quantity ,@RequestParam(name = "orderLineId") Long orderLineId){
        return orderLineService.updateProductQuantity(quantity,orderLineId);
    }
}
