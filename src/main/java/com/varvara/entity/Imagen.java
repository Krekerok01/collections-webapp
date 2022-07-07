package com.varvara.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "imagen")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "imagen_id")
    private String imagenId;


    public Imagen(String name, String imagenUrl, String imagenId) {
        this.name = name;
        this.imagenUrl = imagenUrl;
        this.imagenId = imagenId;
    }

}
