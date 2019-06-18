package com.kodilla.library.mapper;

import com.kodilla.library.domain.TitleDto;
import com.kodilla.library.entity.Title;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto) {
        return  new Title(titleDto.getTitle(),titleDto.getAuthor(),titleDto.getYear_published());
    }

    public TitleDto mapToTitleDto(final Title title) {
        return  new TitleDto(title.getId(),title.getTitle(),title.getAuthor(),title.getYear_published());
    }

    public List<TitleDto> mapToBookDtoList(List<Title> bookList) {
        return bookList.stream()
                .map(this::mapToTitleDto)
                .collect(toList());
    }
}
