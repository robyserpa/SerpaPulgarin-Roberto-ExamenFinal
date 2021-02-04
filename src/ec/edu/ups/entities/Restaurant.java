package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Restaurant
 *
 */
@Entity
@Table(name="RESTAURANTS")

public class Restaurant implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Restaurant() {
		super();
	}
	
	public Restaurant(String name, String address, String phone, int persons) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.persons = persons;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="res_id")
	private int id;
	
	@Column(name="res_name", length=255, nullable=false, unique=true)
	private String name;
	
	@Column(name="res_address", length=255, nullable=false)
	private String address;
	
	@Column(name="res_phone", length=10, nullable=false)
	private String phone;
	
	@Column(name="res_max_persons")
	private int persons;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private List<Reserve> reserves;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getPersons() {
		return persons;
	}

	public void setPersons(int persons) {
		this.persons = persons;
	}

	public List<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserve> reserves) {
		this.reserves = reserves;
	}
	
}
