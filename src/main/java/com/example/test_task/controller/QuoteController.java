package com.example.test_task.controller;

import com.example.test_task.dto.quote.QuoteRequestDto;
import com.example.test_task.service.quote.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/quote/")
public class QuoteController {
    // .requestMatchers("/api/auth/**")
    //                                .permitAll()
    //                                .requestMatchers("/api/quote/**")
    //                                .permitAll()
    //                                .requestMatchers("/api/quote/user/**")
    //                                .authenticated())


    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("user")
    public ResponseEntity saveQuote(@RequestBody QuoteRequestDto request) {
        // TODO: эксепшн + dto
        return ResponseEntity.ok(quoteService.saveQuote(request));
    }

    @GetMapping("top10")
    public ResponseEntity getTop10Quotes() {
        // TODO: эксепшн + dto
        return ResponseEntity.ok(quoteService.getTop10Quotes());
    }

    @GetMapping("random")
    public ResponseEntity getRandomQuote() {
        // TODO: эксепшн + dto
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    @PutMapping("user/like")
    public ResponseEntity likeQuote(@PathVariable("id") Long id) {
        // TODO: эксепшн + dto + PathVariable?
        return ResponseEntity.ok(quoteService.likeQuote(id));
    }

    @PutMapping("user/dislike")
    public ResponseEntity dislikeQuote(@PathVariable("id") Long id) {
        // TODO: эксепшн + dto + PathVariable?
        return ResponseEntity.ok(quoteService.dislikeQuote(id));
    }

    @GetMapping("user/last_votes")
    public ResponseEntity getLast5Votes() {
        return ResponseEntity.ok(quoteService.getLast5Votes());
    }
}
