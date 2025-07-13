package xyz.cashbreaker.break_down_maker.service;

import jakarta.persistence.Column;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import xyz.cashbreaker.break_down_maker.model.Like;
import xyz.cashbreaker.break_down_maker.repository.LikeRepository;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    private Like like;
    @Column(nullable = false)
    private String page;



    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }



    @PostConstruct
    public void init() {
        if (!likeRepository.existsByPage("home")) {




            Like like = new Like();
            like.setCount(0);
            like.setPage("homepage");  // or some default page identifier
            likeRepository.save(like);

            likeRepository.save(like);
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



