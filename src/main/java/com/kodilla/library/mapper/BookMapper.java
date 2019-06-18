package com.kodilla.library.mapper;

import com.kodilla.library.domain.*;
import com.kodilla.library.entity.Book;
import com.kodilla.library.entity.Title;
import com.kodilla.library.repository.BookDao;
import com.kodilla.library.repository.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    @Autowired
    TitleDao titleDao;
    @Autowired
    BookDao bookDao;

        public BookDto mapToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getStatus(),
                book.getTitle());
    }

    public Book mapToBookWithTitleId(BookWithTitleIdDto bookWithTitleIdDto) {
        Title title = titleDao.findById(bookWithTitleIdDto.getTitle_id());
        System.out.println(title);
        return new Book(bookWithTitleIdDto.getStatus(),title);
    }

    public BookAvailableDto mapToTitleBookDto(Book book) {
        return new BookAvailableDto(
                book.getStatus(),
                book.getTitle().getTitle(),
                book.getTitle().getYear_published());
    }

    public List<BookAvailableDto> mapToBookAvaialbleDtoList(List<Book> bookList) {
        return bookList.stream()
                .map(this::mapToTitleBookDto)
                .collect(Collectors.toList());
        }
    }
