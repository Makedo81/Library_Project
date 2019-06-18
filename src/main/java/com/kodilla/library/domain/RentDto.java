package com.kodilla.library.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.library.entity.Book;
import com.kodilla.library.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RentDto {

    @JsonProperty("user_id")
    private Long user_id;
    @JsonProperty("book_id")
    private Long book_id;
}
