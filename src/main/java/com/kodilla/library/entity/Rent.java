package com.kodilla.library.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.Date;
@NamedQuery(
        name = "Rent.findRentByBookId",
        query ="FROM Rent WHERE book_id = :BOOKID"
)
@Setter
@Entity
@NoArgsConstructor
@Table(name = "RENTS")
public class Rent {

    private Long id;
    public Date rentedDate;
    public  Date returnedDate;
    private User user;
    private Book book;

    public Rent(Date rentedDate, Date returnedDate) {
        this.rentedDate = null;
        this.returnedDate = null;
    }
    public Rent(User user,Book book,Date rentedDate, Date returnedDate) {
        this.user= user;
        this.book = book;
        this.rentedDate = null;
        this.returnedDate = null;
    }
    public Rent(User user,Book book) {
        this.user= user;
        this.book = book;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @NotNull
    @Column(name = "RENT_ID", unique = true)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "BOOK_ID")
    public Book getBook() {
        return book;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    @Column(name = "RENT_DATE")
    public Date getRentedDate() {
        return rentedDate;
    }

    @Column(name = "RETURN_DATE")
    public Date getReturnedDate() {
        return returnedDate;
    }
}
