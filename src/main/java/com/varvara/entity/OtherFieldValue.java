package com.varvara.entity;

import lombok.*;

import javax.persistence.*;


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
}
