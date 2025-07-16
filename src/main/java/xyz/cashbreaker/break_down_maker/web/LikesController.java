package xyz.cashbreaker.break_down_maker.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.cashbreaker.break_down_maker.model.Like;
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
        System.out.println("Session ID: " + session.getId());
        System.out.println("Already liked? " + session.getAttribute("liked"));


        Boolean liked = (Boolean) session.getAttribute("liked");

        if (Boolean.TRUE.equals(liked)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("You've already liked this.");
        }

        Like like;

        if (likeRepository.count() == 0) {
            like = likeRepository.save(new Like(1));
        } else {
            like = likeRepository.findAll().get(0);
            like.setCount(like.getCount() + 1);
            likeRepository.save(like);
        }

        session.setAttribute("liked", true);
        System.out.println("Like saved, session marked as liked.");
        return ResponseEntity.ok(like.getCount());
    }

    @GetMapping
    public ResponseEntity<Integer> getLikes() {
        if (likeRepository.count() == 0) {
            return ResponseEntity.ok(0);
        }

        return ResponseEntity.ok(likeRepository.findAll().get(0).getCount());
    }
}

