package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entities.Client;

@Stateless
public class ClientFacade extends AbstractFacade<Client> {

	@PersistenceContext(unitName = "SerpaPulgarin-Roberto-ExamenFinal")
    private EntityManager em;
	
    public ClientFacade() {
    	super(Client.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public Client findClientByDNI(String clientDNI) {
		Client client;
		String[][] attributes = {{"clientDNI"}};
		String[] values = {"equal&" + clientDNI};
		try {
			client = super.findByPath(attributes, values, null, 0, 1, false, false).get(0);
		} catch (Exception e) {
			client = null;
		}
		return client;
	}
	
	
}
