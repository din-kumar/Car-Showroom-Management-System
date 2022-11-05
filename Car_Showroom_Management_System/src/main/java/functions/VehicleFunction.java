package functions;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.UserEntity;
import Entity.VehicleEntity;

public class VehicleFunction {

	
	Scanner sc= new Scanner(System.in);
	Configuration con = new Configuration().configure().addAnnotatedClass(VehicleEntity.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);

    public void clrscr() {
    	System.out.println("-----------------------------------------------");
    }
    
    public void vehicleadd() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
    	
    	
		clrscr();
		VehicleEntity V = new VehicleEntity();
		double temp;
		String temp2;
		int temp3;
		
		System.out.print("Input Vehicle Brand : ");
		temp2 = sc.nextLine();
		V.setBrand(temp2);
		
		System.out.print("Input Vehicle Model : ");
		temp2 = sc.nextLine();
		V.setModel(temp2);
		
		System.out.print("Input Vehicle Price : ");
		temp = sc.nextDouble();
		V.setVehiclePrice(temp);
		
		System.out.print("Input Vehicle Stock : ");
		temp3 = sc.nextInt();
		sc.nextLine();
		V.setVehicleStock(temp3);
		
		//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
		// Check here for the data that were Already stored in the database
		session.save(V);
		transaction.commit();
		System.out.println("Saved Successfully");

		
	}

	public void vehicleview() {
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from VehicleEntity");
		List<VehicleEntity> V=q.list();
		System.out.println("Brand\t\tModel\t\tStock\t\tPrice");
		System.out.println();
		for(VehicleEntity ve: V) {
			System.out.println(ve.getBrand()+"\t\t"+ve.getModel()+"\t\t"+ve.getVehicleStock()+"\t\t"+ve.getVehiclePrice());
			}
	}
}