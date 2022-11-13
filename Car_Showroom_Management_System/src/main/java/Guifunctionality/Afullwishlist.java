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

public class Afullwishlist {

	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
	
    Frame frame;
    public void Frame8() {
		frame = new Frame();
		
		Fullwishlist(frame);
			
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
	
	
	//fullwishlisttoadmin
		public void Fullwishlist(Frame frame) {
			frame.removeAll();
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();
	        Query q = session.createQuery("from WishlistEntity");
	        List<WishlistEntity> W=q.list();
	        Label display=new Label("List of Wishlisted Vehicles by Users: ");
			display.setBounds(100, 40, 200, 30);
			int x=120;
			Label uname =new Label("Username");
			Label id=new Label("V Id");
			Label brand=new Label("Brand");
			Label model=new Label("Model ");
			Label price=new Label("Price");
			uname.setBounds(20,80,100,30);
			id.setBounds(130,80,100,30);
			brand.setBounds(240,80,100,30);
			model.setBounds(350,80,100,30);
			price.setBounds(460,80,100,30);
			final Button back = new Button("Back");
			
			frame.add(display);
			frame.add(uname);
			frame.add(id);
			frame.add(brand);
			frame.add(model);
			frame.add(price);
			
			
			for(WishlistEntity we:W) {
				VehicleEntity ve = (VehicleEntity) session.get(VehicleEntity.class,we.getVehicleId());
				Label name=new Label(we.getUsername());
				Label vid=new Label(""+ve.getVehicleId());
				Label vbrand=new Label(ve.getBrand());
				Label vmodel=new Label(ve.getModel());
				Label vprice=new Label(""+ve.getVehiclePrice());
				name.setBounds(20,x,100,30);
				vid.setBounds(130,x,100,30);
				vbrand.setBounds(240,x,100,30);
				vmodel.setBounds(350,x,100,30);
				vprice.setBounds(460,x,100,30);	
			x+=60;
			frame.add(name);
			frame.add(vid);
			frame.add(vbrand);
			frame.add(vmodel);
			frame.add(vprice);
			}
			
			
			
			back.setBounds(140, x+60, 80, 30);
			frame.add(back);
			frame.setSize(800,x+120);
			back.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					frame.dispose();
					AdminProfile ap=new AdminProfile();
					ap.Frame5();
				}
			});
			
	        
		}
}
