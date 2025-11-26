package riccardogulin.d6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import riccardogulin.d6.dto.blogs.NewBlogDTO;
import riccardogulin.d6.entities.Blog;
import riccardogulin.d6.services.BlogsService;

import java.util.UUID;

@RestController
@RequestMapping("/blogs")
public class BlogsController {
	@Autowired
	private BlogsService blogsService;

	@GetMapping
	public Page<Blog> getBlogs(@RequestParam(defaultValue = "0") int page,
	                           @RequestParam(defaultValue = "10") int size,
	                           @RequestParam(defaultValue = "id") String sortBy) {
		return this.blogsService.findAll(page, size, sortBy);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Blog createUser(@RequestBody NewBlogDTO body) {
		return this.blogsService.createPost(body);
	}

	@GetMapping("/{blogId}")
	public Blog getUserById(@PathVariable("blogId") UUID blogId) {
		return this.blogsService.findById(blogId);
	}

}
