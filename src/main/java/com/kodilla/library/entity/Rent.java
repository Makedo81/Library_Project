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
    public Date returnedDate;
    private User user;
    private Book book;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
