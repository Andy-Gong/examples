package sharding.multitenancy.repository.global;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import sharding.multitenancy.model.global.Tenant;

@Transactional
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("from tenant where tenantId=:tenantId")
    Tenant findByTenantId(@Param("tenantId") Long tenantId);
}
