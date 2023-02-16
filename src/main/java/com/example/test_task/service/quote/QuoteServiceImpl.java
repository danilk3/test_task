package com.example.test_task.service.quote;

import com.example.test_task.dto.quote.QuoteRequestDto;
import com.example.test_task.model.Quote;
import com.example.test_task.model.QuoteVote;
import com.example.test_task.model.User;
import com.example.test_task.repository.QuoteRepository;
import com.example.test_task.repository.QuoteVoteRepository;
import com.example.test_task.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    private final QuoteVoteRepository quoteVoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository, UserRepository userRepository, QuoteVoteRepository quoteVoteRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
        this.quoteVoteRepository = quoteVoteRepository;
    }

    @Override
    public List<Quote> getTop10Quotes() {
        return quoteRepository.findTop10ByOrderByScoreDesc();
    }

    @Override
    public Quote findById(Long id) {
        Quote quote = quoteRepository.findById(id).orElse(null);
        return quote;
    }

    @Override
    public Quote getRandomQuote() {
        Long size = quoteRepository.count();
        return findById(new Random().nextLong(size + 1));
    }

    @Override
    public Quote likeQuote(Long id) {
        User user = getCurrentUser();
        Quote quote = findById(id);
        quote.setScore(quote.getScore() + 1);

        QuoteVote vote = new QuoteVote();
        vote.setVoter(user);
        vote.setQuote(quote);
        vote.setCreatedAt(LocalDate.now());
        quoteVoteRepository.save(vote);

        return quote;
    }

    @Override
    public Quote dislikeQuote(Long id) {
        User user = getCurrentUser();
        Quote quote = findById(id);
        quote.setScore(quote.getScore() - 1);

        QuoteVote vote = new QuoteVote();
        vote.setVoter(user);
        vote.setQuote(quote);
        vote.setCreatedAt(LocalDate.now());
        quoteVoteRepository.save(vote);

        return quote;
    }

    @Override
    public Quote saveQuote(QuoteRequestDto request) {
        User creator = getCurrentUser();

        Quote quote = new Quote();
        quote.setContent(request.getContent());
        quote.setScore(0);
        quote.setCreatedAt(LocalDate.now());
        quote.setCreator(creator);

        quoteRepository.save(quote);

        return quote;
    }

    @Override
    public List<QuoteVote> getLast5Votes() {
        List<QuoteVote> quoteList = quoteVoteRepository.findTop5ByOrderByCreatedAtDesc();
        return quoteList;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }
}
