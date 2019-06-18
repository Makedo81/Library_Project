package com.kodilla.library.service;

import com.kodilla.library.entity.Title;
import com.kodilla.library.repository.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleService {

    @Autowired
    TitleDao titleDao;

    public Title save(Title title) {
        return titleDao.save(title);
    }
    public void deleteTitle(Long id) {
        titleDao.deleteById(id);
    }
}
