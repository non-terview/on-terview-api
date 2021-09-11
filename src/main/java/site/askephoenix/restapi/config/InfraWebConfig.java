package site.askephoenix.restapi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import site.askephoenix.restapi.resolver.UserHandlerMethodArgumentResolver;

import java.util.List;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
public class InfraWebConfig implements WebMvcConfigurer {
    private final UserHandlerMethodArgumentResolver userHandlerMethodArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolverList) {
        resolverList.add(userHandlerMethodArgumentResolver);
    }
}
