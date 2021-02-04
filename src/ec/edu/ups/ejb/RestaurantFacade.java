package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.Restaurant;

@Stateless
public class RestaurantFacade extends AbstractFacade<Restaurant> {

	@PersistenceContext(unitName = "SerpaPulgarin-Roberto-ExamenFinal")
    private EntityManager em;
	
    public RestaurantFacade() {
    	super(Restaurant.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	private static final String RESTAURANTS_MAX = "SELECT re FROM Restaurant re WHERE re.persons > :key ";
	
	public List<Restaurant> findRestMaxPerson(int key) {
		try {
			List<Restaurant> resultList = (List<Restaurant>) em.createQuery(RESTAURANTS_MAX)
					.setParameter("key", key).getResultList();
			return resultList;
		} catch (Exception e) {
			return null;
		}
		
		
	}
	

}
