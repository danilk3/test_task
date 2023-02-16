package com.example.test_task.repository;

import com.example.test_task.model.QuoteVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteVoteRepository extends JpaRepository<QuoteVote, Long> {

    List<QuoteVote> findTop5ByOrderByCreatedAtDesc();
}
