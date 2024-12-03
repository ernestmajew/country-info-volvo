package pl.majewski.countryinfovolvo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.majewski.countryinfovolvo.dto.CountryDto;
import pl.majewski.countryinfovolvo.service.CountryService;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{countryCode}")
    public ResponseEntity<CountryDto> getCountryByAlpha3Code(@PathVariable String countryCode) {
        CountryDto countryDto = countryService.getCountryByCca3Code(countryCode);
        return ResponseEntity.ok(countryDto);
    }
}
