package pl.majewski.countryinfovolvo.util;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.majewski.countryinfovolvo.service.RestCountryService;

@Component
public class CountryDataLoader implements ApplicationRunner {

    private final RestCountryService restCountryService;

    public CountryDataLoader(RestCountryService restCountryService) {
        this.restCountryService = restCountryService;
    }

    public void run(ApplicationArguments args) {
        restCountryService.seedDatabaseWithCountriesFromApi();
    }
}
