package facades;

import entities.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserFacade extends AbstractFacade<User> {

    @PersistenceContext(unitName = "MummyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }
    
    public User findUserByEmail(String email) {
        Query users = em.createNamedQuery("User.findByEmail").setParameter("email", email);
        
        return (User) users.getSingleResult();
    }
    
}
