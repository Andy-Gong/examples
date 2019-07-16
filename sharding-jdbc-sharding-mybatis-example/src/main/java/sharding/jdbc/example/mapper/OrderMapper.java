package sharding.jdbc.example.mapper;

import sharding.jdbc.example.model.Order;

import java.util.List;

public interface OrderMapper {

    int save(Order info);

    /**
     * 批量保存
     */
    int batchSave(List<Order> list);
}
