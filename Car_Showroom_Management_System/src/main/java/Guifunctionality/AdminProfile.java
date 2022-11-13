package Guifunctionality;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.Sales;
import Entity.UserEntity;
import Entity.VehicleEntity;
import Entity.WishlistEntity;

public class AdminProfile {

	Login lo=new Login();
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    
    Frame frame;
    public void Frame5() {
		frame = new Frame();
		
		Admin_Profile(frame);
			
			frame.setLayout(null);
			frame.setVisible(true);

			
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});
	}
	
	public void Admin_Profile(Frame frame){
		frame.removeAll();
			
		Label welcome = new Label("Welcome Admin");
		welcome.setBounds(80, 40, 100, 30);
		
		Label option = new Label("Select");
		option.setBounds(20,80,100,30);
		final Choice c=new Choice();
		c.add("Add Vehicle");
		c.add("View Vehicle");
		c.add("View Registered Users");
		c.add("View Full Wishlist with User");
		c.add("Sales");
		c.setBounds(140, 80, 200, 30);
		Button submit=new Button("Submit");
		submit.setBounds(350,80,100,30);
			

	
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
		//Button function
				submit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frame.removeAll();
						
						switch(c.getSelectedIndex()) {
						case 0 -> {frame.dispose();Aaddvehicle av=new Aaddvehicle();av.Frame10();}
						case 1 -> {frame.dispose();Avehicleview avv=new Avehicleview();avv.Frame7();}
						case 2 -> {frame.dispose();Aviewuser au=new Aviewuser();au.Frame6();}
						case 3 -> {frame.dispose();Afullwishlist aw=new Afullwishlist();aw.Frame8();}
						case 4 -> {frame.dispose();Salesview sv=new Salesview();sv.Frame9();}
						}
					}

				});	
		lo.Logout(frame);
			
		frame.add(welcome);
		frame.add(option);
		frame.add(submit);
		frame.add(c);
		frame.setSize(600,400);
}

}
