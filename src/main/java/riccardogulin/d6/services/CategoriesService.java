package riccardogulin.d6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.d6.entities.Category;
import riccardogulin.d6.exceptions.NotFoundException;
import riccardogulin.d6.repositories.CategoriesRepository;

import java.util.List;
import java.util.UUID;

@Service
public class CategoriesService {
	@Autowired
	private CategoriesRepository categoriesRepository;

	public Category save(String name) {
		return this.categoriesRepository.save(new Category(name));
	}

	public Category findById(UUID id) {
		return this.categoriesRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public List<Category> findAll() {
		return this.categoriesRepository.findAll();
	}
}
