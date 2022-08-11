package br.com.creditas.credit_analysis.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfiguration {

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("br.com.creditas.credit_analysis.controller"))
            .paths(PathSelectors.any())
            .build()

    fun getApiInfo(): ApiInfo {
        val contact = Contact("Patricia Magalhães", "https://github.com/pmagalhaes2", "paath.magalhaes2@gmail.com")
        return ApiInfoBuilder()
                .title("Credit Analysis")
                .description("Spring Boot REST API for credit analysis considering CPF score")
                .version("1.0.0")
                .contact(contact)
                .build()
    }
}