package io.oreto.gringo;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.oreto.gringo.jackson5.Jackson5HttpMessageConverter;
import io.oreto.jackson.Jackson5;
import io.oreto.jackson.MapperConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"io.oreto.gringo"})
public class WebAppInitializer implements WebApplicationInitializer, WebMvcConfigurer {
    @Override
    public void onStartup(ServletContext container) {
        // Create the 'root' Spring application context
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(WebAppInitializer.class);

        // Manage the lifecycle of the root application context
        container.addListener(new ContextLoaderListener(rootContext));

        // Create the dispatcher servlet's Spring application context
        AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

        // Register and map the dispatcher servlet
        ServletRegistration.Dynamic dispatcher = container
                .addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return MapperConfig.defaultConfig().build();
    }

    @Bean
    Jackson5 jackson5() {
        Jackson5.supply(this::objectMapper);
        return Jackson5.get();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
        messageConverters.add(new Jackson5HttpMessageConverter());
    }
}
