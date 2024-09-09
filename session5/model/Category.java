package ir.freeland.springboot.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;	
	@Column(name = "NAME", length = 50, nullable = false, unique = false)
	private String name;
	@Column
	private String location;
	@OneToOne(mappedBy = "category")
    private Item2 item2;
	
	public Category() {
		super();
	}

	public Category(long id, String name, String location) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Item2 getItem2() {
		return item2;
	}

	public void setItem2(Item2 item2) {
		this.item2 = item2;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", location=" + location + "]";
	}
	
	
	
}
