package Anudip.Car_Showroom_Management_System;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {
	
	private String V_Brand;
	private String V_Name;
	@Id
	private int V_Number;
	private double V_Price;
	
	

	public String getV_Brand() {
		return V_Brand;
	}



	public void setV_Brand(String v_Brand) {
		V_Brand = v_Brand;
	}



	public String getV_Name() {
		return V_Name;
	}



	public void setV_Name(String v_Name) {
		V_Name = v_Name;
	}



	public int getV_Number() {
		return V_Number;
	}



	public void setV_Number(int v_Number) {
		V_Number = v_Number;
	}



	public double getV_Price() {
		return V_Price;
	}



	public void setV_Price(double v_Price) {
		V_Price = v_Price;
	}



	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

}
