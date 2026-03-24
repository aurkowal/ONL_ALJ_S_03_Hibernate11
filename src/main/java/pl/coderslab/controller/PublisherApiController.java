package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.PublisherRepository;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherApiController {

    private final PublisherRepository publisherRepository;

    public PublisherApiController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }
    @GetMapping("/")
    public List<Publisher> getAll() {
        return publisherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publisher getById(@PathVariable("id") Long id) {
        return publisherRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Publisher add(@RequestBody Publisher publisher) {
        publisherRepository.save(publisher);
        return publisher;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        publisherRepository.deleteById(id);
    }


}
