package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Category;
import pl.coderslab.entity.Publisher;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByRating(int rating);

    List<Book> findAllByTitle(String title);

    List<Book> findAllByCategory(Category category);

    List<Book> findAllByCategoryId(Long aLong);

//    List<Book> findAllByAuthors(List<Author> authors);

    List<Book> findAllByPublisher(Publisher publisher);

    Book findFirstByCategoryIdOrderByTitleAsc(Long categoryId);

}
