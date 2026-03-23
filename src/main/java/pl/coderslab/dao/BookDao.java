package pl.coderslab.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Book;


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

    public void delete(Book book) {
        if (book == null) return;
        Book managed = entityManager.contains(book) ? book : entityManager.merge(book);
        entityManager.remove(managed);
    }


}
