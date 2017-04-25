package facades;

import entities.Task;
import entities.User;
import java.util.List;
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
    
    public void addRoleUser(User user, String rolename) {
        Query query = em.createNativeQuery("INSERT INTO user_role (useremail, rolename) " + " VALUES(?,?)");
        query.setParameter(1, user.getEmail());
        query.setParameter(2, rolename);
        query.executeUpdate();
    }
    
    public void destroyRoleUser(User user, String rolename) {
        Query query = em.createNativeQuery("DELETE FROM `user_role` WHERE " + "useremail=? AND rolename=?");
        query.setParameter(1, user.getEmail());
        query.setParameter(2, rolename);
        query.executeUpdate();
    }
    
    public List<Task> getAllTasksCreate(User user) {
        Query tasks = em.createNamedQuery("User.findAllTasksCreate").setParameter("id", user.getId());
        return tasks.getResultList();
    }
    
    public List<Task> getAllTasksParticipate(User user) {
        Query tasks = em.createNamedQuery("User.findAllTasksParticipate").setParameter("id", user.getId());
        return tasks.getResultList();
    }
    
    public List<Task> getAllTasksCreateByName(User user, String title) {
        Query tasks = em.createNamedQuery("User.findAllTasksCreateByTitle")
                .setParameter("id", user.getId())
                .setParameter("title", title);
        return tasks.getResultList();
    }
    
    public List<Task> getAllTasksParticipateByName(User user, String title) {
        Query tasks = em.createNamedQuery("User.findAllTasksParticipateByTitle")
                .setParameter("id", user.getId())
                .setParameter("title", title);
        return tasks.getResultList();
    }
}
