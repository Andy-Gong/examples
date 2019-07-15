package sharding.jdbc.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sharding.jdbc.example.model.Order;
import sharding.jdbc.example.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public int save(Order order) {
        return orderRepository.save(order);
    }

    public int batchSave(List<Order> orders) {
        return orderRepository.batchSave(orders);
    }
}
