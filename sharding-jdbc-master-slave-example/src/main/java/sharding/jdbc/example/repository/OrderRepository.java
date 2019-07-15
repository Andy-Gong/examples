package sharding.jdbc.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharding.jdbc.example.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
