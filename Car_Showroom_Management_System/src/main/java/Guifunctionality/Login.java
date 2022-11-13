package Guifunctionality;

import java.awt.*;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class Login {

	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    Session session = sf.openSession();
    Transaction transaction = session.beginTransaction();
	// ----------------------- Third Part 2 Window -----------------------------------
    

		public void Registered_User(Frame frame) {
			Layout l=new Layout();
	// ******************* Label ************************************
			Label u_id = new Label("User Id");
			u_id.setBounds(20, 80, 100, 30);

			Label pass = new Label("Password");
			pass.setBounds(20, 120, 100, 30);
			
			final TextField i_u_id = new TextField();
			i_u_id.setBounds(160, 80, 200, 30);
			
			final TextField i_pass = new TextField();
			i_pass.setBounds(160, 120, 200, 30);
			
	// ************************ Buttons *******************************************
			Button submit = new Button("Submit");
			Button back = new Button("Back");
			submit.setBounds(40, 160, 80, 30);
			back.setBounds(140, 160, 80, 30);

	// ************************ Add Items ***************************	
			frame.add(u_id);
			frame.add(pass);
			frame.add(i_u_id);
			frame.add(i_pass);
			frame.setSize(500,300);
			frame.add(submit);
			frame.add(back);
			
	//************************** Button function *************************************
			submit.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					if(i_u_id.getText().equals("admin")) {
						if(i_pass.getText().equals("admin")) {
						frame.dispose();
						AdminProfile ap=new AdminProfile();
						ap.Frame5();
						}
						else {
							//wrong password
						}
							
					}
					else {
						
					
					
					
					
					
					Query q=session.createQuery("from UserEntity");
					List<UserEntity> u=q.list();
					for(UserEntity i:u) {
						if(i_u_id.getText().equals(i.getUserName())) {
							if(i_pass.getText().equals(i.getPassword())) {
								UserProfile up=new UserProfile();
								frame.dispose();
								up.Frame2(i);
							}
							else {
								//wrong password
							}
								
						}
						else {
							//wrong username
						}
					}
			
					}
				}
			});
							
					back.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
							frame.removeAll();
							frame.dispose();
							l.Frame1();
						}
					});

			
					
		}
		public void Logout(Frame frame){
			Button logout = new Button("Log Out");
			logout.setBounds(120,350,100,30);
			logout.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					frame.removeAll();
					Registered_User(frame);
						
					}
			});
				
			frame.add(logout);
				
				
		}

    
}
