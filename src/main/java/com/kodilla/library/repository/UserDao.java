package com.kodilla.library.repository;

import com.kodilla.library.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User,Long> {

    @Override
    User save(User user);

    void deleteById(Long id);

    User findById(Long id);

    @Query
    List<User> findByLastname(@Param("LASTNAME") String lastname);
}
