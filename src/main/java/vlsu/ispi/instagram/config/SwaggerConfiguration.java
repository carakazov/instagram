package vlsu.ispi.instagram.config;

import java.util.Collections;

import com.fasterxml.classmate.TypeResolver;
import vlsu.ispi.instagram.dto.ErrorDto;
import vlsu.ispi.instagram.dto.ValidationErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .additionalModels(typeResolver.resolve(ErrorDto.class), typeResolver.resolve(ValidationErrorDto.class))
            .select()
            .apis(
                RequestHandlerSelectors.withClassAnnotation(RestController.class)
            )
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
            "Какой-то сервис", //FIXME
            "Какое-то описание", //FIXME
            "1.0.0",
            "Берите да пользуйтесь",
            contact(),
            "Все бесплатно",
            "",
            Collections.emptyList()
        );
    }

    private Contact contact() {
        return new Contact("Vadim", "", "carakazov@yandex.ru");
    }
}
