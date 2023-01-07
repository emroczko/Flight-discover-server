package com.tass.flightdiscover.domain;

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
    private String from;
    @Column(name = "destination_city")
    private String to;
    @Column(name = "number_of_flights")
    private String count;
}
