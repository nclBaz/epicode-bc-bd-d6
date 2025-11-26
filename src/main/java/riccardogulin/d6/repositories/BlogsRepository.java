package riccardogulin.d6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import riccardogulin.d6.entities.Blog;

import java.util.List;
import java.util.UUID;

@Repository
public interface BlogsRepository extends JpaRepository<Blog, UUID> {
	// ******************************* JPQL QUERIES ****************************************
	@Query("SELECT b FROM Blog b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	List<Blog> findBlogsByTitle(String keyword);

	// **************************** DERIVED QUERIES ***************************************
	List<Blog> findByTitleContainingIgnoreCase(String title);

	List<Blog> findByCategoriesNameIn(List<String> categories);

	// **************************** NATIVE QUERIES *****************************************
	@Query(value = "SELECT * FROM blogs", nativeQuery = true)
	List<Blog> getAll();
}
