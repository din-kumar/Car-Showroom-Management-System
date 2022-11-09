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
import Entity.WishlistEntity;

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

	public void vehicleview(UserEntity U) {
        Session session = sf.openSession();
        int op;
        WishlistFunction W = new WishlistFunction();
        Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from VehicleEntity");
		List<VehicleEntity> V=q.list();
		System.out.println("Id\t\t\tBrand\t\tModel\t\tStock\t\tPrice");
		System.out.println();
		for(VehicleEntity ve: V) {
			System.out.println(ve.getVehicleId()+"\t\t"+ve.getBrand()+"\t\t"+ve.getModel()+"\t\t"+ve.getVehicleStock()+"\t\t"+ve.getVehiclePrice());
			}
		System.out.println("Would you like to wishlist any vehicle ?\n1. Yes\n2. No");
		op=sc.nextInt();
		if(op==1)
		{System.out.println("Input the vehicle id");
		int id = sc.nextInt();
			W.wishlist(U,id);
		}
	}

	public void vehicleviewadmin() {
		// TODO Auto-generated method stub
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from VehicleEntity");
		List<VehicleEntity> V=q.list();
		System.out.println("Id\t\t\tBrand\t\tModel\t\tStock\t\tPrice");
		System.out.println();
		for(VehicleEntity ve: V) {
			System.out.println(ve.getVehicleId()+"\t\t"+ve.getBrand()+"\t\t"+ve.getModel()+"\t\t"+ve.getVehicleStock()+"\t\t"+ve.getVehiclePrice());
			}
	}
	
	public void viewdetails(int id)
	{
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		try {
			VehicleEntity ve = (VehicleEntity) session.get(VehicleEntity.class,id);
			
			System.out.println(ve.getVehicleId()+"\t\t"+ve.getBrand()+"\t\t"+ve.getModel()+"\t\t"+ve.getVehicleStock()+"\t\t"+ve.getVehiclePrice());
			}
			catch(Exception e){
				System.out.println("Wrong id");
			}
	}
	
	public void viewdetailswithuser(int id,String username)
	{
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		try {
			VehicleEntity ve = (VehicleEntity) session.get(VehicleEntity.class,id);
			
			System.out.println(username+"\t\t"+ve.getVehicleId()+"\t\t"+ve.getBrand()+"\t\t"+ve.getModel()+"\t\t"+ve.getVehicleStock()+"\t\t"+ve.getVehiclePrice());
			}
			catch(Exception e){
				System.out.println("Wrong id");
			}
	}
	
}