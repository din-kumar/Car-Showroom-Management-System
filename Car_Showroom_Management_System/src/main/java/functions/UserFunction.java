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

public class UserFunction {
	
	Scanner sc= new Scanner(System.in);
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
	
    public void clrscr()
    {
        System.out.println("--------------------------------------");
    }
    
	public void register()

	{
	    Session session = sf.openSession();
	    Transaction transaction = session.beginTransaction();

		clrscr();
        UserEntity U = new UserEntity();
        long temp3;
       String temp,temp2;
        System.out.print("Input your username : ");
        temp = sc.nextLine();
        U.setUserName(temp);
        System.out.print("Input your email : ");
        temp = sc.nextLine();
        U.setEmail(temp);
        System.out.print("Input your phone Number : ");
        temp3 = sc.nextLong();
        temp=sc.nextLine();
        U.setPhoneNo(temp3);
        System.out.print("Input your Address : ");
        temp = sc.nextLine();
        U.setHomeAdd(temp);
        System.out.print("Input your password : ");
        temp = sc.nextLine();
        System.out.print("Repeat your password : ");
        temp2 = sc.nextLine();
        
        if(temp.equals(temp2))
        {
        	U.setPassword(temp2);
        	session.save(U);
            transaction.commit();
        }
        else
        {
        	System.out.println("Password Do not match");
        	register();
        }
	}
	
	public void login()
	{    Session session = sf.openSession();
    Transaction transaction = session.beginTransaction();
		
		clrscr();
		System.out.print("Input your username : ");
		String username=sc.next();
		System.out.print("Input your password : ");
		String password=sc.next();
		//check userpass
		try {
		UserEntity U = (UserEntity) session.get(UserEntity.class,username);
		if(password.equals(U.getPassword()))
		{
			home(U);
		}
		else
			System.out.println("Wrong Password");
		}
		catch(Exception e){
			System.out.println("Wrong username");
		}
		//if is correct
		
		
	}
	
	public void admin()
	{
		clrscr();
		String adminname = "admin";
		String adminpass = "admin";
		System.out.print("Input your username : ");
		String username=sc.next();
		System.out.print("Input your password : ");
		String password=sc.next();
		//check userpass
		if(username.equals(adminname))
		{
			if(password.equals(adminpass))
			{
				adminhome();
			}
			else
			{
				System.out.println("password is incorrect");
			}
		}
		else
		System.out.println("username is incorrect");
	}
	
	public void home(UserEntity U)
	{
		clrscr();
		VehicleFunction V = new VehicleFunction();
		System.out.println("Hello "+U.getUserName());
		int k=-1;
		while(k!=0) {
			clrscr();
			System.out.println("Input your option \n1.View Available Vehicle\n0.Exit");
			k=sc.nextInt();
			switch(k) {
			case 1:V.vehicleview();break;
			default:;break;
			}
	}
	}
	
	public void adminhome()
	{
		clrscr();
		VehicleFunction V = new VehicleFunction();
		System.out.println("Hello Admin");
		int k=-1;
		while(k!=0) {
			clrscr();
			System.out.println("Input your option \n1.Add Vehicle\n2.View Vehicle\n3.View Registered User\n0.Exit");
			k=sc.nextInt();
			switch(k) {
			case 1:V.vehicleadd();break;
			case 2:V.vehicleview();break;
			case 3:userview();break;
			default:;break;
			}
		
	}
}
	public void userview() {
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		Query q = session.createQuery("from UserEntity");
		List<UserEntity> U=q.list();
		System.out.println("Username\t\tPhone Number\t\tEmail Address\t\tAddress");
		System.out.println();
		for(UserEntity ue: U) {
			System.out.println(ue.getUserName()+"\t\t"+ue.getPhoneNo()+"\t\t"+ue.getEmail()+"\t\t"+ue.getHomeAdd());
			}
	}
}
//test