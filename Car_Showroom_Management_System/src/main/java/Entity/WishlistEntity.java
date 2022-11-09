package Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity

public class WishlistEntity {

private int vehicleId;
@Id
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
