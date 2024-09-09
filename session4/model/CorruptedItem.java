package ir.freeland.springboot.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(name = "corrupted_item")
public class CorruptedItem {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "REASON" , length = 50)
    private String reason;

    @ManyToOne
    @JoinColumn(name = "id")
    private Item item;	
	
    public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


}