package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.dao.ArticleDao;
import pl.coderslab.entity.Article;

import java.time.LocalDate;

@Controller
public class ArticleController {
    private final ArticleDao articleDao;

    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @RequestMapping("/article/add")
    @ResponseBody
    public String addArticle() {
        Article article = new Article();
        article.setTitle("Nowa ERA");
        article.setContent("Spadające gwiazdy");
        article.setCreated(LocalDate.now());
        article.setUpdated(LocalDate.now());
        articleDao.save(article);
        return "Id artykułu " + article.getId();
    }

    @RequestMapping("/article/get/{id}")
    @ResponseBody
    public String get(@PathVariable("id") long id) {
        Article article = articleDao.findById(id);
            return article.toString();
    }

    @RequestMapping("/article/update/{id}/{title}/{content}")
    @ResponseBody
    public String update(@PathVariable("id") long id, @PathVariable("title") String title, @PathVariable("content")String content) {
        Article article = articleDao.findById(id);
        article.setTitle(title);
        article.setContent(content);
        article.setUpdated(LocalDate.now());
        return article.toString();

    }

    @RequestMapping("/article/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable("id") long id) {
        Article article = articleDao.findById(id);
        articleDao.delete(article);
        return "deleted";
    }

}
