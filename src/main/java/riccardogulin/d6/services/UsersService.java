package riccardogulin.d6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.d6.dto.users.NewUserDTO;
import riccardogulin.d6.dto.users.NewUserRespDTO;
import riccardogulin.d6.entities.User;
import riccardogulin.d6.entities.UserSettings;
import riccardogulin.d6.exceptions.NotFoundException;
import riccardogulin.d6.exceptions.ValidationException;
import riccardogulin.d6.repositories.UserSettingsRepository;
import riccardogulin.d6.repositories.UsersRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private UserSettingsRepository userSettingsRepository;

	public List<User> getUsers() {
		return this.usersRepository.findAll();
	}

	public NewUserRespDTO save(NewUserDTO body) {

		if (body.firstName().length() < 2) throw new ValidationException("Il nome non può essere più corto di 2 caratteri");

		// TODO: Verificare che l'indirizzo email non sia già in uso

		User newUser = new User(body.firstName(), body.lastName(), body.email(), body.password(), body.dateOfBirth(),
				"https://ui-avatars.com/api/?name=" + body.firstName() + "+" + body.lastName());

		User saved = this.usersRepository.save(newUser);

		UserSettings settings = new UserSettings(saved);
		userSettingsRepository.save(settings);

		System.out.println("L'utente " + saved.getId() + " è stato correttamente salvato");

		return new NewUserRespDTO(saved.getId());
	}

	public User findById(UUID userId) {
		return this.usersRepository.findById(userId).orElseThrow(() -> new NotFoundException(userId));
	}

	public User findByIdAndUpdate(UUID userId, NewUserDTO body) {

		User found = this.findById(userId);

		found.setFirstName(body.firstName());
		found.setLastName(body.lastName());
		found.setEmail(body.email());
		found.setPassword(body.password());
		found.setDateOfBirth(body.dateOfBirth());
		found.setAvatarURL("https://ui-avatars.com/api/?name=" + body.firstName() + "+" + body.lastName());

		return this.usersRepository.save(found);
	}

	public void findByIdAndDelete(UUID userId) {
		User found = this.findById(userId);
		this.usersRepository.delete(found);
	}

	public void removeSettingsFromUser(UUID userId) {
		User found = this.findById(userId);
		found.setSettings(null);
		this.usersRepository.save(found);
	}

}
