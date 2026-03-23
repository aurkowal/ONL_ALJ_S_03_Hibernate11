package pl.coderslab.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Author;
import pl.coderslab.dao.AuthorDao;

@Controller
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao =  authorDao;
    }

    @RequestMapping("/author/add")
    @ResponseBody
    public String hello() {
        Author author = new Author();
        author.setFirstName("Adam");
        author.setLastName("Mickiewicz");
        authorDao.save(author);
        return "Id autora:"
                + author.getId();
    }

    @RequestMapping("/author/get/{id}")
    @ResponseBody
    public String get(@PathVariable("id") long id) {
        Author author = authorDao.findById(id);
        return author.toString();
    }

    @RequestMapping("/author/update/{id}/{firstName}/{lastName}")
    @ResponseBody
    public String update(@PathVariable("id") long id, @PathVariable("firstName") String firstName, @PathVariable("lastName")String lastName) {
        Author author = authorDao.findById(id);
        author.setLastName(lastName);
        author.setFirstName(firstName);
        authorDao.update(author);
        return author.toString();

    }

    @RequestMapping("/author/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        Author author = authorDao.findById(id);
        authorDao.delete(author);
        return "deleted";
    }


}