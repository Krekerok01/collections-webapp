package com.varvara.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "collection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotBlank(message = "The field name is required")
    private String name;

    @Column(name = "theme")
    @NotBlank(message = "The field theme is required")
    private String theme;

    @Column(name = "description")
    @NotBlank(message = "The field description is required")
    private String description;

    @Column(name = "image_url")
    @NotBlank(message = "The field image URL is required")
    private String imageUrl;


    @OneToMany(fetch = FetchType.EAGER,
            mappedBy = "collection",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Item> items;


    @Override
    public String toString() {
        return "Collection{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", theme='" + theme + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", items=" + items +
                '}';
    }
}
