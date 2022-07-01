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

    @OneToMany(mappedBy = "otherField",
            cascade = CascadeType.REMOVE)
    private List<OtherFieldValue> value;

    @ManyToOne()
    @JoinColumn(name = "collection_id")
    private Collection collection;


}
