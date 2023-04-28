package com.mammadli.order_management_system.service;

import com.mammadli.order_management_system.dto.OrderLineDto;
import com.mammadli.order_management_system.mapper.OrderLineMapper;
import com.mammadli.order_management_system.model.Order;
import com.mammadli.order_management_system.model.OrderLine;
import com.mammadli.order_management_system.model.Product;
import com.mammadli.order_management_system.repository.OrderLineRepository;
import com.mammadli.order_management_system.repository.OrderRepository;
import com.mammadli.order_management_system.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderLine createOrderLine(OrderLineDto orderLineDto) {

        Optional<Order> order = orderRepository.findById(orderLineDto.getOrderId());
        Optional<Product> product = productRepository.findById(orderLineDto.getProductId());

        if (order.isPresent() && product.isPresent()) {
            OrderLine orderLine = OrderLineMapper.INSTANCE.mapToOrderLine(orderLineDto);
            orderLine.setOrder(order.get());
            orderLine.setProduct(product.get());
            return orderLineRepository.save(orderLine);
        }
        return null;
    }

    public OrderLine updateProductQuantity(int quantity,Long orderLineId) {
        Optional<OrderLine> orderLine = orderLineRepository.findById(orderLineId);
        if(orderLine.isPresent()){
            orderLine.get().setQuantity(quantity);
            return orderLine.get();
        }
        return null;
    }
}
