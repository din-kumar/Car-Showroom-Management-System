package Anudip.Car_Showroom_Management_System;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Vehicle vehicle = new Vehicle();
        User user = new User();
        Configuration con = new Configuration().configure().addAnnotatedClass(Vehicle.class).addAnnotatedClass(User.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        
        
        session.save();
        transaction.commit();
        
    }
}
