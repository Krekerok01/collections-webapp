package com.varvara.entity;

import lombok.*;
import javax.persistence.*;


@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "item_id")
    private int itemId;

    public Like(int userId, int itemId) {
        this.userId = userId;
        this.itemId = itemId;
    }
}
