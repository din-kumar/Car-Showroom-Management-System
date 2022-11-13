package Guifunctionality;

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

import java.awt.*;
import Entity.Sales;
import Entity.UserEntity;
import Entity.VehicleEntity;
import Entity.WishlistEntity;

public class NewUser{
	
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    
    
 // --------------------------- Third Part-1 Window ------------------------------
 	public void New_User(Frame frame) {
 		Layout l =new Layout();
 		Session session = sf.openSession();
 	    Transaction transaction = session.beginTransaction();
 // ******************* Labels ******************************************
 		Label fname = new Label("First Name - ");
 		fname.setBounds(20, 80, 120, 30);

 		Label lname = new Label("Last Name - ");
 		lname.setBounds(20, 120, 120, 30);

 		Label mobile = new Label("Phone No. -");
 		mobile.setBounds(20, 160, 120, 30);

 		Label email = new Label("E-Mail - ");
 		email.setBounds(20, 200, 120, 30);

 		Label adrs = new Label("Address - ");
 		adrs.setBounds(20, 240, 120, 30);

 		Label u_id = new Label("Create User Id - ");
 		u_id.setBounds(20, 280, 120, 30);

 		Label c_uid = new Label("Confirm User Id - ");
 		c_uid.setBounds(20, 320, 120, 30);

 		Label pass = new Label("Create Password - ");
 		pass.setBounds(20, 360, 120, 30);

 		Label c_pass = new Label("Confirm Password - ");
 		c_pass.setBounds(20, 400, 120, 30);

 // ************************* Inputs Container ********************************
 		

 		final TextField i_fname = new TextField();
 		i_fname.setBounds(160, 80, 200, 30);

 		final TextField i_lname = new TextField();
 		i_lname.setBounds(160, 120, 200, 30);

 		final TextField i_mobile = new TextField();
 		i_mobile.setBounds(160, 160, 200, 30);

 		final TextField i_email = new TextField();
 		i_email.setBounds(160, 200, 200, 30);

 		final TextField i_adrs = new TextField();
 		i_adrs.setBounds(160, 240, 200, 30);

 		final TextField i_u_id = new TextField();
 		i_u_id.setBounds(160, 280, 200, 30);

 		final TextField i_c_uid = new TextField();
 		i_c_uid.setBounds(160, 320, 200, 30);

 		final TextField i_pass = new TextField();
 		i_pass.setEchoChar('*');
 		i_pass.setBounds(160, 360, 200, 30);

 		final TextField i_c_pass = new TextField();
 		i_c_pass.setEchoChar('*');
 		i_c_pass.setBounds(160, 400, 200, 30);
 		
 		
 // ************************ Buttons *******************************************
 		Button submit = new Button("Submit");
 		final Button back = new Button("Back");
 		submit.setBounds(40, 440, 80, 30);
 		back.setBounds(140, 440, 80, 30);

 // *********************add items*********************************************
 		frame.add(fname);
 		frame.add(lname);
 		frame.add(mobile);
 		frame.add(email);
 		frame.add(adrs);
 		frame.add(u_id);
 		frame.add(c_uid);
 		frame.add(pass);
 		frame.add(c_pass);
 		frame.add(i_fname);
 		frame.add(i_lname);
 		frame.add(i_mobile);
 		frame.add(i_email);
 		frame.add(i_adrs);
 		frame.add(i_u_id);
 		frame.add(i_c_uid);
 		frame.add(i_pass);
 		frame.add(i_c_pass);
 		frame.add(submit);
 		frame.add(back);
 		frame.setSize(500,600);		

 //************************** Button function *************************************
 		submit.addActionListener(new ActionListener() {
 			
 			public void actionPerformed(ActionEvent e) {
 				// add data to the database
 				
 				UserEntity U = new UserEntity();
 				//temp1 = i_fname.getText() +i_lname.getText();
// 				U.setName(temp1);
 				
 				
 				U.setEmail(i_email.getText());
 				
 				
 				U.setHomeAdd(i_adrs.getText());
 				
 				
 				U.setPhoneNo(Long.parseLong(i_mobile.getText()));
 				
 				 
 				Query q= session.createQuery("from UserEntity");
 				List<UserEntity> u = q.list();
 				if(i_u_id.getText().equals(i_c_uid.getText())) {
 					for(UserEntity i:u ) {
 					if(i_u_id.getText().equals(i.getUserName())) {
 						new dialog("Username already exist");
 						//NOt working
 						//
 						//
 						//
 						//
 						//
 					}
 					else {
 						U.setUserName(i_u_id.getText());
 						
 						if(i_pass.getText().equals(i_c_pass.getText())) {
 							U.setPassword(i_pass.getText());
 							session.save(U);
 							if (!transaction.wasCommitted()){ 
 							    transaction.commit();
 							}
 							session.flush();
 							
 						}
 						else {
 							new dialog("Please enter same Password");
 						}
 					}
 					
 					}
 				}
 				else {
 					new dialog("Please enter same Username");
 				}
 				
 				new dialog("Your Response has been Submitted Successfuly");

 				frame.dispose();
 				l.Frame1();
 				//frame.setSize(500,300);
 			
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


}
