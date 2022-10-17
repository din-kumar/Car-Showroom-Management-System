package Application;

public class Vehicle {
	private String brand;
	private String model;
	private int vehicleId;
	private double vehiclePrice;
	private int vehicleStock;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public double getVehiclePrice() {
		return vehiclePrice;
	}
	public void setVehiclePrice(double vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}
	public int getVehicleStock() {
		return vehicleStock;
	}
	public void setVehicleStock(int vehicleStock) {
		this.vehicleStock = vehicleStock;
	}
	@Override
	public String toString() {
		return "Vehicle [brand=" + brand + ", model=" + model + ", vehicleId=" + vehicleId + ", vehiclePrice="
				+ vehiclePrice + ", vehicleStock=" + vehicleStock + "]";
	}
	
}
