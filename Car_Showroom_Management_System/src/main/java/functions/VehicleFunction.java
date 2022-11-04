package functions;

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
    Session session = sf.openSession();
    Transaction transaction = session.beginTransaction();
	
    public void clrscr() {
    	System.out.println("-----------------------------------------------");
    }
    
    public void vehicleadd() {
    	
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
		
		System.out.println("add another vehicle ? \n1. Yes \n2.No");
		int op;
		op=sc.nextInt();
		if(op==1)
		{
			vehicleadd();
			
		}
		
	}

	public void vehicleview() {
//		VehicleEntity V = (VehicleEntity) session.createQuery("SELECT a FROM VehicleEntity");
//		String sql = "Select * FROM VehicleEntity";
//		Query countQuery = session.createSQLQuery(sql);
//		System.out.println(countQuery.list());
//		System.out.println("Number of Employee: " + countQuery.list().get(0));
//		Integer count = Integer.parseInt((String) countQuery.list().get(0));
//		for(int i = 1;i<=count;i++) {
//			
//			VehicleEntity V = (VehicleEntity)session.get(VehicleEntity.class, i);
//			System.out.println(V.toString());
//			
//		}
	}
}
//test