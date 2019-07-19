package com.example.demo.multidatasource;

import com.example.demo.context.Context;
import com.example.demo.context.ContextManager;
import org.hibernate.context.TenantIdentifierMismatchException;
import org.hibernate.context.spi.CurrentSessionContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.stereotype.Component;

@Component
public class CustomTenantIdentifier implements CurrentTenantIdentifierResolver {

    /**
     * Resolve the current tenant identifier.
     *
     * @return The current tenant identifier
     */
    @Override
    public String resolveCurrentTenantIdentifier() {
        Context context = ContextManager.getContext();
        if (context == null) {
            return "1";
        }
        return String.valueOf(context.getTenantId());
    }

    /**
     * Should we validate that the tenant identifier on "current sessions" that already exist when
     * {@link CurrentSessionContext#currentSession()} is called matches the value returned here from
     * {@link #resolveCurrentTenantIdentifier()}?
     *
     * @return {@code true} indicates that the extra validation will be performed; {@code false} indicates it will not.
     * @see TenantIdentifierMismatchException
     */
    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}
