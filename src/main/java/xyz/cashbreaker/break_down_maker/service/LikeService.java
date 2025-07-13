package xyz.cashbreaker.break_down_maker.service;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import xyz.cashbreaker.break_down_maker.model.Like;
import xyz.cashbreaker.break_down_maker.repository.LikeRepository;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    private Like like;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @PostConstruct
    public void init() {
        // Create one row if none exists
        if (likeRepository.count() == 0) {
            like = likeRepository.save(new Like(0));
        } else {
            like = likeRepository.findAll().get(0);
        }
    }

    public int incrementLike() {
        like.setCount(like.getCount() + 1);
        likeRepository.save(like);
        return like.getCount();
    }

    public int getLikes() {
        return like.getCount();
    }
}
