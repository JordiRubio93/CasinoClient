package model.struct.users;
import java.util.Date;

public class PrivateUser extends PublicUser {
	private double id;
	private String password;
	private Date birthday;
	private float money;
	
	public PrivateUser(String avatar, String name, int age, char gender, String nationality, String password, Date birthday, float money) {
		super(avatar, name, age, gender, nationality);
		this.password = password;
		this.birthday = birthday;
		this.money = money;
		this.id = Math.random();
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public double getId() {
		return id;
	}
	public void setId(double id) {
		this.id = id;
	}
}
