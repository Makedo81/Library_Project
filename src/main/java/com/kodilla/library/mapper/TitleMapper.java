package com.kodilla.library.mapper;

import com.kodilla.library.domain.TitleDto;
import com.kodilla.library.entity.Title;
import org.springframework.stereotype.Component;

@Component
public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto) {
        return  new Title(titleDto.getTitle(),titleDto.getAuthor(),titleDto.getYear_published());
    }

    public TitleDto mapToTitleDto(final Title title) {
        return  new TitleDto(title.getId(),title.getTitle(),title.getAuthor(),title.getYear_published());
    }
}
