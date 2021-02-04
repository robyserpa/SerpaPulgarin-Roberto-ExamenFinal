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

import ec.edu.ups.ejb.RestaurantFacade;
import ec.edu.ups.entities.Reserve;
import ec.edu.ups.entities.Restaurant;

@FacesConfig(version = FacesConfig.Version.JSF_2_3)
@Named
@SessionScoped
public class RestaurantBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private RestaurantFacade ejbRestaurantFacade;
	
	private String name;
	private String address;
	private String phone;
	private int persons;
	private Restaurant restaurant;
	private List<Restaurant> restaurantList;
	private List<Reserve> reserves;
	
	public RestaurantBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		restaurantList = ejbRestaurantFacade.findAll();
	}
	
	public void searchCRestaurantAndListReserve() {
		String[][] attributes = {{"name"}};
		String[] values = {"equal&"+name};
		restaurant = ejbRestaurantFacade.findByPath(attributes, values, null, 0, 1, false, false).get(0);
		if (restaurant == null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"No se encuentra el restaurante", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg2", message);
		}else {
			reserves = restaurant.getReserves();
		}
		cleanString();
	}
	
	public String create() {
		try {
			Restaurant restaurant = new Restaurant(name, address, phone, persons);
			ejbRestaurantFacade.create(restaurant);
			cleanString();
		} catch (Exception e) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
					"Ya existe", null);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("msg1", message);
		}
		return null;
	}
	
	public void cleanString() {
		name = "";
		persons = 0;
		address = "";
		phone = "";
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}
	
}
