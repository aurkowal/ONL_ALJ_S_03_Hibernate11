package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Book;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.CategoryRepository;

import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookDao bookDao, PublisherDao publisherDao, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping("/book/test-repo-1")
    @ResponseBody
    public String test() {
        return bookRepository.findAllByCategoryId(2L)
                .stream().map(Book::getTitle)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/book/test-repo-2")
    @ResponseBody
    public String test2() {

        return bookRepository.findAllByCategory(categoryRepository.findById(1L).get())
                .stream().map(Book::getTitle)
                .collect(Collectors.joining(", "));
    }


    @GetMapping("/book/add-with-publisher")
    @ResponseBody
    public String addWithPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("Ala");
        publisherDao.save(publisher);

        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setDescription("Czara ognia");
        book.setRating(10);
        book.setPublisher(publisher);
        bookDao.save(book);
        return "Dodano ksiazke o id: " + book.getId();
    }

    @GetMapping("/book/add")
    @ResponseBody
    public String add() {
        Book book = new Book();
        book.setTitle("Harry Potter");
        book.setDescription("Czara ognia");
        book.setRating(10);
        bookDao.save(book);
        return "Dodano ksiazke o id: " + book.getId();
    }

    @RequestMapping("/book/get/{id}")
    @ResponseBody
    public String getBook(@PathVariable("id") long id) {
        Book book = bookDao.find(id);
        return book.toString();
    }

    @RequestMapping("/book/update/{id}/{title}/{description}")
    @ResponseBody
    public String updateBook(@PathVariable("id") long id, @PathVariable("title") String title, @PathVariable("description") String desc) {
        Book book = bookDao.find(id);
        book.setTitle(title);
        book.setDescription(desc);
        bookDao.update(book);
        return book.toString();

    }

    @RequestMapping("/book/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable("id") long id) {
        Book book = bookDao.find(id);
        bookDao.delete(book);
        return "usunieto ksiazke";
    }

    @RequestMapping("/book/by-rating/{rating}")
    @ResponseBody
    public String bookByRating(@PathVariable("rating") int rating) {
        return bookDao.findAllByRating(rating).stream()
                .map(Book::toString)
                .collect(Collectors.joining(", "));
    }


}