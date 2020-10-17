package sa;

public class SpeedRecord {
	private String licencePlate;
	private int speed;
	public SpeedRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SpeedRecord(String licencePlate, int speed) {
		super();
		this.licencePlate = licencePlate;
		this.speed = speed;
	}
	public String getLicencePlate() {
		return licencePlate;
	}
	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	

}
