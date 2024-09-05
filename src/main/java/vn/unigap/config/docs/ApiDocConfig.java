package vn.unigap.config.docs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


/** Configures OpenAPI documentation. */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Recruitment Service API",
                version = "${api.version}",
                contact = @Contact(name = "Wie", email = "wie@gmail.com", url = "https://wie.com"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        ),
        servers = @Server(
                url = "${api.server.url}",
                description = "localhost"
        )
)
@SecurityScheme(
        name = "Authorization",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class ApiDocConfig {

  @Value("${api.version}")
  String apiVersion;

  @Value("${api.server.url}")
  String apiServerUrl;

}
