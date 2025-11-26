package riccardogulin.d6.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class Category {
	@Id
	@GeneratedValue
	private UUID id;

	private String name;

	// Many to many BIDIREZIONALE
	@ManyToMany(mappedBy = "categories")
	private List<Blog> blogList;

	public Category() {
	}

	public Category(String name) {
		this.name = name;
	}

	public UUID getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Blog> getBlogList() {
		return blogList;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				// ", blogList=" + blogList +
				'}';
	}
}
