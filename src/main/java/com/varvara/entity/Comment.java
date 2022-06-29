package com.varvara.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment_text")
    private String commentText;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;

}
