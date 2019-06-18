package com.kodilla.library.repository;

import com.kodilla.library.entity.Title;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TitleDao extends CrudRepository<Title,Integer> {

    @Override
    Title save(Title title);

    void deleteById(Long id);

    List<Title> findAll();

    Title findById(Long id);

    @Query
    List<Title> findTitleId(@Param("TITLE") String title );
}
