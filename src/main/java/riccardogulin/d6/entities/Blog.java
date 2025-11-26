package riccardogulin.d6.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "blogs")
public class Blog {
	@Id
	@GeneratedValue
	private UUID id;

	private String title;
	private String content;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User author;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "blogs_categories",
			joinColumns = @JoinColumn(name = "blog_id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "category_id", nullable = false))
	private List<Category> categories;

	public Blog() {
	}

	public Blog(String title, String content, User author, List<Category> categories) {
		this.title = title;
		this.content = content;
		this.author = author;
		this.categories = categories;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public UUID getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Blog{" +
				"id=" + id +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", author=" + author +
				", categories=" + categories +
				'}';
	}
}
