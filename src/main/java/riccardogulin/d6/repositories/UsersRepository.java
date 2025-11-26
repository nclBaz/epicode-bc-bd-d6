package riccardogulin.d6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import riccardogulin.d6.entities.User;

import java.util.Optional;
import java.util.UUID;

@Repository // Ulteriore specializzazione di @Component
public interface UsersRepository extends JpaRepository<User, UUID> {
	// Nelle parentesi angolari devo indicare <Classe dell' Entità di riferimento, Tipo dell'id di tale Entità>
	// Estendendo JpaRepository ottengo già un notevole numero di metodi CRUD (e non solo) già pronti all'uso
	// Eventualmente potrò aggiungere ulteriori metodi per aggiungere funzionalità custom

	@Query("SELECT COUNT(b) FROM Blog b WHERE b.author.id = :userId")
	Long countBlogsByUser(UUID userId);

	@Query("SELECT COUNT(b) FROM Blog b WHERE b.author = :user")
	Long countBlogsByUser(User user);

	// ********************* DERIVED QUERIES **********************
	Optional<User> findByEmail(String email); // Equivale a SELECT * FROM users WHERE email = :email
}
