package pl.majewski.countryinfovolvo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Entity
@Data
public class Country {
    @Id
    private String cca3;

    private String commonName;
    private String officialName;

    @ElementCollection
    @CollectionTable(name = "country_capitals", joinColumns = @JoinColumn(name = "country_id"))
    @Column(name = "capital")
    private List<String> capitals;

    private String region;
    private String subregion;

    private int population;

    @ElementCollection
    @CollectionTable(name = "country_timezones", joinColumns = @JoinColumn(name = "country_id"))
    @Column(name = "timezone")
    private List<String> timezones;

    @ElementCollection
    @CollectionTable(name = "country_borders", joinColumns = @JoinColumn(name = "country_id"))
    @Column(name = "border")
    private List<String> borders;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "country_id")
    private List<Currency> currencies;

    @ElementCollection
    @CollectionTable(name = "country_languages", joinColumns = @JoinColumn(name = "country_id"))
    @MapKeyColumn(name = "language_code")
    @Column(name = "language_name")
    private Map<String, String> languages;
}