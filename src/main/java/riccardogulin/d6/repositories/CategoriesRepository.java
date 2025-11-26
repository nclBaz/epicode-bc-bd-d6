package riccardogulin.d6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardogulin.d6.entities.Category;

import java.util.UUID;

@Repository
public interface CategoriesRepository extends JpaRepository<Category, UUID> {
}
