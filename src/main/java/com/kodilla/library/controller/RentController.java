package com.kodilla.library.controller;

import com.kodilla.library.domain.RentDto;
import com.kodilla.library.exceptions.BookNotAvailableException;
import com.kodilla.library.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v2/rent")
public class RentController {

    @Autowired
    private RentService rentService;

    @RequestMapping(method = RequestMethod.POST, value = "rent", consumes = APPLICATION_JSON_VALUE)
    public void rentBook(@RequestBody RentDto rentDto) throws BookNotAvailableException {
        rentService.renting(rentDto); }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook", consumes = APPLICATION_JSON_VALUE)
    public void returnBook(@RequestBody RentDto rentDto) {
        rentService.returningBook(rentDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "delete")
    public void delete(@RequestParam Long id) {
        rentService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateStatus", consumes = APPLICATION_JSON_VALUE)
    public String updateStatus(@RequestBody RentDto rentDto) {
        return  rentService.updateStatusIfPaid(rentDto);
    }
}
