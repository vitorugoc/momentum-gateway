package momentum.api.gateway.route;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import momentum.api.gateway.filter.JwtAuthFilter;

@Configuration
public class GatewayRoutesConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public GatewayRoutesConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("service1", r -> r.path("/service1/**")
                        .filters(f -> f.filter((GatewayFilter) jwtAuthFilter))
                        .uri("http://localhost:8081"))
                .build();
    }
}