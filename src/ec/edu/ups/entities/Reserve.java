package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reserve
 *
 */
@Entity
@Table(name="RESERVES")

public class Reserve implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Reserve() {
		super();
	}
   
	public Reserve(Calendar date, Restaurant restaurant, Client client) {
		super();
		this.date = date;
		this.restaurant = restaurant;
		this.client = client;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rese_id")
	private int id;
	
	@Column(name = "rese_date")
	private Calendar date;
	
	@ManyToOne
	@JoinColumn
	private Restaurant restaurant;
	
	@ManyToOne
	@JoinColumn
	private Client client;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
}
