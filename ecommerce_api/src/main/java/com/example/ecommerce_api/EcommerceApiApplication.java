package com.example.ecommerce_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

@SpringBootApplication
public class EcommerceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApiApplication.class, args);
	}

  @Bean
  WebMvcConfigurer webMvcConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
          .allowedOrigins("*")
          .allowedMethods("GET", "POST", "PUT", "DELETE");
      }
    };
  }


  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);  // request => from json to object/entity
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);  // response => from object/entity to json
    objectMapper.registerModule(new Hibernate6Module()); //Registering Hibernate6Module to support lazy objects
    objectMapper.registerModule(new JSR310Module());
    //objectMapper.configure(MapperFeature.PROPAGATE_TRANSIENT_MARKER, true);
    /*objectMapper.setVisibilityChecker(
      objectMapper.getSerializationConfig()
        .getDefaultVisibilityChecker()
        .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
        .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
    );*/
    return objectMapper;
  }


  @Bean
  public MappingJackson2HttpMessageConverter jacksonMessageConverter() {
    MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
    messageConverter.setObjectMapper(objectMapper());
    return messageConverter;
  }

  @Bean
  public CommonsRequestLoggingFilter requestLoggingFilter() {
    CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
//    loggingFilter.setIncludeClientInfo(true);
//    loggingFilter.setIncludeQueryString(true);
    loggingFilter.setIncludePayload(true);
    loggingFilter.setMaxPayloadLength(64000);
    return loggingFilter;
  }



}
