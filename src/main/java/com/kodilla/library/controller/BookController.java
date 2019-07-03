package com.kodilla.library.controller;

import com.kodilla.library.domain.BookAvailableDto;
import com.kodilla.library.domain.BookDto;
import com.kodilla.library.domain.BookWithTitleIdDto;
import com.kodilla.library.domain.TitleDto;
import com.kodilla.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v2/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookWithTitleIdDto bookWithTitleIdDto) {
        bookService.create(bookWithTitleIdDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateStatus", consumes = APPLICATION_JSON_VALUE)
    public void updateStatus(@RequestBody BookDto bookDto) {
        bookService.updateStatusToAvaialble(bookDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "getAvailableBooks", consumes = APPLICATION_JSON_VALUE)
    public List<BookAvailableDto> getAvailableBooks(@RequestBody TitleDto titleDto) {
        return bookService.checkAvailableBooks(titleDto); }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) {
         bookService.deleteBook(bookId);
    }
}
