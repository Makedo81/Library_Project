package com.kodilla.library.entity;

import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedQuery(
        name = "Title.findTitleId",
        query ="FROM Title WHERE title = :TITLE"
)
@Setter
@Entity
@NoArgsConstructor
@Table(name = "TITLES")
public class Title {

    private Long id;
    private String title;
    private String author;
    private int year_published;
    private List<Book> books = new ArrayList<>();

    public Title(String title, String author, int year_published) {
        this.title = title;
        this.author = author;
        this.year_published = year_published;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true)
    public Long getId() {
        return id;
    }

    @OneToMany(
    targetEntity = Book.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Book> getBooks() {
        return books;
    }

    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }


    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }


    @Column(name = "PUBLICATION_YEAR")
    public int getYear_published() {
        return year_published;
    }

}
