package xyz.cashbreaker.break_down_maker.web;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.cashbreaker.break_down_maker.service.LikeService;

@RestController
@RequestMapping("/api/likes")
public class LikesController {

    private final LikeService likeService;

    public LikesController(LikeService likeService) {
        this.likeService = likeService;
    }

    private static final String PAGE_ID = "breakdown-form";
    private static final String SESSION_KEY = "liked_" + PAGE_ID;

    @GetMapping
    public ResponseEntity<Integer> getLikes() {
        int likes = likeService.getLikes(PAGE_ID);
        return ResponseEntity.ok(likes);
    }

    @PostMapping
    public ResponseEntity<?> like(HttpSession session) {
        if (Boolean.TRUE.equals(session.getAttribute(SESSION_KEY))) {
            return ResponseEntity.status(429).body("Already liked");
        }
        session.setAttribute(SESSION_KEY, true);
        int newCount = likeService.incrementLike(PAGE_ID);
        return ResponseEntity.ok(newCount);
    }
}
