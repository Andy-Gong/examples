package sharding.multitenancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import sharding.multitenancy.model.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
