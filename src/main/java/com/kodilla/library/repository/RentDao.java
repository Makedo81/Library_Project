package com.kodilla.library.repository;

import com.kodilla.library.entity.Rent;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RentDao extends CrudRepository <Rent,Integer> {

    List<Rent> findAll();

    void deleteById(Long id);

    @Query
    List<Rent> findRentByBookId(@Param("BOOKID") Long bookId);

}
