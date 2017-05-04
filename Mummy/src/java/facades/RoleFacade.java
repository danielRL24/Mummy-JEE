package facades;

import entities.Role;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RoleFacade extends AbstractFacade<Role> {

    @PersistenceContext(unitName = "MummyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        em.getEntityManagerFactory().getCache().evictAll();
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }
    
}
