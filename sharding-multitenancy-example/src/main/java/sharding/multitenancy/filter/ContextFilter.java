package sharding.multitenancy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sharding.multitenancy.context.Context;
import sharding.multitenancy.context.ContextManager;
import sharding.multitenancy.model.global.Tenant;
import sharding.multitenancy.repository.global.TenantRepository;
import sharding.multitenancy.resource.ResourceUtil;

@Component
public class ContextFilter implements Filter {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
        throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String tenantId = httpServletRequest.getHeader("tenantId");
        if (StringUtils.isBlank(tenantId) && httpServletRequest.getContextPath().contains(ResourceUtil.TENANT_PATH)) {
            ContextManager.addContext(Context.builder().global(true).build());
        } else {
            Tenant tenant = tenantRepository.findByTenantId(Long.valueOf(tenantId));
            if (tenant == null) {
                throw new RuntimeException("the tenant isn't exist.");
            }
            ContextManager.addContext(Context
                .builder()
                .tenant(Tenant.builder()
                    .id(Long.valueOf(tenantId))
                    .name(tenant.getName())
                    .schema(tenant.getSchema())
                    .build())
                .build());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
