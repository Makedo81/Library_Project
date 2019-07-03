package com.kodilla.library.controller;

import com.kodilla.library.domain.TitleDto;
import com.kodilla.library.mapper.TitleMapper;
import com.kodilla.library.service.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("v2/title")
public class TitleController {

    @Autowired
    private TitleService titleService;
    @Autowired
    private TitleMapper titleMapper;

    @RequestMapping(method = RequestMethod.POST,value = "addTitle",consumes = APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        titleService.save(titleMapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteTitle")
    public void deleteTitle(@RequestParam Long titleId) {
        titleService.deleteTitle(titleId);
    }

}
