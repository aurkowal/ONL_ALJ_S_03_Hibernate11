package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Book;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Publisher;

@Controller
public class BookController {
    private final BookDao bookDao;
    private final PublisherDao publisherDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
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


}