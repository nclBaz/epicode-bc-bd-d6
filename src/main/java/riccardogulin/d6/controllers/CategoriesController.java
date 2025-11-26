package riccardogulin.d6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardogulin.d6.dto.categories.NewCategoryDTO;
import riccardogulin.d6.entities.Category;
import riccardogulin.d6.services.CategoriesService;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoriesController {
	@Autowired
	private CategoriesService categoriesService;

	@GetMapping
	public List<Category> getUsers() {
		return this.categoriesService.findAll();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Category createCategory(@RequestBody NewCategoryDTO body) {
		return this.categoriesService.save(body.name());
	}

}
