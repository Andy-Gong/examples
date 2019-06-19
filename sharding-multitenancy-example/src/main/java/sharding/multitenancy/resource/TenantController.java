package sharding.multitenancy.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sharding.multitenancy.model.Tenant;
import sharding.multitenancy.presentation.TenantVo;
import sharding.multitenancy.repository.TenantRepository;

@RestController(value = ResourceUtil.BASE_PATH + "/tenant/")
public class TenantController {

    @Autowired
    private TenantRepository tenantRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void save(@RequestBody TenantVo tenantVo) {
        Tenant tenant = Tenant.builder().name(tenantVo.getName()).build();
        tenantRepository.save(tenant);
    }
}
