package com.kodilla.library.service;

import com.kodilla.library.domain.RentDto;
import com.kodilla.library.domain.TitleDto;
import com.kodilla.library.entity.Book;
import com.kodilla.library.entity.Rent;
import com.kodilla.library.entity.Title;
import com.kodilla.library.entity.User;
import com.kodilla.library.exceptions.BookNotAvailableException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.repository.BookDao;
import com.kodilla.library.repository.RentDao;
import com.kodilla.library.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class RentService {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    RentDao rentDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    TitleMapper titleMapper;
    @Autowired
    UserDao userDao;
    @Autowired
    RentMapper rentMapper;
    @Autowired
    BookMapper bookMapper;

    public static final String AVAILABLE = "available";
    public static final String RENTED = "rented";
    public static final String LOST = "lost";
    boolean paid = true;

    public void deleteRent(Long id) {
        rentDao.deleteById(id);
    }

    public void renting(final RentDto rentDto) throws BookNotAvailableException {
        Book book = bookDao.findById(rentDto.getBook_id());
        Title titleToCheck = bookDao.findById(book.getId()).getTitle();
        TitleDto title = titleMapper.mapToTitleDto(titleToCheck);
        Rent rent = new Rent();
        User user = userDao.findById(rentDto.getUser_id());
        List<User> users = userService.findUserByLastname(user.getLastname());
        List<User> checkedUser = users.stream()
                .filter(u -> u.getFirstname().equals(user.getFirstname()))
                .collect(toList());
        bookService.checkAvailableBooks(title);
        boolean avaialble = bookService.checkIfAvailable();
        if (avaialble) {
            rent.setUser(checkedUser.get(0));
            rent.setBook(book);
            rent.setRentedDate(new Date());
            rent.setReturnedDate(null);
            rentDao.save(rent);
            book.setStatus(RENTED);
            bookDao.save(book);
        }
    }
    public void returningBook (final RentDto rentDto){
        List<Rent> rents = rentDao.findRentByBookId(rentDto.getBook_id());
        User user = userDao.findById(rentDto.getUser_id());
        List<Rent> checkedRent = rents.stream()
                .filter(c -> c.getUser().getLastname().equals(user.getLastname()))
                .filter(c -> c.getUser().getFirstname().equals(user.getFirstname()))
                .filter(a -> a.getUser().getAccountCreated().equals((user.getAccountCreated())))
                .collect(toList());
        if (checkedRent.get(0).getRentedDate() != null && checkedRent.get(0).returnedDate == null
                && checkedRent.get(0).getBook().getStatus().equals(RENTED)) {
            checkedRent.get(0).setReturnedDate(new Date());
            rentDao.save(rents.get(0));
            rents.get(0).getBook().setStatus(AVAILABLE);
            bookDao.save(rents.get(0).getBook());
        } else System.out.println("Something is wrong");
    }

    public String updateStatusIfPaid(final RentDto rentDto) {
        List<Rent> rents = rentDao.findRentByBookId(rentDto.getBook_id());
        Date day = rents.get(0).getRentedDate();
        LocalDate date = day.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        List<Rent> rentList = rents.stream()
                .filter(r -> r.getReturnedDate() == null)
                .collect(toList());
        Book book3 = rents.get(0).getBook();
          if (date.equals(LocalDate.now())) {  //w celu szybkiego sprawdzenia kodu
              //  if (date.compareTo(LocalDate.now().plusDays(30)) > 31) {  // prawidlowy warunek
              if (book3.getStatus().equals(RENTED)) {
                  System.out.println(1);
                  rentList.get(0).setReturnedDate(new Date());
                  book3.setStatus(LOST);
                  bookDao.save(book3);
              } if(paid) {
                  System.out.println(" Book is paid and status set to available ");
              bookService.updateStatusToAvaialble(bookMapper.mapToBookDto(book3));}
              else paid = false;
          } return " Status has been changed.Book is available ";
    }
}



