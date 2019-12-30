package sharding.jdbc.example.filter;

import io.shardingsphere.api.HintManager;
import org.springframework.stereotype.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class ContextFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HintManager hintManager = HintManager.getInstance();
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String tenantId = httpServletRequest.getHeader("tenantId");
            hintManager.addDatabaseShardingValue("user", tenantId);
            hintManager.addTableShardingValue("user", tenantId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            hintManager.close();
        }
    }

    @Override
    public void destroy() {

    }
}
