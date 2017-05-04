package facades;

import entities.Priority;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PriorityFacade extends AbstractFacade<Priority> {

    @PersistenceContext(unitName = "MummyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        em.getEntityManagerFactory().getCache().evictAll();
        return em;
    }

    public PriorityFacade() {
        super(Priority.class);
    }
    
}
