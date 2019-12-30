package sharding.jdbc.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sharding.jdbc.example.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(Long id);
}
