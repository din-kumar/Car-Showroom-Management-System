package Application;

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
import functions.VehicleFunction;
import functions.WishlistFunction;

public class Layout {
	
// hibernate 
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class).addAnnotatedClass(VehicleEntity.class).addAnnotatedClass(WishlistEntity.class).addAnnotatedClass(Sales.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    
    
	Frame frame;
	
	String temp1,temp2;
	long temp3;
	
	public Layout() {

		frame = new Frame();
		
		Display_Window1();
		
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
	public void Display_Window1(){

		Label select = new Label("Select User Type - ");
		select.setBounds(20, 90, 100, 30);

		final Choice user_type = new Choice();
		user_type.add("New User");
		user_type.add("Registered User");
		user_type.add("Admin");
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
			New_User();
			
		} 
		else if(index == 1) {
				welcome = new Label("Welcome User");
				Registered_User();
			} 
		else {
				welcome = new Label("Welcome Admin");
				Admin_User();
		}
			

		welcome.setBounds(200, 40, 200, 30);
		frame.add(welcome);
		

	}
// --------------------------- Third Part-1 Window ------------------------------
	public void New_User() {
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
		i_c_pass.setBounds(160, 400, 200, 30);
		i_pass.setEchoChar('*');
		
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
				temp1 = i_fname.getText() +i_lname.getText();
//				U.setName(temp1);
				
				temp1 = i_email.getText();
				U.setEmail(temp1);
				
				temp1 = i_adrs.getText();
				U.setHomeAdd(temp1);
				
				temp3 = Long.parseLong(i_mobile.getText());
				U.setPhoneNo(temp3);
				
				temp1 = i_u_id.getText();
				temp2 = i_c_uid.getText();
				Query q= session.createQuery("from UserEntity");
				List<UserEntity> u = q.list();
				if(temp1.equals(temp2)) {
					for(UserEntity i:u ) {
					if(temp1.equals(i.getUserName())) {
						new dialog("Username already exist");
						//NOt working
						//
						//
						//
						//
						//
					}
					else {
						U.setUserName(temp1);
						temp1 = i_pass.getText();
						temp2 = i_c_pass.getText();
						if(temp1.equals(temp2)) {
							U.setPassword(temp1);
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

				
				frame.setSize(500,300);
			
			}
		});
		
				
				back.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						frame.removeAll();
						Display_Window1();
					}
				});
	}

// ----------------------- Third Part 2 Window -----------------------------------


	public void Registered_User() {
		Session session = sf.openSession();
	    Transaction transaction = session.beginTransaction();
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
				Query q=session.createQuery("from UserEntity");
				List<UserEntity> u=q.list();
				for(UserEntity i:u) {
					if(i_u_id.getText().equals(i.getUserName())) {
						if(i_pass.getText().equals(i.getPassword())) {
							User_Profile(i);
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
		});
						
				back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						frame.removeAll();
						Display_Window1();
					}
				});

		
	}


public void Admin_User() {
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
						Admin_Profile();
					}
					else {
						//wrong password
					}
						
				}
				else {
					//wrong username
				}
				
			
			}
		});
						
				back.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						frame.removeAll();
						Display_Window1();
					}
				});

		
	}


