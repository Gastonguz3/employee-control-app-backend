package com.gaston.project.employee.configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Employee Control App",
                description = "API REST para gestionar empleados, permitiendo crear, actualizar, buscar y eliminar registros",
                contact = @Contact(name = "Gaston Guzman", email = "gastonezguzman@gmail.com", url = "https://www.linkedin.com/in/gaston-guzman-192730352/"),
                version = "1.0.0"),
        servers = {
                @Server(
                        url = "http://localhost:8080",
                        description = "DEV SERVER"
                ),
                @Server(
                        url = "https://employee-control-app.onrender.com",
                        description = "PROD SERVER"
                )
        }
)
public class SwaggerConfig {
}
