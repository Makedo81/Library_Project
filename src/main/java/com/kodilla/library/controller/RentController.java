package com.kodilla.library.controller;

import com.kodilla.library.domain.*;
import com.kodilla.library.exceptions.BookNotAvailableException;
import com.kodilla.library.mapper.BookMapper;
import com.kodilla.library.mapper.RentMapper;
import com.kodilla.library.mapper.UserMapper;
import com.kodilla.library.service.BookService;
import com.kodilla.library.service.RentService;
import com.kodilla.library.service.TitleService;
import com.kodilla.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v2/rent")
public class RentController {

    @Autowired
    RentService rentService;
    @Autowired
    BookService bookService;
    @Autowired
    TitleService titleService;
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    BookMapper bookMapper;
    @Autowired
    RentMapper rentMapper;


    @RequestMapping(method = RequestMethod.POST, value = "rentBook", consumes = APPLICATION_JSON_VALUE)
    public void rentBook(@RequestBody RentDto rentDto) throws BookNotAvailableException {
        rentService.renting(rentDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook", consumes = APPLICATION_JSON_VALUE)
    public void returnBook(@RequestBody RentDto rentDto) {
        rentService.returningBook(rentDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteRent")
    public void deleteRent(@RequestParam Long id) {
        rentService.deleteRent(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateStatusIfPaid", consumes = APPLICATION_JSON_VALUE)
    public String updateStatusIfPaid(@RequestBody RentDto rentDto) {
        return  rentService.updateStatusIfPaid(rentDto);
    }
}
