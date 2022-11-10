package Application;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Entity.UserEntity;

public class Layout {
	
// hibernate 
	Configuration con = new Configuration().configure().addAnnotatedClass(UserEntity.class);
    ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry(); 
    SessionFactory sf = con.buildSessionFactory(reg);
    Session session = sf.openSession();
    Transaction transaction = session.beginTransaction();
    
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
				if(temp1.equals(temp2)) {
					U.setUserName(temp1);
				}
				else {
					// user name miss match
				}
				
				temp1 = i_pass.getText();
				temp2 = i_c_pass.getText();
				if(temp1.equals(temp2)) {
					U.setPassword(temp1);
				}
				else {
					// password missmatch
				}
//7482758245827525987298572875915987517578509149174987149879847198477164981749874398714987134908109847198749182740
				// check alll the feilds completed and then submit save
				session.save(U);
				transaction.commit();
				
				// go to regisered user page
				frame.removeAll();
				Label thank = new Label("Your Response Submitted Successfuly");
				thank.setBounds(20, 40,	250, 30);
				back.setBounds(140, 160, 80, 30);
				
							
				frame.add(thank);
				frame.add(back);
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
// ******************* Label ************************************
		Label u_id = new Label("User Id");
		u_id.setBounds(20, 80, 100, 30);

		Label pass = new Label("Password");
		pass.setBounds(20, 120, 100, 30);
		
		TextField i_u_id = new TextField();
		i_u_id.setBounds(160, 80, 200, 30);
		
		TextField i_pass = new TextField();
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
				// add data to the database(not done)
		
			
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
		
		TextField i_u_id = new TextField();
		i_u_id.setBounds(160, 80, 200, 30);
		
		TextField i_pass = new TextField();
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
				// add data to the database(not done)
				
			
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
		public void User_Profile(){
		frame.removeAll();
			
		Label welcome = new Label("Welcome");
		welcome.setBounds(100, 40, 100, 30);
			
		Label u_id = new Label("User Id ");
		u_id.setBounds(20,80,100,30);
			
		Label name = new Label("Name ");
		name.setBounds(20,120,100,30);
			
		Label mobile = new Label("Mobile no. ");
		mobile.setBounds(20,160,100,30);
			
// $$$$$$$$$$$$$$$$$$$$$$$ Fetch from data base $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
		Label op_u_id = new Label(/*fetch from database */);
		op_u_id.setBounds(140, 80, 100, 30);
			
		Label op_name = new Label();
		op_name.setBounds(140,120,100,30);
			
		Label op_mobile = new Label();
		op_mobile.setBounds(140,160,100,30);
		// also describe other entities to add
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
			
		Logout();
			
		frame.add(welcome);
		frame.add(u_id);
		frame.add(name);
		frame.add(mobile);
		frame.add(op_u_id);
		frame.add(op_name);
		frame.add(op_mobile);
			
		frame.setSize(300,400);
		
}
// ----------------------- Admin Profile -----------------------------------
	public void Admin_Profile(){
		frame.removeAll();
			
		Label welcome = new Label("Welcome");
		welcome.setBounds(80, 40, 100, 30);
			
		Label u_id = new Label("User Id ");
		u_id.setBounds(20,80,100,30);
			
		Label name = new Label("Name ");
		name.setBounds(20,120,100,30);
			
		Label mobile = new Label("Mobile no. ");
		mobile.setBounds(20,160,100,30);
		
// $$$$$$$$$$$$$$$$$$$$$$$ Fetch from data base $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
		Label op_u_id = new Label(/*fetch from database */);
		op_u_id.setBounds(140, 80, 100, 30);
			
		Label op_name = new Label();
		op_name.setBounds(140,120,100,30);
			
		Label op_mobile = new Label();
		op_mobile.setBounds(140,160,100,30);
			
// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$		
			
		Logout();
			
		frame.add(welcome);
		frame.add(u_id);
		frame.add(name);
		frame.add(mobile);
		frame.add(op_u_id);
		frame.add(op_name);
		frame.add(op_mobile);
		
		frame.setSize(300,400);
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
