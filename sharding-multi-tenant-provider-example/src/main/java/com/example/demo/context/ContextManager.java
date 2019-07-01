package com.example.demo.context;

public class ContextManager {

    private final static ThreadLocal<Context> TENANTS = new ThreadLocal<>();

    public static void addContext(Context context) {
        if (TENANTS.get() == null) {
            TENANTS.set(context);
        }
    }

    public static Context getContext() {
        return TENANTS.get();
    }
}
