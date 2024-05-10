package com.alekseyz.testtask.tracktime.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Track time application",
                description = "Приложение для синхронного/асинхронного замера времени выполнения методов"
                , version = "1.0.0",
                contact = @Contact(
                        name = "Алексей Заборников"
                )
        )
)
public class OpenApiConfig {

}
