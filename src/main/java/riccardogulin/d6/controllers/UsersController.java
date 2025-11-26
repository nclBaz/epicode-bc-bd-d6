package riccardogulin.d6.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import riccardogulin.d6.dto.users.NewUserDTO;
import riccardogulin.d6.dto.users.NewUserRespDTO;
import riccardogulin.d6.entities.Blog;
import riccardogulin.d6.entities.User;
import riccardogulin.d6.exceptions.ValidationException;
import riccardogulin.d6.repositories.BlogsRepository;
import riccardogulin.d6.services.UsersService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private BlogsRepository blogsRepository; // N.B. Mai utilizzare direttamente le repositories, si passa sempre dai services
	// questa è utilizzata qua solo per scopi didattici per la realizzazione dell'endpoint di test sottostante

	@GetMapping("/{keyword}/test")
	public List<Blog> test(@PathVariable("keyword") String keyword) {
		return blogsRepository.findByTitleContainingIgnoreCase(keyword);
	}

	@GetMapping
	public List<User> getUsers() {
		return this.usersService.getUsers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public NewUserRespDTO createUser(@RequestBody @Validated NewUserDTO body, BindingResult validationResult) {

		if (validationResult.hasErrors()) {

			throw new ValidationException(validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
		}
		return this.usersService.save(body);
	}

	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") UUID userId) {
		return this.usersService.findById(userId);
	}

	@PutMapping("/{userId}")
	public User getUserByIdAndUpdate(@PathVariable("userId") UUID userId, @RequestBody NewUserDTO body) {
		return this.usersService.findByIdAndUpdate(userId, body);
	}

	@DeleteMapping("/{userId}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // COME ESEMPIO DI CASCADE DELETE
	public void getUserByIdAndDelete(@PathVariable("userId") UUID userId) {
		this.usersService.findByIdAndDelete(userId);
	}

	@DeleteMapping("/{userId}/settings")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteSettingsFromUser(@PathVariable("userId") UUID userId) {
		this.usersService.removeSettingsFromUser(userId);
	}

}
