package com.tass.flightdiscover.domain.flight;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "origin_city")
    private String originCity;
    @Column(name = "origin_country")
    private String originCountry;
    @Column(name = "destination_city")
    private String destinationCity;
    @Column(name = "destination_country")
    private String destinationCountry;
    @Column(name = "number_of_flights")
    private Long count;
}
