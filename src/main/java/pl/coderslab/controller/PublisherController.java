package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.PublisherDao;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.PublisherRepository;

@Controller
public class PublisherController {

    private final PublisherDao publisherDao;
    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherDao publisherDao, PublisherRepository publisherRepository) {
        this.publisherDao = publisherDao;
        this.publisherRepository = publisherRepository;
    }


    @GetMapping("/publisher/by-nip")
    @ResponseBody
    public String getByNip() {
        Publisher publisher = publisherRepository.findByNip("1234");
        return publisher != null ? publisher.getName() : "brak wydawcy";
    }

    @GetMapping("/publisher/by-regon")
    @ResponseBody
    public String getByRegon() {
        Publisher publisher = publisherRepository.findByRegon("2222");
        return publisher != null ? publisher.getName() : "brak wydawcy";
    }


    @RequestMapping("/publisher/add")
    @ResponseBody
    public String hello() {
        Publisher publisher = new Publisher();
        publisher.setName("Ala");
        publisherDao.save(publisher);
        return "id: " + publisher.getId();
    }

    @RequestMapping("/publisher/get/{id}")
    @ResponseBody
    public String get(@PathVariable("id") int id) {
        Publisher publisher = publisherDao.findById(id);
        return publisher.toString();
    }

    @RequestMapping("/publisher/update/{id}/{name}")
    @ResponseBody
    public String update(@PathVariable("id") int id, @PathVariable("name") String name) {
        Publisher publisher = publisherDao.findById(id);
        publisher.setName(name);
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @RequestMapping("/publisher/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") int id) {
        Publisher publisher = publisherDao.findById(id);
        publisherDao.delete(publisher);
        return "deleted";
    }
}
