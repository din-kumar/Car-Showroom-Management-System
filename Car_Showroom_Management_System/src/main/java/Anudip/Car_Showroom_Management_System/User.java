package Anudip.Car_Showroom_Management_System;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	private int U_Id;
	private String U_Name;

	
	public User() {
		// TODO Auto-generated constructor stub
	}

}
