package com.kodilla.library.mapper;

import com.kodilla.library.domain.UserDto;
import com.kodilla.library.entity.User;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class UserMapper {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public User mapToUser(final UserDto userDto) {
         return new User(
                 userDto.getFirstname(),
                 userDto.getLastname());
               //  dateFormat.parse(userDto.getAccountCreated()));
    }

    public User mapToUserWithId(final UserDto userDto)  {
        return new User(
                userDto.getId(),
                userDto.getFirstname(),
                userDto.getLastname());
        //  dateFormat.parse(userDto.getAccountCreated()));
    }

    public UserDto mapToUserDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                dateFormat.format(user.getAccountCreated()));
    }
}