// ----------------------- User Profile -----------------------------------
		public void User_Profile(UserEntity U){
		frame.removeAll();
			
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
			

// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
		//Button function
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.removeAll();
				if(c.getSelectedIndex()==0)
				     vehicleview(U);
				else {
					mywishlist(U);
				}
			}
		});
		Logout();
			
		frame.add(welcome);
		frame.add(c);
		frame.add(option);
		frame.add(submit);
	
			
		frame.setSize(600,400);
		
}
		//mywishlist
	    public void mywishlist(UserEntity U) {
			frame.removeAll();
			Session session = sf.openSession();
	        Transaction transaction = session.beginTransaction();
			Query q = session.createQuery("from WishlistEntity");
			List<WishlistEntity> W=q.list();
			
			Label display=new Label("List of Wishlisted Vehicles: ");
			display.setBounds(100, 40, 200, 30);
			int x=120;
			Label id=new Label("V Id");
			Label brand=new Label("Brand");
			Label model=new Label("Model ");
			Label price=new Label("Price");
			id.setBounds(20,80,100,30);
			brand.setBounds(130,80,100,30);
			model.setBounds(240,80,100,30);
			price.setBounds(350,80,100,30);
			for(WishlistEntity we: W) {
				if(U.getUserName().equals(we.getUsername()))
				{
					int b=we.getVehicleId();
					
					VehicleEntity ve=(VehicleEntity) session.get(VehicleEntity.class, b);
					Label vid=new Label(""+ve.getVehicleId());
					Label vbrand=new Label(ve.getBrand());
					Label vmodel=new Label(ve.getModel());
					Label vprice=new Label(""+ve.getVehiclePrice());
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
			}
			Label purchase = new Label("Enter Vid to Purchase ");
			purchase.setBounds(20,x+40,150,30);
			TextField input = new TextField();
			input.setBounds(180,x+40,50,30);
			Button ok = new Button("Ok");
			ok.setBounds(250,x+40,100,30);
			
			Button back = new Button("Back");
			back.setBounds(400,x+350, 100, 30);
			
			frame.add(display);
			frame.add(id);
			frame.add(brand);
			frame.add(model);
			frame.add(price);
			frame.add(purchase);
			frame.add(input);
			frame.add(ok);
			frame.add(back);
			frame.setSize(800,x+400);
			
			// Button function
			
			ok.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					new dialog("You Want to Buy this Vehicle",Integer.parseInt(input.getText()),U);
				}
			});
			
			back.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					frame.removeAll();
					User_Profile(U);
				}
			});
			
				
		}
		//Vehicleview to user
		public void vehicleview(UserEntity U) {
			Session session = sf.openSession();
		    Transaction transaction = session.beginTransaction();
			frame.removeAll();
			
	        
			Query q = session.createQuery("from VehicleEntity");
			List<VehicleEntity> V=q.list();
			Label display=new Label("List of Vehicles: ");
			display.setBounds(100, 40, 200, 30);
			int x=120;
			Label id=new Label("V Id");
			Label brand=new Label("Brand");
			Label model=new Label("Model ");
			Label price=new Label("Price");
			Label wishlist=new Label("Enter VId to add vehicle to Wishlist");
			TextField t=new TextField();
			id.setBounds(20,80,100,30);
			brand.setBounds(130,80,100,30);
			model.setBounds(240,80,100,30);
			price.setBounds(350,80,100,30);
			
			
			
			
			for(VehicleEntity ve:V) {
				if(ve.getVehicleStock()>0) {
				Label vid=new Label(""+ve.getVehicleId());
				Label vbrand=new Label(ve.getBrand());
				Label vmodel=new Label(ve.getModel());
				Label vprice=new Label(""+ve.getVehiclePrice());
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
			}
			wishlist.setBounds(30,x+80,250,30);
			t.setBounds(300,x+80,50,30);
			Button submit=new Button("Submit");
			submit.setBounds(360,x+80,100,30);
			final Button back = new Button("Back");
			back.setBounds(360, x+140, 100, 30);
			
			
			frame.add(display);
			frame.add(id);
			frame.add(brand);
			frame.add(model);
			frame.add(price);
			frame.add(wishlist);
			frame.add(t);
			frame.add(submit);
			frame.add(back);
			frame.setSize(800,x+400);
			
			
			//Button function
			submit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int vd=Integer.parseInt(t.getText());
					wishlist(U,vd);
					new dialog("Added Successfully");
				}
			});
			back.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					frame.removeAll();
					User_Profile(U);
				}
			});
		}
		
		
		//Wishlist Function
		public void wishlist(UserEntity u,int i) {
			Session session = sf.openSession();
		    Transaction transaction = session.beginTransaction();
			WishlistEntity W = new WishlistEntity();
			W.setUsername(u.getUserName());
			W.setVehicleId(i);
			session.save(W);
	        transaction.commit();
		}
		
