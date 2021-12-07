package com.tledu.mapper;

import com.tledu.model.Book;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface BookMapper {
    List<Book> getBookList(Map<String, Object> map);

    void deleteBookById(Integer id);

    Book getBookById(Integer id);

    void addBook(Book book);

    void updateBook(Book book);
    @Select(" select count(*) from t_book  ")
    Integer getListCount();

    List<Book> getUserList(Map<String, Object> map);
}
