package riccardogulin.d6.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.d6.entities.User;
import riccardogulin.d6.entities.UserSettings;
import riccardogulin.d6.repositories.UserSettingsRepository;

import java.util.UUID;

@Service
public class UsersSettingsService {

	@Autowired
	private UserSettingsRepository userSettingsRepository;
	@Autowired
	private UsersService usersService;

	public UserSettings save(UUID userId) {
		User userFromDB = this.usersService.findById(userId);
		UserSettings settings = new UserSettings(userFromDB);
		return this.userSettingsRepository.save(settings);
	}
}
