package br.com.erudio.mapper.custom;

import br.com.erudio.data.dto.v1.BookDTO;
import br.com.erudio.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookDTO convertEntityToDto(Book dto) {
        BookDTO entity = new BookDTO();
        entity.setId(dto.getId());
        entity.setAuthor(dto.getAuthor());
        entity.setLaunchDate(dto.getLaunchDate());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());
        return entity;
    }

    public Book convertDtoToEntity(BookDTO dto) {
        Book entity = new Book();
        entity.setId(dto.getId());
        entity.setAuthor(dto.getAuthor());
        entity.setLaunchDate(dto.getLaunchDate());
        entity.setPrice(dto.getPrice());
        entity.setTitle(dto.getTitle());
        return entity;
    }
}
