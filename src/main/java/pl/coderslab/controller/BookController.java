package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.AuthorDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Category;
import pl.coderslab.repository.BookRepository;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Book;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao, BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }




    @GetMapping("/book/get-allby-category")
    @ResponseBody
    public String getAllByCategory() {
        return bookRepository.findAllByCategoryId(2L)
                .stream().map(Book::getTitle)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/book/get-allby-category-id")
    @ResponseBody
    public String getAllByCategoryId() {

        return bookRepository.findAllByCategory(categoryRepository.findById(1L).get())
                .stream().map(Book::getTitle)
                .collect(Collectors.joining(", "));
    }

    @GetMapping("/book/get-allby-title")
    @ResponseBody
    public String getAllByTitle() {

        return bookRepository.findAllByTitle("Harry Potter").stream().map(Book::getTitle).collect(Collectors.joining(", "));
    }

    @GetMapping("/book/get-allby-rating")
    @ResponseBody
    public String getAllByRating() {

        return bookRepository.findAllByRating(10).stream().map(Book::getTitle).collect(Collectors.joining(", "));
    }

    //AUTOR???


    @GetMapping("/book/get-allby-publisher")
    @ResponseBody
    public String getAllByPublisher() {
        return bookRepository.findAllByPublisher(publisherDao.findById(7)).stream().map(Book::getTitle).collect(Collectors.joining(", "));
    }

    @GetMapping("/book/first-by-category")
    @ResponseBody
    public String firstByCategory() {
        Book book = bookRepository.findFirstByCategoryIdOrderByTitleAsc(1L);
        return book != null ? book.getTitle() : "brak książek";
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


    @GetMapping("/book/add-with-author")
    @ResponseBody
    public String addWithAuthor() {
        Book book = new Book();
        List<Author> authors = new ArrayList<>();
        Author author1 = authorDao.findById(5);
        Author author2 = authorDao.findById(6);
        authors.add(author1);
        authors.add(author2);
        book.setTitle("Hobbit");
        book.setDescription("Bilbo Baggins");
        book.setRating(10);
        book.setAuthors(authors);
        bookDao.save(book);
        return "Dodano ksiazke o id: " + book.getId();
    }

    @RequestMapping("/book/get-with-publisher")
    @ResponseBody
    public String getAllWithPublisher() {
        List<Book> books = bookDao.findAllWithPublisher();
        return books.toString();
    }

    @RequestMapping("/book/get-with-this-publisher")
    @ResponseBody
    public String getAllWithThisPublisher() {
        List<Book> books = bookDao.findAllWithThisPublisher(publisherDao.findById(7));
        return books.toString();
    }

    @RequestMapping("/book/get-with-this-author")
    @ResponseBody
    public String getAllWithThisAuthor() {
        List<Book> books = bookDao.findAllWithThisAuthor(authorDao.findById(5));
        return books.toString();
    }

}