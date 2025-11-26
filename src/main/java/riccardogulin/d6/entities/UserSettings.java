package riccardogulin.d6.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "users_settings")
public class UserSettings {
	@Id
	@GeneratedValue
	private UUID id;

	private boolean darkMode;
	private boolean emailNotifications;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public UserSettings() {
	}

	public UserSettings(User user) {
		this.user = user;
		this.darkMode = false;
		this.emailNotifications = false;
	}

	public UUID getId() {
		return id;
	}


	public boolean isDarkMode() {
		return darkMode;
	}

	public void setDarkMode(boolean darkMode) {
		this.darkMode = darkMode;
	}

	public boolean isEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserSettings{" +
				"id=" + id +
				", darkMode=" + darkMode +
				", emailNotifications=" + emailNotifications +
				", user=" + user +
				'}';
	}
}
