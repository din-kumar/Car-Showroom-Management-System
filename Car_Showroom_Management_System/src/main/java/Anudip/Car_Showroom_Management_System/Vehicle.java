package Anudip.Car_Showroom_Management_System;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {
	
	private String V_Brand;
	private String V_Model;
	@Id
	private int V_Number;
	private double V_Price;
	private int V_stock;
	
	


	@Override
	public String toString() {
		return "Vehicle [V_Brand=" + V_Brand + ", V_Model=" + V_Model + ", V_Number=" + V_Number + ", V_Price="
				+ V_Price + ", V_stock=" + V_stock + "]";
	}




	public String getV_Brand() {
		return V_Brand;
	}




	public void setV_Brand(String v_Brand) {
		V_Brand = v_Brand;
	}




	public String getV_Model() {
		return V_Model;
	}




	public void setV_Model(String v_Model) {
		V_Model = v_Model;
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




	public int getV_stock() {
		return V_stock;
	}




	public void setV_stock(int v_stock) {
		V_stock = v_stock;
	}




	public Vehicle() {
		// TODO Auto-generated constructor stub
	}

}
