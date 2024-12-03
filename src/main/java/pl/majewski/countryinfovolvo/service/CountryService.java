package pl.majewski.countryinfovolvo.service;

import org.springframework.stereotype.Service;
import pl.majewski.countryinfovolvo.dto.CountryDto;
import pl.majewski.countryinfovolvo.entity.Country;
import pl.majewski.countryinfovolvo.exception.CountryNotFoundException;
import pl.majewski.countryinfovolvo.exception.InvalidCountryCodeException;
import pl.majewski.countryinfovolvo.repository.CountryRepository;
import pl.majewski.countryinfovolvo.util.ModelMapper;
import java.util.regex.Pattern;

@Service
public class CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    private static final Pattern COUNTRY_CODE_PATTERN = Pattern.compile("^[A-Za-z]{3}$");

    public CountryService(CountryRepository countryRepository, ModelMapper modelMapper) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
    }

    public CountryDto getCountryByCca3Code(String countryCode) {
        if (!COUNTRY_CODE_PATTERN.matcher(countryCode).matches()) {
            throw new InvalidCountryCodeException("This country code is invalid: " + countryCode);
        }

        Country country = countryRepository.findByCca3(countryCode.toUpperCase())
                .orElseThrow(() -> new CountryNotFoundException("Country with code " + countryCode + " not found"));

        return modelMapper.mapCountryEntityToCountryDto(country);
    }
}
