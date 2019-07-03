package com.kodilla.library.service;

import com.kodilla.library.domain.*;
import com.kodilla.library.entity.Book;
import com.kodilla.library.entity.Title;
import com.kodilla.library.exceptions.BookNotAvailableException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.repository.BookDao;
import com.kodilla.library.repository.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    public List<BookAvailableDto> availableTitlesList = new ArrayList<>();
    public static final String AVAILABLE = "available";
    public static final String LOST = "lost";
    @Autowired
    BookDao bookDao;
    @Autowired
    TitleDao titleDao;
    @Autowired
    BookMapper bookMapper;

    public void create(BookWithTitleIdDto bookWithTitleIdDto) {
        Book book1 = bookMapper.mapToBookWithTitleId(bookWithTitleIdDto);
        bookDao.save(book1);
    }

    public void deleteBook(Long bookId) {
        bookDao.deleteById(bookId);
    }

    public List<BookAvailableDto> checkAvailableBooks(TitleDto titleDto) {
        List<Title> titleIdToCheck = titleDao.findTitleId(titleDto.getTitle());
        for (int i = 0; i <= titleIdToCheck.size(); i++) {
            List<Book> booksAvaialble = bookDao.findAllById(titleIdToCheck.get(i).getId());
            for (i = 0; i <= booksAvaialble.size(); i++) {
                List<BookAvailableDto>list = bookMapper.mapToBookAvailableDtoList(booksAvaialble);
                availableTitlesList = list.stream()
                        .filter(b->b.getStatus().equals(AVAILABLE))
                        .collect(Collectors.toList());
            }
        }
        return availableTitlesList;
    }

    public boolean checkIfAvailable() throws BookNotAvailableException {
        if (availableTitlesList.size() > 0) {
            return true;
        } else {
            throw new BookNotAvailableException("Book with this title is not available. Choose another title");
        }
    }

    public String updateStatusToAvaialble(final BookDto bookDto) {
        Book book2 = bookDao.findById(bookDto.getId());
        if(book2.getStatus().equals(LOST)){
            book2.setStatus(AVAILABLE);
            bookDao.save(book2);
        }
        return "Status has been changed. Book is " + bookDto.getStatus();
    }
}
