package com.davimarques.carsApi.domain;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "carro")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "tipo")
    private String type;

    @Column(name = "descricao")
    private String description;

    @Column(name = "url_foto")
    private String url_photo;

    private String url_video;

    private String latitude;

    private String longitude;
}
