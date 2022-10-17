package Application;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import Entity.UserEntity;
import Entity.VehicleEntity;

public class App 
{
    public static void main( String[] args )
    {
        
        
        Configuration con = new Configuration().configure().addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(UserEntity.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
        SessionFactory sf = con.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();

//------------------------------------------------------------------------------------------//

        	User U = new User(); // use only for front end gui
        	
    		System.out.println(U);
    		
    		UserEntity user = new UserEntity();
    		user.setUserName(U.getUserName());
    		user.setEmail(U.getEmail());
    		user.setPhoneNo(U.getPhoneNo());
    		user.setHomeAdd(U.getHomeAdd());
//------------------------------------------------------------------------------------------//
    		
    		Vehicle V = new Vehicle(); //use only for front end and gui
    		
    		System.out.println(U);
    		
    		VehicleEntity vehicle = new VehicleEntity();
    		vehicle.setBrand(V.getBrand());
    		vehicle.setModel(V.getModel());
    		vehicle.setVehiclePrice(V.getVehiclePrice());
    		vehicle.setVehicleStock(V.getVehicleStock());
    		
//------------------------------------------------------------------------------------------//
       transaction.commit();
        
    }
}
