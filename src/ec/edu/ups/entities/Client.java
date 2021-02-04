package ec.edu.ups.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: client
 *
 */
@Entity
@Table(name="CLIENTS")

public class Client implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Client() {
		super();
	}
	
	public Client(String name, String dni, String email, String address, String phone) {
		super();
		this.name = name;
		this.dni = dni;
		this.email = email;
		this.address = address;
		this.phone = phone;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cli_id")
	private int id;
	
	@Column(name="cli_name", length=255, nullable=false)
	private String name;
	
	@Column(name="cli_dni", length=10, nullable=false, unique=true)
	private String dni;
	
	@Column(name="cli_email", length=255, nullable=false, unique=true)
	private String email;
	
	@Column(name="cli_address", length=255, nullable=false)
	private String address;
	
	@Column(name="cli_phone", length=10, nullable=false)
	private String phone;
   
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserve> reserves) {
		this.reserves = reserves;
	}
}
