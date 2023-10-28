package com.example.service;

import com.example.HtmlUtil;
import com.example.dto.OrderDTO;
import com.example.entity.OrderEntity;
import com.example.entity.ProductEntity;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<String> create(OrderDTO orderDTO) {
        //TODO check prtId;
        Optional<ProductEntity> optionalProduct = productRepository
                .getByIdAndVisibleTrue(UUID.fromString(orderDTO.getProductId()));
        if (optionalProduct.isPresent()) {
            ProductEntity productEntity = optionalProduct.get();
            if (productEntity.getAmount() > 0) {
                OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setPrtId(prtId);//TODO
                orderEntity.setProductId(UUID.fromString(orderDTO.getProductId()));
                orderEntity.setName(orderEntity.getName());
                orderEntity.setPhone(orderDTO.getPhone());
                orderEntity.setAddress(orderDTO.getAddress());
                OrderEntity saved = orderRepository.save(orderEntity);

                productEntity.setAmount(productEntity.getAmount() - 1);
                productRepository.save(productEntity);
                return HtmlUtil.successOrdered();
            }
        }
        return HtmlUtil.failedOrdered();
    }
}
