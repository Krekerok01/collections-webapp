package com.varvara.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "other_field")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OtherField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @ManyToOne()
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @ManyToMany()
    @JoinTable(name = "other_fields_items",
            joinColumns = @JoinColumn(name = "other_field_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;
}
