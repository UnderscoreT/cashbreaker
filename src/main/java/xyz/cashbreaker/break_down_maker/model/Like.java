// Like.java
package xyz.cashbreaker.break_down_maker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String page;

    @Column(nullable = false)
    private int count = 0;

    public Like(String page, int count) {
        this.page = page;
        this.count = count;
    }
}
