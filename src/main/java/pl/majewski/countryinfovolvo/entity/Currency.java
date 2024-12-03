package pl.majewski.countryinfovolvo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;
    private String symbol;
}