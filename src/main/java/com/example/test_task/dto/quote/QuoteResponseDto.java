package com.example.test_task.dto.quote;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuoteResponseDto {

    @JsonInclude(Include.NON_NULL)
    private String content;

    @JsonInclude(Include.NON_NULL)
    private Integer score;

    @JsonInclude(Include.NON_NULL)
    private String creatorEmail;

    @JsonInclude(Include.NON_NULL)
    private String status;
}
