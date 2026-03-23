package pl.coderslab.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.List;


@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;


    public void save(Book book) {
        entityManager.persist(book);
    }

    public Book find(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void update(Book book) {
        if (book == null || book.getId() == null) {
            throw new IllegalArgumentException("Do aktualizacji wymagany jest Book z ustawionym ID");
        }
        entityManager.merge(book);
    }

    public List<Book> findAll() {
        Query query = entityManager.createQuery("select b from Book  b");
        List<Book> resultList = query.getResultList();
        return resultList;
    }

    public List<Book> findAll2() {
        return entityManager
                .createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    public List<Book> findAllByRating(int rating) {
        return entityManager
                .createQuery("select b from Book b where b.rating=:rating", Book.class)
                .setParameter("rating", rating)
                .getResultList();
    }


    public List<Book> findAllWithPublisher() {
        Query query = entityManager.createQuery("select b from Book b JOIN b.publisher");
        List<Book> resultList = query.getResultList();
        return resultList;
    }

    public List<Book> findAllWithThisPublisher(Publisher publisher) {
        Query query = entityManager.createQuery("select b from Book b WHERE b.publisher=:publisher");
        query.setParameter("publisher", publisher);
        List<Book> resultList = query.getResultList();
        return resultList;
    }

    public List<Book> findAllWithThisAuthor(Author author) {
        Query query = entityManager.createQuery("select distinct b from Book b JOIN FETCH b.authors a WHERE :author member of b.authors");
        query.setParameter("author", author);
        List<Book> resultList = query.getResultList();
        return resultList;
    }

    public void delete(Book book) {
        if (book == null) return;
        Book managed = entityManager.contains(book) ? book : entityManager.merge(book);
        entityManager.remove(managed);
    }


}
