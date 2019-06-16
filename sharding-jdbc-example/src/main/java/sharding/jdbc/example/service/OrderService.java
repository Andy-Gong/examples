package sharding.jdbc.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sharding.jdbc.example.mapper.OrderMapper;
import sharding.jdbc.example.model.Order;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public int save(Order order) {
        return orderMapper.save(order);
    }

    public int batchSave(List<Order> orders) {
        return orderMapper.batchSave(orders);
    }
}
