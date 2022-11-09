package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class WishlistEntity {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int Id;
private int vehicleId;
private String username;
public int getVehicleId() {
	return vehicleId;
}
public void setVehicleId(int vehicleId) {
	this.vehicleId = vehicleId;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}

}
