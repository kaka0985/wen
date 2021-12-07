package com.tledu.service.impl;

import com.tledu.mapper.BookMapper;
import com.tledu.model.Book;
import com.tledu.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> getBookList(Map<String, Object> map) {
        return bookMapper.getBookList(map);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookMapper.deleteBookById(id);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.getBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public List<Book> getUserList(Map<String, Object> map) {
        return bookMapper.getUserList(map);
    }

    @Override
    public Integer getListCount() {
        return bookMapper.getListCount();
    }
}
