package com.endava.tmd.endavatmdbookproject.controllers;

import com.endava.tmd.endavatmdbookproject.models.Book;
import com.endava.tmd.endavatmdbookproject.repositories.BookRepository;
import com.endava.tmd.endavatmdbookproject.services.BookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Book> list(){
        return bookService.list();
    }

    @RequestMapping(path = "/get",
            params = "id",
            method = RequestMethod.GET)
    public Book get(@RequestParam Long id){
        return bookService.get(id);
    }

    @RequestMapping(path = "/create",
            method = RequestMethod.POST)
    public Object create(@RequestBody Book book){
        Object bookCreated = bookService.create(book);
        if(bookCreated == null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return bookCreated;
    }

    @RequestMapping(path = "/get/titleANDauthor",
            method = RequestMethod.GET)
    public Object getBookByTitleAndAuthor(@RequestParam("title") String title, @RequestParam("author") String author){
        if(bookService.getBookByTitleAndAuthor(title,author) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return bookService.getBookByTitleAndAuthor(title,author);
    }
/*    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id){
        bookRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Book update(@PathVariable Long id, @RequestBody Book book){
        Book existingBook = bookRepository.getReferenceById(id);
        BeanUtils.copyProperties(book, existingBook, "book_id");
        return bookRepository.saveAndFlush(existingBook);
    }*/
}
