package functions;

import java.util.Scanner;

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
	
	public void vehicleadd() {


//		clrscr();
//        UserEntity U = new UserEntity();
//        double temp3;
//       String temp,temp2;
//        System.out.print("Input your username : ");
//        temp = sc.next();
//        U.setUserName(temp);
//        System.out.print("Input your email : ");
//        temp = sc.next();
//        U.setEmail(temp);
//        System.out.print("Input your phone Number : ");
//        temp3 = sc.nextDouble();
//        U.setPhoneNo(temp3);
//        System.out.print("Input your Address : ");
//        temp = sc.next();
//        U.setHomeAdd(temp);
//        System.out.print("Input your password : ");
//        temp = sc.next();
//        System.out.print("Repeat your password : ");
//        temp2 = sc.next();
//        if(temp.equals(temp2))
//        {
//        	U.setPassword(temp2);
//        	session.save(U);
//            transaction.commit();
//        }
//        else
//        {
//        	System.out.println("Password Do not match");
//        	register();
//        }
		System.out.println("add another vehicle ? \n1. Yes \n2.No");
		int op;
		op=sc.nextInt();
		if(op==1)
		{
			vehicleadd();
			
		}
		
	}

	public void vehicleview() {
		
	}
}
//test