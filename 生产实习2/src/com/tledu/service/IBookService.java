package com.tledu.service;

import com.tledu.model.Book;

import java.util.List;
import java.util.Map;

public interface IBookService {
    List<Book> getBookList(Map<String, Object> map);

    void deleteBookById(Integer id);

    Book getBookById(Integer id);

    void addBook(Book book);

    void updateBook(Book book);

    List<Book> getUserList(Map<String, Object> map);

    Integer getListCount();
}
