package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookApiController {
    private final BookRepository bookRepository;

    public BookApiController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getById(@PathVariable("id") Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Book add(@RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        bookRepository.deleteById(id);
    }

}