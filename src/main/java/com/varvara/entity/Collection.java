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
@ToString
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


    @OneToMany(mappedBy = "collection",
               cascade = CascadeType.REMOVE)
    private List<Item> items;

    @ManyToMany()
    @JoinTable(name = "users_collections",
            joinColumns = @JoinColumn(name = "collection_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;


    @OneToMany(mappedBy = "collection",
            cascade = CascadeType.REMOVE)
    private List<OtherField> otherFields;
}
