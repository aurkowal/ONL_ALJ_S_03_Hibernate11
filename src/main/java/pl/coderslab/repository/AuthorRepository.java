package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.entity.Author;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findByEmail(String email);

    Author findByPesel(String pesel);

    List<Author> findAllByLastName(String lastName);

    @Query("select a FROM Author a WHERE a.email LIKE ?1%")
    List<Author> findAllByEmailContainingQuery(String param);

    @Query("select a FROM Author a WHERE a.pesel LIKE ?1%")
    List<Author> findAllByPeselContainingQuery(String pesel);
}