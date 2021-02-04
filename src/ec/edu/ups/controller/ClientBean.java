package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import ec.edu.ups.ejb.ClientFacade;
import ec.edu.ups.ejb.ReserveFacade;
import ec.edu.ups.entities.Client;
import ec.edu.ups.entities.Reserve;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ClientBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ClientFacade ejbClientFacade;
	
	@EJB
	private ReserveFacade ejbReserveFacade;
	
	private String name;
	private String dni;
	private String email;
	private String address;
	private String phone;
	private Client client;
	private List<Client> clientList;
	private List<Reserve> reserves;
	
	private int clientId;
	private String clientDNI;
	
	
	public ClientBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		clientList = ejbClientFacade.findAll();
	}
	
	public void searchClientAndListReserve() {
		client = ejbClientFacade.findClientByDNI(clientDNI);
		if (client == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No se encuentra el cliente", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg2", message);
		}else {
			reserves = client.getReserves();
		}
		cleanString();
	}
	
	public String create() {
		try {
			Client user = new Client();
			user.setEmail(email);
			user.setDni(dni);
			user.setName(name);
			user.setAddress(address);
			user.setPhone(phone);
			ejbClientFacade.create(user);
//			clientList = ejbClientFacade.findAll();
			cleanString();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Ya existe DNI o Email", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg1", message);
		}
		return null;
	}
	
	public void cleanString() {
		name = "";
		dni = "";
		email = "";
		address = "";
		phone = "";
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Client> getClientList() {
		return clientList;
	}

	public void setClientList(List<Client> clientList) {
		this.clientList = clientList;
	}

	public List<Reserve> getReserves() {
		return reserves;
	}

	public void setReserves(List<Reserve> reserves) {
		this.reserves = reserves;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getClientDNI() {
		return clientDNI;
	}

	public void setClientDNI(String clientDNI) {
		this.clientDNI = clientDNI;
	}
	
}
