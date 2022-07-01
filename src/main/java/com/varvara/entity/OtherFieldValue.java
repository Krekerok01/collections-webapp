package com.varvara.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "other_field_value")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class OtherFieldValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;


    @Column(name = "text")
    private String text;


    @ManyToOne()
    @JoinColumn(name = "other_field_id")
    private OtherField otherField;

    @ManyToMany()
    @JoinTable(name = "other_fields_values_items",
            joinColumns = @JoinColumn(name = "other_field_value_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private List<Item> items;
}
