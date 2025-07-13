package xyz.cashbreaker.break_down_maker.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.cashbreaker.break_down_maker.model.LikeEntity;
import xyz.cashbreaker.break_down_maker.repository.LikeRepository;


@RestController
@RequestMapping("/api/likes")
public class LikesController {

    private final LikeRepository likeRepository;



    public LikesController(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @PostMapping
    public ResponseEntity<?> addLike(HttpSession session) {
        Boolean liked = (Boolean) session.getAttribute("liked");

        if (Boolean.TRUE.equals(liked)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("You've already liked this.");
        }

        LikeEntity likeEntity;

        if (likeRepository.count() == 0) {
            likeEntity = likeRepository.save(new LikeEntity(1));
        } else {
            likeEntity = likeRepository.findAll().get(0);
            likeEntity.setCount(likeEntity.getCount() + 1);
            likeRepository.save(likeEntity);
        }

        session.setAttribute("liked", true);

        return ResponseEntity.ok(likeEntity.getCount());
    }

    @GetMapping
    public ResponseEntity<Integer> getLikes() {
        if (likeRepository.count() == 0) {
            return ResponseEntity.ok(0);
        }

        return ResponseEntity.ok(likeRepository.findAll().get(0).getCount());
    }
}

