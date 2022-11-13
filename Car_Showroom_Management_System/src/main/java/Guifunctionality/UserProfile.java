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

public class UserProfile {

	
	Layout l=new Layout();
	Login lo=new Login();
	
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    
    Frame frame;
    public void Frame2(UserEntity U) {
		frame = new Frame();
		
		User_Profile(U,frame);
			
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
    
    public void User_Profile(UserEntity U, Frame frame){
		
    	
		Label welcome = new Label("Welcome  " + U.getUserName());
		welcome.setBounds(100, 40, 200, 30);
		
		Label option = new Label("Select");
		option.setBounds(20,80,100,30);
		final Choice c=new Choice();
		c.add("View Available Cars");
		c.add("View Wishlist");
		c.setBounds(140, 80, 150, 30);
		Button submit=new Button("Submit");
		submit.setBounds(300,80,100,30);
			
		frame.add(welcome);
		frame.add(c);
		frame.add(option);
		frame.add(submit);
	
			
		frame.setSize(600,400);
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
		//Button function
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.removeAll();
				if(c.getSelectedIndex()==0) {
				     frame.dispose();
				     Uvehicleview uv=new Uvehicleview();
				     uv.Frame3(U);
				    
					}
				else {
					Wishlistf wl=new Wishlistf();
					frame.dispose();
					wl.Frame4(U);
				}
			}
		});
		
		lo.Logout(frame);
			
		
		
}
}
