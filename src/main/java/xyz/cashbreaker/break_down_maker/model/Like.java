package xyz.cashbreaker.break_down_maker.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter @Setter


@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int count = 0;
    private String page;

    public Like(int i) {
        this.count = i;
    }

    public Like(String home, int i) {
        this.count = i;
    }

    public void setPage(String home) {
    }
}
