package pl.majewski.countryinfovolvo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class CountryDto {
    private String cca3;
    private String commonName;
    private String officialName;
    private List<String> capitals;
    private String region;
    private String subregion;
    private int population;
    private List<String> borders;
    private List<String> timezones;
    private Map<String, String> languages;
    private List<CurrencyDto> currencies;
}