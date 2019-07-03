package com.kodilla.library.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Book.findAllById",
        query ="FROM Book WHERE title_id = :ID"
)
@Setter
@Entity
@NoArgsConstructor
@Table(name = "BOOKS")
public class Book {

    private Long id;
    private String status;
    private Title title;
    List<Rent> rents = new ArrayList<>();

    public Book(Title title) {
        this.title = title;
    }

    public Book( String status,Title title) {
        this.status = status;
        this.title = title;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_ID", unique = true)
    public Long getId() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    public Title getTitle() {
            return title;
            }

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "book",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Rent> getRents() {
        return rents;
    }

    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

}
