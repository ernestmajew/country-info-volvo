package pl.majewski.countryinfovolvo.util;

import org.springframework.stereotype.Component;
import pl.majewski.countryinfovolvo.dto.CountryDto;
import pl.majewski.countryinfovolvo.dto.CurrencyDto;
import pl.majewski.countryinfovolvo.dto.RestCountryDto;
import pl.majewski.countryinfovolvo.entity.Country;
import pl.majewski.countryinfovolvo.entity.Currency;

import java.util.stream.Collectors;

@Component
public class ModelMapper {
    public Country mapRestCountryDtoToCountryEntity(RestCountryDto dto) {
        Country country = new Country();
        country.setCommonName(dto.getName().getCommon());
        country.setOfficialName(dto.getName().getOfficial());
        country.setCca3(dto.getCca3());
        country.setCapitals(dto.getCapital());
        country.setRegion(dto.getRegion());
        country.setSubregion(dto.getSubregion());
        country.setLanguages(dto.getLanguages());
        country.setPopulation((int) dto.getPopulation());
        country.setBorders(dto.getBorders());
        country.setTimezones(dto.getTimezones());

        country.setCurrencies(dto.getCurrencies().entrySet().stream()
                .map(entry -> {
                    Currency currency = new Currency();
                    currency.setCode(entry.getKey());
                    currency.setName(entry.getValue().getName());
                    currency.setSymbol(entry.getValue().getSymbol());
                    return currency;
                })
                .collect(Collectors.toList()));

        return country;
    }

    public CountryDto mapCountryEntityToCountryDto(Country country) {
        return CountryDto.builder()
                .cca3(country.getCca3())
                .commonName(country.getCommonName())
                .officialName(country.getOfficialName())
                .capitals(country.getCapitals())
                .region(country.getRegion())
                .subregion(country.getSubregion())
                .population(country.getPopulation())
                .borders(country.getBorders())
                .timezones(country.getTimezones())
                .languages(country.getLanguages())
                .currencies(country.getCurrencies().stream()
                        .map(currency -> CurrencyDto.builder()
                                .code(currency.getCode())
                                .name(currency.getName())
                                .symbol(currency.getSymbol())
                                .build())
                        .collect(Collectors.toList()))
                .build();
    }
}
