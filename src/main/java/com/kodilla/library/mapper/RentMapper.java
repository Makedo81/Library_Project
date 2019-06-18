package com.kodilla.library.mapper;

import com.kodilla.library.domain.RentDto;
import com.kodilla.library.entity.Book;
import com.kodilla.library.entity.Rent;
import com.kodilla.library.entity.User;
import com.kodilla.library.repository.BookDao;
import com.kodilla.library.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RentMapper {

    @Autowired
    UserDao userDao;
    @Autowired
    BookDao bookDao;

    public Rent mapToRent(RentDto rentDto) {
        Book book = bookDao.findById(rentDto.getBook_id());
        User user = userDao.findById(rentDto.getUser_id());
        return new Rent(
                user,
                book);
    }

    public RentDto mapToRent(Rent rent) {
        Book book = bookDao.findById(rent.getBook().getId());
        User user = userDao.findById(rent.getUser().getId());
        return new RentDto(
                user.getId(),
                book.getId());
    }
}
