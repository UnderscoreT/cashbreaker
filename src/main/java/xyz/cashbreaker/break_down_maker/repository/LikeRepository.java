package xyz.cashbreaker.break_down_maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.cashbreaker.break_down_maker.model.Like;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPage(String page);
    Optional<Like> findByPage(String page);
}
