package xyz.cashbreaker.break_down_maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.cashbreaker.break_down_maker.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPage(String home);
}
