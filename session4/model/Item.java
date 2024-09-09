package ir.freeland.springboot.persistence.model;

import jakarta.persistence.*;



@Entity
@Table(name = "item")
public class Item {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", length = 50, nullable = false, unique = false)
    private String name;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CATEGORY")
    private String category;
	
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


}
