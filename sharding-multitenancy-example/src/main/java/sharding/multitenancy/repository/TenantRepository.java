package sharding.multitenancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import sharding.multitenancy.model.Tenant;

import javax.annotation.sql.DataSourceDefinition;

@Transactional
@DataSourceDefinition()
public interface TenantRepository extends JpaRepository<Tenant, Long> {

}
