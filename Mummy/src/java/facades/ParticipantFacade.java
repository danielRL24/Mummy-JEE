package facades;

import entities.Participant;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ParticipantFacade extends AbstractFacade<Participant> {

    @PersistenceContext(unitName = "MummyPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        em.getEntityManagerFactory().getCache().evictAll();
        return em;
    }

    public ParticipantFacade() {
        super(Participant.class);
    }
    
}
