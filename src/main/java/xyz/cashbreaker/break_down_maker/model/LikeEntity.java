package xyz.cashbreaker.break_down_maker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter @Setter


@NoArgsConstructor
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count = 0;


    public LikeEntity(int i) {
        this.count = i;
    }
}
