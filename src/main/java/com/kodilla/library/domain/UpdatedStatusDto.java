package com.kodilla.library.domain;

import com.kodilla.library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedStatusDto {

    Book id;
    Book status;
}
