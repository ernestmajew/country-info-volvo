package pl.majewski.countryinfovolvo.configuration;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "country-api")
public class CountryApiProperties {

    @NotNull
    private String baseUrl;

    @NotNull
    private String fieldsUri;
}