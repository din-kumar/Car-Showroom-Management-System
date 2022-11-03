package functions;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import Entity.UserEntity;

public class UserFunction {
	
	Scanner sc= new Scanner(System.in);
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    Session session = sf.openSession();
    Transaction transaction = session.beginTransaction();
	
    public void clrscr()
    {
    	System.out.print("\033[2J");   
        System.out.flush();  
        System.out.println("--------------------------------------");
    }
    
	public void register()

	{

		clrscr();
        UserEntity U = new UserEntity();
        double temp3;
       String temp,temp2;
        System.out.print("Input your username : ");
        temp = sc.next();
        U.setUserName(temp);
        System.out.print("Input your email : ");
        temp = sc.next();
        U.setEmail(temp);
        System.out.print("Input your phone Number : ");
        temp3 = sc.nextDouble();
        U.setPhoneNo(temp3);
        System.out.print("Input your Address : ");
        temp = sc.next();
        U.setHomeAdd(temp);
        System.out.print("Input your password : ");
        temp = sc.next();
        System.out.print("Repeat your password : ");
        temp2 = sc.next();
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
	{
		clrscr();
		System.out.print("Input your username : ");
		String username=sc.next();
		System.out.print("Input your password : ");
		String password=sc.next();
		//check userpass
		UserEntity U = (UserEntity) session.get(UserEntity.class,username);
		//if is correct
		if(password.equals(U.getPassword()))
		{
			home(U);
		}
		
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
		System.out.println("Hello "+U.getUserName());
	}
	
	public void adminhome()
	{
		clrscr();
		System.out.println("Hello Admin");
	}
}
