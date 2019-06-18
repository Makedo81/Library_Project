package com.kodilla.library.repository;

import com.kodilla.library.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookDao extends CrudRepository<Book,Long> {

    @Override
    Book save(Book book);

    @Query(nativeQuery = true)
    List<Book> findAllById(@Param("ID") Long id );

    List<Book> findAll();

    void deleteById(Long id);

    Book findById(Long id);

}
