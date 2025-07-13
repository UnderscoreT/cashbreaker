package xyz.cashbreaker.break_down_maker.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import xyz.cashbreaker.break_down_maker.model.LikeEntity;
import xyz.cashbreaker.break_down_maker.repository.LikeRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public void run(String... args) {
        if (likeRepository.count() == 0) {
            likeRepository.save(new LikeEntity(0));
        }
    }
}
