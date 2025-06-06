package com.example.library_service.Controllers;

import com.example.library_service.Entity.Book;
import com.example.library_service.Services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {
    final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody Book bookDto){
        bookDto.setCreatedAt(new Date());
        Book book = bookService.save(bookDto);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/getBooks")
    public ResponseEntity<?> getBooksPage(
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ){

        List<Book> bookList = bookService.findAllPage(pageNum, pageSize);

        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }
}
