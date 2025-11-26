package riccardogulin.d6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import riccardogulin.d6.dto.blogs.NewBlogDTO;
import riccardogulin.d6.entities.Blog;
import riccardogulin.d6.entities.Category;
import riccardogulin.d6.entities.User;
import riccardogulin.d6.exceptions.NotFoundException;
import riccardogulin.d6.repositories.BlogsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BlogsService {
	@Autowired
	private BlogsRepository blogsRepository;
	@Autowired
	private UsersService usersService;

	public Blog createPost(NewBlogDTO body) {
		List<Category> categories = new ArrayList<>();
		User author = this.usersService.findById(body.authorId());
		Blog newPost = new Blog(body.title(), body.content(), author, categories);
		return blogsRepository.save(newPost);
	}


	public Blog findById(UUID blogId) {
		Blog found = this.blogsRepository.findById(blogId).orElseThrow(() -> new NotFoundException(blogId));
		return found;
	}


	public Page<Blog> findAll(int page, int size, String sortBy) {
		if (size > 50) size = 50;
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
		return this.blogsRepository.findAll(pageable);
	}


}
