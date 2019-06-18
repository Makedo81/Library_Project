package com.kodilla.library.service;

import com.kodilla.library.domain.UserDto;
import com.kodilla.library.entity.User;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    UserMapper userMapper;

    public User save(User user) {
        return userDao.save(user);
    }

    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    public UserDto getUser(Long id) {
        User user = userDao.findById(id);
        return userMapper.mapToUserDto(user);
    }

    public List<User> findUserByLastname(String lastname) {
        List<User> users = userDao.findByLastname(lastname);
        return users;
    }
}
