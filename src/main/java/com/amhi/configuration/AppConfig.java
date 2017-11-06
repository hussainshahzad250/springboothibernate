package com.amhi.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.amhi.*")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}
	
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver getCommonsMultipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(20971520);   // 20MB
	    multipartResolver.setMaxInMemorySize(1048576);  // 1MB
	    return multipartResolver;
	}

	// @Override
	// public void configureMessageConverters(List<HttpMessageConverter<?>>
	// converters) {
	//
	// Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	//
	// builder.indentOutput(true);
	// converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
	// }

	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// PasswordEncoder encoder = new BCryptPasswordEncoder();
	// return encoder;
	// }

	// @Override
	// public void addCorsMappings(CorsRegistry registry) {
	//
	// registry.addMapping("/api")
	// .allowedOrigins("http://172.16.27.9:8090",
	// "http://localhost:4200")
	// .allowedMethods("POST", "GET", "PUT", "DELETE")
	// .allowedHeaders("Content-Type")
	// .exposedHeaders("header-1", "header-2").allowCredentials(false)
	// .maxAge(6000);
	//
	// }
}
