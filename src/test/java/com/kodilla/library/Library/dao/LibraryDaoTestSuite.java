package com.kodilla.library.Library.dao;

import com.kodilla.library.entity.Book;
import com.kodilla.library.entity.Rent;
import com.kodilla.library.entity.Title;
import com.kodilla.library.entity.User;
import com.kodilla.library.repository.BookDao;
import com.kodilla.library.repository.RentDao;
import com.kodilla.library.repository.TitleDao;
import com.kodilla.library.repository.UserDao;
import com.kodilla.library.service.RentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryDaoTestSuite {

    @Autowired
    RentDao rentDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    TitleDao titleDao;
    @Autowired
    UserDao userDao;
    @Autowired
    RentService rentService;

    @Test
    public void testLibraryDaoSave() {

        User anne = new User("Anne","Kirbach",new Date());
        User dominika = new User("Dominika","Stankiewicz",new Date());
        Title title = new Title("Nowy bookTitle","Jan Kowalski",1956);
        Book book1 = new Book(title);
        Book book2 = new Book(title);

        List<Book> books = new ArrayList<>();
        title.getBooks().add(book1);
        title.getBooks().add(book2);

        title.setBooks(books);
        book1.setTitle(title);
        book2.setTitle(title);

        List<Rent>rents = new ArrayList<>();
        book1.setRents(rents);
        book2.setRents(rents);

        Rent rent = new Rent();
        rent.setBook(book2);
        rent.setUser(anne);
        rent.setUser(dominika);

        titleDao.save(title);
        Long idTitle = title.getId();
        bookDao.save(book1);
        Long idBook = book1.getId();
        bookDao.save(book2);
        Long idBook2 = book2.getId();
        userDao.save(anne);
        Long id1 = anne.getId();
        userDao.save(dominika);
        Long id2 = anne.getId();
        rentDao.save(rent);
        Long idRent = rent.getId();

        Assert.assertEquals(1L,(long)idRent);

        rentDao.deleteById(idRent);
        userDao.deleteById(id2);
        userDao.deleteById(id1);
        bookDao.deleteById(idBook2);
        bookDao.deleteById(idBook);

    }
}
