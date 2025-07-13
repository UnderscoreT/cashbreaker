package xyz.cashbreaker.break_down_maker.service;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import xyz.cashbreaker.break_down_maker.model.LikeEntity;
import xyz.cashbreaker.break_down_maker.repository.LikeRepository;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    private LikeEntity likeEntity;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @PostConstruct
    public void init() {
        // Create one row if none exists
        if (likeRepository.count() == 0) {
            likeEntity = likeRepository.save(new LikeEntity(0));
        } else {
            likeEntity = likeRepository.findAll().get(0);
        }
    }

    public int incrementLike() {
        likeEntity.setCount(likeEntity.getCount() + 1);
        likeRepository.save(likeEntity);
        return likeEntity.getCount();
    }

    public int getLikes() {
        return likeEntity.getCount();
    }
}
