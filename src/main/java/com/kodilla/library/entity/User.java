package com.kodilla.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NamedQuery(
        name = "User.findByLastname",
        query = "FROM User WHERE lastname LIKE :LASTNAME "
)

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column(name = "FIRSTNAME")
    private String firstname;

    @Column(name = "LASTNAME")
    private String lastname;

    @Column(name = "ACCOUNT_CREATED")
    private Date accountCreated;
    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    List<Rent> rents = new ArrayList<>();

    public User(String firstname, String lastname, Date accountCreated) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountCreated = new Date();
    }
    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountCreated = new Date();
    }

    public User(Long id,String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.accountCreated = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    public Long getId() {
        return id;
    }

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    public List<Rent> getRents() {
        return rents;
    }

    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    @Column(name = "ACCOUNT_CREATED")
    public Date getAccountCreated() {
        return accountCreated;
    }

}
