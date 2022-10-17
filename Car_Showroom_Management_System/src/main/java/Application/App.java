package Application;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.UserEntity;
import Entity.VehicleEntity;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Vehicle vehicle = new Vehicle();
        
        Configuration con = new Configuration().configure().addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(UserEntity.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        

        	User U = new User(); // use only for front end gui
        	
    		System.out.println(U);
    		
    		UserEntity entity = new UserEntity();
    		entity.setUserId(U.getUserId());
    		entity.setUserName(U.getUserName());
    		entity.setEmail(U.getEmail());
    		entity.setPhoneNo(U.getPhoneNo());
    		entity.setHomeAdd(U.getHomeAdd());

        transaction.commit();
        
    }
}
