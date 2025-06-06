package com.example.library_service.Services;


import com.example.library_service.Entity.Book;

import java.util.List;

public interface BookService {
    Book save(Book bookDto);

    List<Book> findAllPage(int pageNum, int pageSize);
}
