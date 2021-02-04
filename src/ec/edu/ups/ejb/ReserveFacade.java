package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.Reserve;

@Stateless
public class ReserveFacade extends AbstractFacade<Reserve> {

	@PersistenceContext(unitName = "SerpaPulgarin-Roberto-ExamenFinal")
    private EntityManager em;
	
    public ReserveFacade() {
    	super(Reserve.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	

}
