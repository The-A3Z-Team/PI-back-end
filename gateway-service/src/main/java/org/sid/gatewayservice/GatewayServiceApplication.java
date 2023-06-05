package org.sid.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("payment-service", r -> r.path("/payments/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8094"))
				.route("negociation-service", r -> r.path("/negociations/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8095"))
				.route("education-service", r -> r.path("/educations/**")
						.filters(f -> f.stripPrefix(1))
						.uri("http://localhost:8096"))
				.build();
	}
}
