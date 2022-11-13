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


public class Layout {
	
// hibernate 
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    
    
	Frame frame;
	
	
	public void Frame1() {

		frame = new Frame();
		
		Display_Window1(frame);
		
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
	
// -------------------- First Window ---------------------------------------
	public void Display_Window1(Frame frame){

		Label select = new Label("Select User Type - ");
		select.setBounds(20, 90, 100, 30);

		final Choice user_type = new Choice();
		user_type.add("New User");
		user_type.add("User Login");
		
		user_type.setBounds(130, 90, 150, 30);

		Button ok = new Button("Select");
		ok.setBounds(300, 90, 50, 30);

//************************** ADD *****************************************
		frame.add(select);
		frame.add(user_type);
		frame.add(ok);

//*************************Frame size ****************************************
		frame.setSize(400, 200);

// *******************************Button function *************************

		ok.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				Display_Window2(user_type);
				

			}
		});
	}
// --------------------------------------- Second Window --------------------------------------------

	public void Display_Window2(Choice user_type) {
		Label welcome = null;

		frame.removeAll();

		

		int index = user_type.getSelectedIndex();
		if (index == 0) {
			welcome = new Label("Welcome New User");
			NewUser nu=new NewUser();
			nu.New_User(frame);
			
		} 
		else {
				welcome = new Label("Welcome User");
				Login ru = new Login();
				ru.Registered_User(frame);
			} 
		welcome.setBounds(200, 40, 200, 30);
		frame.add(welcome);
	}
}
//Dialog

class dialog {
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    
	
	private static Dialog d;  
    dialog(String s) {  
        Frame frame= new Frame();  
        d = new Dialog(frame , "Successful", true);  
        d.setLayout( new FlowLayout() );  
        Button b = new Button ("OK");  
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                dialog.d.setVisible(false);  
            }  
        });  
        d.add( new Label (s));  
        d.add(b);   
        d.setSize(300,300);    
        d.setVisible(true); 
        
    }
    dialog(String s,int i,UserEntity U){
    	
    	Frame frame = new Frame();
    	d = new Dialog(frame , "Confirmation", true);  
        d.setLayout( new FlowLayout() );  
        Button b1 = new Button ("Buy");
        Button b2 = new Button("Cancel");
        b1.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
            	Session session = sf.openSession();
    		    Transaction transaction = session.beginTransaction();
                VehicleEntity ve=(VehicleEntity) session.get(VehicleEntity.class,i);
                int var = ve.getVehicleStock();
                if(var -1== -1) {
                	dialog.d.setVisible(false);
                	new dialog("Sorry The item Is not in Stock");
                }
                else {                	
                	ve.setVehicleStock(var-1);
                	Sales s=new Sales();
                	s.setVehicleId(i);
                	s.setUsername(U.getUserName());
                
                	session.save(ve);
                	session.save(s);
                	transaction.commit();
                	dialog.d.setVisible(false);
                	new dialog("Thanks for the purchase");
                	
                }
               
               
            }  
        });  
        b2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.d.setVisible(false);
				
	               
			}
		});
        
        d.add( new Label (s));  
        d.add(b1);   
        d.add(b2);
        d.setSize(300,300);    
        d.setVisible(true); 
        }
}
