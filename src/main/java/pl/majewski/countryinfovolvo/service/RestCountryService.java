package pl.majewski.countryinfovolvo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import pl.majewski.countryinfovolvo.dto.RestCountryDto;
import pl.majewski.countryinfovolvo.repository.CountryRepository;
import pl.majewski.countryinfovolvo.util.ModelMapper;

import java.util.List;

@Service
@Slf4j
public class RestCountryService {
    private final ModelMapper modelMapper;
    private final CountryRepository countryRepository;
    private final WebClient webClient;
    private final String requestUri;

    public RestCountryService(
            ModelMapper modelMapper,
            CountryRepository countryRepository,
            @Value("${external.countryApi.base-url}") String baseUrl,
            @Value("${external.countryApi.fields-uri}") String fieldsUrl) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.webClient = WebClient.create(baseUrl);
        this.requestUri = fieldsUrl;
    }

    public void seedDatabaseWithCountriesFromApi() {
        try {
            log.info("Fetching country data from API");
            List<RestCountryDto> restCountryDtoList = webClient.get()
                    .uri(requestUri)
                    .retrieve()
                    .bodyToFlux(RestCountryDto.class)
                    .collectList()
                    .block();

            if (restCountryDtoList != null) {
                log.info("Successfully fetched {} countries from API.", restCountryDtoList.size());
                try {
                    this.countryRepository.saveAll(
                            restCountryDtoList.stream()
                                    .map(modelMapper::mapRestCountryDtoToCountryEntity)
                                    .toList()
                    );
                    log.info("Successfully seeded {} countries into the database.", restCountryDtoList.size());
                } catch (Exception e) {
                    log.error("An error occurred while saving countries to the database.", e);
                    throw new RuntimeException("Failed to save countries to the database.", e);
                }
            } else {
                log.warn("No country data received from API.");
            }
        } catch (WebClientResponseException e) {
            log.error("Failed to fetch country data from API. Status code: {}", e.getStatusCode(), e);
        } catch (Exception e) {
            log.error("An unexpected error occurred while seeding database with countries from API.", e);
        }
    }
}