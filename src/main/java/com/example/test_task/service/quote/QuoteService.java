package com.example.test_task.service.quote;

import com.example.test_task.dto.quote.QuoteRequestDto;
import com.example.test_task.model.Quote;
import com.example.test_task.model.QuoteVote;

import java.util.List;

public interface QuoteService {

    List<Quote> getTop10Quotes();

    Quote findById(Long id);

    Quote getRandomQuote();

    Quote likeQuote(Long id);

    Quote dislikeQuote(Long id);

    Quote saveQuote(QuoteRequestDto reques);

    List<QuoteVote> getLast5Votes();
}
