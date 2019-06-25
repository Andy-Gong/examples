package sharding.multitenancy.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sharding.multitenancy.context.Context;
import sharding.multitenancy.context.ContextManager;
import sharding.multitenancy.model.Tenant;
import sharding.multitenancy.repository.TenantRepository;
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
//            Optional<Tenant> tenant = tenantRepository.findById(Long.valueOf(tenantId));
//            if (!tenant.isPresent()) {
//                throw new RuntimeException("the tenant isn't exist.");
//            }
            ContextManager.addContext(Context
                .builder()
                .tenant(Tenant.builder().id(Long.valueOf(tenantId)).schema("schema_111")
                    .build())
                .build());
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
