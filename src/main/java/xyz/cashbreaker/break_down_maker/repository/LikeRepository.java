package xyz.cashbreaker.break_down_maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.cashbreaker.break_down_maker.model.LikeEntity;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {
}
