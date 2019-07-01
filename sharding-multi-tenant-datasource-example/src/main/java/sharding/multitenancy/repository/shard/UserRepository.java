package sharding.multitenancy.repository.shard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import sharding.multitenancy.model.shard.User;

@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

}
