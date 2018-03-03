package infrastructure;

import java.util.Date;

import org.hibernate.Session;

import it.polito.tesiclustering.infrastructure.HibernateUtil;
import it.polito.tesiclustering.model.User;
 
public class Test {
    public static void main(String[] args) {
    	
        Session session = HibernateUtil.getSessionFactory().openSession();
 
        session.beginTransaction();
        User user = new User();
 
        user.setId(1);
        user.setUsername("Mukesh");
        user.setCreatedBy("Google");
        user.setCreatedDate(new Date());
 
        session.save(user);
        session.getTransaction().commit();
 
    }
 
}