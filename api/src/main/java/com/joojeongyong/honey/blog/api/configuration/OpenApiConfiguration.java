package com.joojeongyong.honey.blog.api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Honey Blog Open API")
            .description("허니 블로그 API")
            .version("1.0.0")
            .contact(new Contact()
                .email("jeongyong95@gmail.com")
                .name("Jeongyong Joo")
                .url("https://joojeongyong.tistory.com")
            )
            .title("Honey Blog Open API")
            .description("허니 블로그 API")
            .version("1.0.0")
        );
  }
}