// ----------------------- Admin Profile -----------------------------------
	public void Admin_Profile(){
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
						case 0 -> addvehicle();
						case 1 -> viewvehicleadmin();
						case 2 -> viewuser();
						case 3 -> Fullwishlist();
						case 4 -> Sales();
						}
					}

				});	
		Logout();
			
		frame.add(welcome);
		frame.add(option);
		frame.add(submit);
		frame.add(c);
		frame.setSize(600,400);
}
	//Sales
	public void Sales() {
		frame.removeAll();
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("from Sales");
        List<Sales> S=q.list();
        Label display=new Label("List of Vehicles Purchased by Users: ");
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
		
		
		for(Sales se:S) {
			VehicleEntity ve = (VehicleEntity) session.get(VehicleEntity.class,se.getVehicleId());
			Label name=new Label(se.getUsername());
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
				frame.removeAll();
				Admin_Profile();
			}
		});
	}
	
	
	//fullwishlisttoadmin
	public void Fullwishlist() {
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
				frame.removeAll();
				Admin_Profile();
			}
		});
		
        
	}
	
	
	//add vehicle
	public void addvehicle() {
		frame.removeAll();
		Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
		VehicleEntity V = new VehicleEntity();
		
		Label display=new Label("Add Vehicle Details ");
		display.setBounds(100, 40, 200, 30);
		Label vbrand=new Label(" Vehicle Brand :");
		vbrand.setBounds(20,80,100,30);
		TextField brand=new TextField();
		brand.setBounds(150,80,200,30);
		Label vmodel=new Label(" Vehicle Model :");
		vmodel.setBounds(20,120,100,30);
		TextField model=new TextField();
		model.setBounds(150,120,200,30);
		Label vprice=new Label(" Vehicle Price :");
		vprice.setBounds(20,160,100,30);
		TextField price=new TextField();
		price.setBounds(150,160,200,30);
		Label vstock=new Label(" No. of Vehicles :");
		vstock.setBounds(20,200,100,30);
		TextField stock=new TextField();
		stock.setBounds(150,200,200,30);
		Button submit=new Button("Submit");
		submit.setBounds(150,250,100,30);
		final Button back = new Button("Back");
		back.setBounds(300, 250, 100, 30);
		
		//Button function
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VehicleEntity V=new VehicleEntity();
				
				V.setBrand(brand.getText());
				V.setModel(model.getText());
				V.setVehiclePrice(Double.parseDouble(price.getText()));
				V.setVehicleStock(Integer.parseInt(stock.getText()));
				
				session.save(V);
				transaction.commit();
				//added successfully
				new dialog("Added Successfully");
				
			}

		});	
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.removeAll();
				Admin_Profile();
			}
		});
		
		frame.add(display);
		frame.add(vbrand);
		frame.add(brand);
		frame.add(vmodel);
		frame.add(model);
		frame.add(vprice);
		frame.add(price);
		frame.add(vstock);
		frame.add(stock);
		frame.add(submit);
		frame.add(back);
		frame.setSize(800,600);
		
	}
	
	//viewvehicleadmin
	public void viewvehicleadmin() {
		frame.removeAll();
		Session session = sf.openSession();
	    Transaction transaction = session.beginTransaction();
		frame.removeAll();
		
        
		Query q = session.createQuery("from VehicleEntity");
		List<VehicleEntity> V=q.list();
		Label display=new Label("List of Vehicles: ");
		display.setBounds(100, 40, 200, 30);
		int x=120;
		Label id=new Label("V Id");
		Label brand=new Label("Brand");
		Label model=new Label("Model ");
		Label price=new Label("Price");
		Label stock=new Label("Stock");
		id.setBounds(20,80,100,30);
		brand.setBounds(130,80,100,30);
		model.setBounds(240,80,100,30);
		price.setBounds(350,80,100,30);
		stock.setBounds(460,80,100,30);
		for(VehicleEntity ve:V) {
			Label vid=new Label(""+ve.getVehicleId());
			Label vbrand=new Label(ve.getBrand());
			Label vmodel=new Label(ve.getModel());
			Label vprice=new Label(""+ve.getVehiclePrice());
			Label vstock=new Label(""+ve.getVehicleStock());
			vid.setBounds(20,x,100,30);
			vbrand.setBounds(130,x,100,30);
			vmodel.setBounds(240,x,100,30);
			vprice.setBounds(350,x,100,30);	
			vstock.setBounds(460,x,100,30);
		x+=60;
		frame.add(vid);
		frame.add(vbrand);
		frame.add(vmodel);
		frame.add(vprice);
		frame.add(vstock);
		}
		final Button back = new Button("Back");
		back.setBounds(140, x+60, 80, 30);
		
		
		
		frame.add(display);
		frame.add(id);
		frame.add(brand);
		frame.add(model);
		frame.add(price);
		frame.add(stock);
		frame.add(back);
		frame.setSize(800,x+120);
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				frame.removeAll();
				Admin_Profile();
			}
		});
		
	}
	
	//viewusers
	public void viewuser() {
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
				frame.removeAll();
				Admin_Profile();
			}
		});
        
        
	}
	
// ------------------------------ Log out -------------------------------------
	public void Logout(){
		Button logout = new Button("Log Out");
		logout.setBounds(120,350,100,30);
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame.removeAll();
				Display_Window1();
					
				}
		});
			
		frame.add(logout);
			
			
	}
	
	
	
// ---------------------------- main method -----------------------	
//	public static void main(String[] args) {
//
//		Layout layout = new Layout();
//	}
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