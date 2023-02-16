package com.example.test_task.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "quote_votes")
@Data
public class QuoteVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User voter;

    @ManyToOne
    @JoinColumn(name = "quote_id")
    private Quote quote;

    private LocalDate createdAt;
}
