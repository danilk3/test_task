package com.example.test_task.controller;

import com.example.test_task.dto.quote.QuoteRequestDto;
import com.example.test_task.dto.quote.QuoteResponseDto;
import com.example.test_task.model.Quote;
import com.example.test_task.model.QuoteVote;
import com.example.test_task.service.quote.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/quote/")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping("user")
    public ResponseEntity saveQuote(@RequestBody QuoteRequestDto request) {
        try {
            quoteService.saveQuote(request);
            return ResponseEntity.ok(QuoteResponseDto.builder().status("success").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }

    @GetMapping("top10")
    public ResponseEntity getTop10Quotes() {
        try {
            List<Quote> quoteList = quoteService.getTop10Quotes();
            List<QuoteResponseDto> response = quoteList.stream().map(quote -> QuoteResponseDto.builder().content(quote.getContent()).score(quote.getScore()).creatorEmail(quote.getCreator().getEmail()).build()).toList();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }

    @GetMapping("random")
    public ResponseEntity getRandomQuote() {
        try {
            Quote randomQuote = quoteService.getRandomQuote();
            QuoteResponseDto response = QuoteResponseDto.builder().content(randomQuote.getContent()).score(randomQuote.getScore()).creatorEmail(randomQuote.getCreator().getEmail()).build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }

    @PutMapping("user/like")
    public ResponseEntity likeQuote(@RequestParam("quote_id") Long id) {
        try {
            quoteService.likeQuote(id);
            return ResponseEntity.ok(QuoteResponseDto.builder().status("success").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }

    @PutMapping("user/dislike")
    public ResponseEntity dislikeQuote(@RequestParam("quote_id") Long id) {
        try {
            quoteService.dislikeQuote(id);
            return ResponseEntity.ok(QuoteResponseDto.builder().status("success").build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }

    @GetMapping("user/last_votes")
    public ResponseEntity getLast5Votes() {
        try {
            List<QuoteVote> quoteVotes = quoteService.getLast5Votes();
            List<QuoteResponseDto> response = quoteVotes.stream().map(quote -> QuoteResponseDto.builder().content(quote.getQuote().getContent()).score(quote.getQuote().getScore()).build()).toList();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request");
        }
    }
}
