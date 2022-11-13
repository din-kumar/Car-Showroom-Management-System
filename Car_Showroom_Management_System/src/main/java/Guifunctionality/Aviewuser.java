package Guifunctionality;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
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

public class Aviewuser {


	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
	
    Frame frame;
    public void Frame6() {
		frame = new Frame();
		
		viewuser(frame);
			
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
	
	//viewusers
		public void viewuser(Frame frame) {
			frame.removeAll();
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();
	        
	        Query q=session.createQuery("from UserEntity");
	        List<UserEntity> U = q.list();
	        Label display=new Label("List of Users: ");
			display.setBounds(100, 40, 200, 30);
			int x=120;
			Label uname=new Label("Username");
			Label email=new Label("Email");
			Label add=new Label("Address");
			Label phnum=new Label("Phone Number");
			final Button back = new Button("Back");
			uname.setBounds(20,80,100,30);
			email.setBounds(130,80,100,30);
			add.setBounds(240,80,100,30);
			phnum.setBounds(350,80,100,30);
			
			
			for(UserEntity ue:U) {
				Label vid=new Label(ue.getUserName());
				Label vbrand=new Label(ue.getEmail());
				Label vmodel=new Label(ue.getHomeAdd());
				Label vprice=new Label(""+ue.getPhoneNo());
				vid.setBounds(20,x,100,30);
				vbrand.setBounds(130,x,100,30);
				vmodel.setBounds(240,x,100,30);
				vprice.setBounds(350,x,100,30);	
			x+=60;
			frame.add(vid);
			frame.add(vbrand);
			frame.add(vmodel);
			frame.add(vprice);
			}
			back.setBounds(140, x+40, 80, 30);
			frame.add(display);
			frame.add(uname);
			frame.add(email);
			frame.add(add);
			frame.add(phnum);
			frame.add(back);
			frame.setSize(800,x+400);
			back.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					AdminProfile ap=new AdminProfile();
					ap.Frame5();
				}
			});
	        
	        
		}
}
