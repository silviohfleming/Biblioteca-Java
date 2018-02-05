package br.biblioteca.livros;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploaded-images/**")
                .addResourceLocations("file:///C:/Users/tuTeNB/Documents/github/Biblioteca-Java/uploaded-images/uploaded-images/");
    }
}