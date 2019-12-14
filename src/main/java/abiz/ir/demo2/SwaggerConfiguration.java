package abiz.ir.demo2;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationScopeBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter {
    public SwaggerConfiguration() {
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
        registry.addResourceHandler(new String[]{"/**"}).addResourceLocations(new String[]{"classpath:/static/"});
    }

    @Bean
    public Docket abizApi() {
        Docket docket = (new Docket(DocumentationType.SWAGGER_2)).groupName("abiz").apiInfo(this.apiInfo()).select().build();
        docket.securitySchemes(Lists.newArrayList(new SecurityScheme[]{this.apiKey()})).securityContexts(Lists.newArrayList(new SecurityContext[]{this.securityContext()})).directModelSubstitute(LocalDateTime.class, Long.class).directModelSubstitute(LocalDate.class, Long.class);
        return docket;
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title("abiz API Info").description("API Documentation: Authentication : use /auth in AuthController to get api-key and send this token in Authorize form;").termsOfServiceUrl("#").contact(new Contact("asdsad", "http://asdasd.ir", "info@mail.asdasd.ir")).version("alpha").build();
    }

    @Bean
    SecurityContext securityContext() {
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{(new AuthorizationScopeBuilder()).scope("global").description("accessEverything").build()};
        SecurityReference securityReference = SecurityReference.builder().reference("Authorization").scopes(authorizationScopes).build();
        return SecurityContext.builder().securityReferences(Lists.newArrayList(new SecurityReference[]{securityReference})).forPaths(PathSelectors.ant("/**")).build();
    }

    @Bean
    SecurityScheme apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

}