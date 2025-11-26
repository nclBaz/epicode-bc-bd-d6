package riccardogulin.d6.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity // OBBLIGATORIA
@Table(name = "users") // NON OBBLIGATORIA
public class User {
	@Id
	@GeneratedValue // NON OBBLIGATORIA. Però utile se vogliamo che la chiave primaria sia generata automaticamente dal database
	// se non specifichiamo alcuna opzione, la strategia di generazione sarà AUTO (perfetta per UUID)
	private UUID id;

	@Column(name = "nome", nullable = false) // NON OBBLIGATORIA
	private String firstName;
	@Column(name = "cognome", nullable = false)
	private String lastName;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	@JsonIgnore
	private String password;
	@Enumerated(EnumType.STRING) // Se ho un enum e non utilizzo @Enumerated(EnumType.STRING), la colonna corrispondente sarà di tipo numerico
	private Role role;
	private LocalDate dateOfBirth;
	private String avatarURL;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private UserSettings settings;
	@JsonIgnore
	@OneToMany(mappedBy = "author")
	private List<Blog> blogs;

	public User() {
	}

	public User(String firstName, String lastName, String email, String password, LocalDate dateOfBirth, String avatarURL) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.avatarURL = avatarURL;
		this.role = Role.USER;
		this.blogs = new ArrayList<>();
	}

	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public UserSettings getUserSettings() {
		return settings;
	}

	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getAvatarURL() {
		return avatarURL;
	}

	public void setAvatarURL(String avatarURL) {
		this.avatarURL = avatarURL;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", role=" + role +
				", dateOfBirth=" + dateOfBirth +
				", avatarURL='" + avatarURL + '\'' +
				// ", settings='" + userSettings + '\'' +
				'}';
	}
}
