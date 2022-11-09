package functions;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.UserEntity;
import Entity.WishlistEntity;

public class WishlistFunction {

	Configuration con = new Configuration().configure().addAnnotatedClass(WishlistEntity.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
	
	public void wishlist(UserEntity u, int id) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
	    Transaction transaction = session.beginTransaction();
		WishlistEntity W = new WishlistEntity();
		W.setUsername(u.getUserName());
		W.setVehicleId(id);
		session.save(W);
        transaction.commit();
        System.out.println("Added to wishlist");
	}

	public void mywishlist(UserEntity u) {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from WishlistEntity");
		VehicleFunction V = new VehicleFunction();
		List<WishlistEntity> W=q.list();
		
		System.out.println();
		for(WishlistEntity we: W) {
			if(we.getUsername().equals(u.getUserName()))
			{
				V.viewdetails(we.getVehicleId());
			}
		}
	}

	public void fullwishlist() {
		// TODO Auto-generated method stub
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from WishlistEntity");
		System.out.println("Username\t\tVehicle Brand\t\tVehicle Model\t\tStock");
		VehicleFunction V = new VehicleFunction();
		List<WishlistEntity> W=q.list();
		System.out.println();
		for(WishlistEntity we: W) {
				V.viewdetailswithuser(we.getVehicleId(),we.getUsername());
		}
	}

	
}
