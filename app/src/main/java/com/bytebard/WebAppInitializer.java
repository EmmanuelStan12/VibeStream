package com.bytebard;

import com.bytebard.config.DefaultConfig;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.Conventions;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;
import java.util.*;


public final class WebAppInitializer implements WebApplicationInitializer {

    public static final String ROOT_PATH = "/*";
    public static final String API_PATH = "/api/v1/*";
    public static final String SERVLET_NAME = "dispatcher";
    public static final boolean ASYNC_SUPPORTED = true;
    public static final String SPRING_SECURITY_FILTER_BEAN = "springSecurityFilterChain";
    public static final String REQUEST_CONTEXT_FILTER = "requestContext";
    public static final String SECURITY_FILTER = "securityFilter";
    private static final Logger LOGGER = LoggerFactory.getLogger(WebAppInitializer.class);

    private final Class<?>[] servletConfigClasses = {};
    private final Class<?>[] rootConfigClasses = {
            DefaultConfig.class
    };
    private final ApplicationContextInitializer<?>[] rootContextListenerInitializers = {};
    private final Filter[] servletFilters = {};
    private final EnumSet<DispatcherType> dispatcherTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC);

    private WebApplicationContext createApplicationContext(Class<?>[] config) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        for (Class<?> clazz : config) {
            context.register(clazz);
        }
        return context;
    }

    private void registerDispatcherServlet(WebApplicationContext servletAppContext, ServletContext servletContext) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet(servletAppContext);
        dispatcherServlet.setContextInitializers((ApplicationContextInitializer<?>[]) null);
        var registration = servletContext.addServlet(SERVLET_NAME, dispatcherServlet);
        if (registration == null) {
            throw new IllegalStateException("Failed to register servlet with name '" + SERVLET_NAME + "'. Check if there is another servlet registered under the same name.");
        }

        registration.setLoadOnStartup(1);
        registration.addMapping(ROOT_PATH);
        registration.setAsyncSupported(true);
    }

    private void registerContextLoaderListener(WebApplicationContext rootContext, ServletContext servletContext) {
        ContextLoaderListener listener = new ContextLoaderListener(rootContext);
        listener.setContextInitializers(rootContextListenerInitializers);
        servletContext.addListener(listener);
    }

    @Override
    public void onStartup(@NotNull ServletContext servletContext) {
        LOGGER.debug("Starting up app....");
        WebApplicationContext rootContext = createApplicationContext(rootConfigClasses);
        registerContextLoaderListener(rootContext, servletContext);

        WebApplicationContext servletAppContext = createApplicationContext(servletConfigClasses);
        registerDispatcherServlet(servletAppContext, servletContext);

        servletContext.addFilter(REQUEST_CONTEXT_FILTER, new RequestContextFilter())
                .addMappingForUrlPatterns(null, false, ROOT_PATH);

        servletContext.addFilter(SECURITY_FILTER, new DelegatingFilterProxy(SPRING_SECURITY_FILTER_BEAN));
    }

    private void registerServletFilter(ServletContext servletContext, Filter filter) {
        String filterName = Conventions.getVariableName(filter);
        var registration = servletContext.addFilter(filterName, filter);
        if (registration == null) {
            throw new IllegalStateException("Failed to register filter with name '" + filterName + "'. Check if there is another filter registered under the same name.");
        }

        registration.setAsyncSupported(ASYNC_SUPPORTED);
        registration.addMappingForServletNames(dispatcherTypes, false, SERVLET_NAME);
    }
}

