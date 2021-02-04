package ec.edu.ups.controller;

import java.io.Serializable;
import java.util.Calendar;
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
import ec.edu.ups.ejb.RestaurantFacade;
import ec.edu.ups.entities.Client;
import ec.edu.ups.entities.Reserve;
import ec.edu.ups.entities.Restaurant;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class ReserveBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ReserveFacade ejbReserveFacade;
	
	@EJB
	private RestaurantFacade ejbRestaurantFacade;
	
	@EJB
	private ClientFacade ejbClientFacade;
	
	private Calendar date;
	private int restaurantId;
	private int maxPerson;
	private String nameRes;
	private String clientDNI;
	private Client client;
	private Restaurant restaurant;
	private Reserve reserve;
	private List<Reserve> reserveList;
	private List<Restaurant> restaurants;
	
	public ReserveBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		
	}
	
	public void searchClient() {
		client = ejbClientFacade.findClientByDNI(clientDNI);
		if (client == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No se encuentra el cliente", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg2", message);
		}
		cleanString();
	}
	
	public void searchRestaurant() {
		String[][] attributes = {{"name"}};
		String[] values = {"equal&" + nameRes};
		try {
			restaurant = ejbRestaurantFacade.findByPath(attributes, values, null, 0, 1, false, false).get(0);
		} catch (Exception e) {
			restaurant = null;
		}
		if (restaurant == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No se encuentra el restaurante", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg2", message);
		}
		cleanString();
	}
	
	public void listRestaurant() {
		restaurants =  ejbRestaurantFacade.findRestMaxPerson(maxPerson);
	}
	
	public String create() {
		try {
			Restaurant restaurant;
			restaurant = ejbRestaurantFacade.read(restaurantId);
			int maxP = restaurant.getPersons() - maxPerson;
			restaurant.setPersons(maxP);
			ejbRestaurantFacade.update(restaurant);
			ejbReserveFacade.create(new Reserve(date, restaurant, client));
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
		date = null;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Reserve getReserve() {
		return reserve;
	}

	public void setReserve(Reserve reserve) {
		this.reserve = reserve;
	}

	public List<Reserve> getReserveList() {
		return reserveList;
	}

	public void setReserveList(List<Reserve> reserveList) {
		this.reserveList = reserveList;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getClientDNI() {
		return clientDNI;
	}

	public void setClientDNI(String clientDNI) {
		this.clientDNI = clientDNI;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	public int getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(int maxPerson) {
		this.maxPerson = maxPerson;
	}

	public String getNameRes() {
		return nameRes;
	}

	public void setNameRes(String nameRes) {
		this.nameRes = nameRes;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	
}
