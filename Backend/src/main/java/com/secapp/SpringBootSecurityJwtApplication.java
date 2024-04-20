package com.secapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@SpringBootApplication
@OpenAPIDefinition (info=@Info(title = "Simple To-Do App",description="Simple to-do listing for users , user management for admins"))
@SecurityScheme(
	    name = "BearerAuth",
	    type = SecuritySchemeType.HTTP,
	    scheme = "bearer",
	    bearerFormat = "JWT"
	)
public class SpringBootSecurityJwtApplication {

	public static void main(String[] args) {
    SpringApplication.run(SpringBootSecurityJwtApplication.class, args);
	}

}
