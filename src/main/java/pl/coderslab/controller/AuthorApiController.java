package pl.coderslab.controller;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.repository.AuthorRepository;

import java.util.List;

@RestController
@RequestMapping("/api/author")
public class AuthorApiController {

    private final AuthorRepository authorRepository;

    public AuthorApiController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("/")
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Author getById(@PathVariable("id") Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @PostMapping("/")
    public Author add(@RequestBody Author author) {
        authorRepository.save(author);
        return author;
    }

    @PutMapping("/{id}")
    public Author update(@PathVariable("id") Long id, @RequestBody Author author) {
        Author author1 = authorRepository.findById(id).orElse(null);
        if (author1 != null) {
            author1.setFirstName(author.getFirstName());
            author1.setLastName(author.getLastName());
            author1.setEmail(author.getEmail());
            author1.setPesel(author.getPesel());
            authorRepository.save(author1);
        }

        return author;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        authorRepository.deleteById(id);
    }


}
