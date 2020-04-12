package com.suollon.lvchang.nonbusiness.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangwl
 * @date 2020/4/12 15:05
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("^(?!auth).*$"))
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());

        //如果代码里没写.apis(RequestHandlerSelectors.basePackage("com.suollon.demo.controller"))
        //需要加注解@ComponentScan(basePackages = {"com.suollon.demo.controller"})
		/*return new Docket(DocumentationType.SWAGGER_2)
                		.apiInfo(apiInfo())
                		.tags(new Tag("loan-application","资产信息-借款申请"),getTags())
                		.select()
                		.apis(RequestHandlerSelectors.basePackage("com.suollon.demo.controller"))
               		.paths(PathSelectors.any())
                		.build()
                		.securitySchemes(securitySchemes())
                		.securityContexts(securityContexts())
                		.directModelSubstitute(org.joda.time.LocalDate.class, java.sql.Date.class)
                		.directModelSubstitute(org.joda.time.DateTime.class, java.util.Date.class);
		*/
    }

    private List<ApiKey> securitySchemes() {
        List<ApiKey> list = new ArrayList<>();
        list.add(new ApiKey("Authorization", "Authorization", "header"));
        return list;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> list = new ArrayList<>();
        list.add(SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.regex("^(?!auth).*$"))
                .build());
        return list;
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        List<SecurityReference> list = new ArrayList<>();
        list.add(new SecurityReference("Authorization", authorizationScopes));
        return list;
    }

}
