package com.varvara.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne()
    @JoinColumn(name = "collection_id")
    private Collection collection;

    @OneToMany(mappedBy = "item",
               cascade = CascadeType.ALL)
    private List<Tag> tags;

    @OneToMany(mappedBy = "item",
              cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "other_fields_items",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "other_field_id"))
    private List<OtherField> otherFields;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
