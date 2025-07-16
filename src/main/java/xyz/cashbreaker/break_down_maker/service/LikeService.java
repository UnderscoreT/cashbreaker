package xyz.cashbreaker.break_down_maker.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import xyz.cashbreaker.break_down_maker.model.Like;
import xyz.cashbreaker.break_down_maker.repository.LikeRepository;

import java.util.Optional;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @PostConstruct
    public void init() {
        if (!likeRepository.existsByPage("breakdown-form")) {
            likeRepository.save(new Like("breakdown-form", 0));
        }
    }

    public int incrementLike(String page) {
        Like like = likeRepository.findByPage(page)
                .orElseGet(() -> new Like(page, 0));
        like.setCount(like.getCount() + 1);
        likeRepository.save(like);
        return like.getCount();
    }

    public int getLikes(String page) {
        return likeRepository.findByPage(page)
                .map(Like::getCount)
                .orElse(0);
    }
}
