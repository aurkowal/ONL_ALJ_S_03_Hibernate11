package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

    List<Book> findAllByAuthors(Author author);

    List<Book> findAllByPublisher(Publisher publisher);

    Book findFirstByCategoryIdOrderByTitleAsc(Long categoryId);

    @Query("select b from Book b where b.title =?1")
    List<Book> findAllByTitleByQuery(String title);

    @Query("select b from Book b where b.category =?1")
    List<Book> findAllByCategoryQuery(Category category);

    @Query("select b from Book b WHERE b.rating >= ?1 AND b.rating <= ?2")
    List<Book> findAllByRatingBetween3and5(int rating1, int rating2);

    @Query("select b from Book b WHERE b.publisher =?1")
    List<Book> findAllByPublisherQuery(Publisher publisher);

    @Query("select b from Book b WHERE b.category.name = ?1 ORDER BY b.title ASC LIMIT 1")
    Book findFirstByCategoryIdOrderByTitleQuery(String name);


}
