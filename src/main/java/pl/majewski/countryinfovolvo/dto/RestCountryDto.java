package pl.majewski.countryinfovolvo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class RestCountryDto {
    private String cca3;
    private Name name;
    private Map<String, Currency> currencies;
    private List<String> capital;
    private String region;
    private String subregion;
    private Map<String, String> languages;
    private long population;
    private List<String> borders;
    private List<String> timezones;

    @Data
    public static class Currency {
        private String name;
        private String symbol;
    }

    @Data
    public static class Name {
        private String common;
        private String official;
    }
}
