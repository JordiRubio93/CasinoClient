package model.struct.user;
import java.util.LinkedList;

import model.struct.bet.Bet;

public class PublicUser {
	protected String avatar;
	protected String name;
	protected int age;
	protected char gender;
	protected String nationality;
	protected LinkedList<Bet> bets;

	public PublicUser(String avatar, String name, int age, char gender, String nationality) {
		this.avatar = avatar;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.nationality = nationality;
	}
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public LinkedList<Bet> getBets() {
		return bets;
	}
	public void setBets(LinkedList<Bet> bets) {
		this.bets = bets;
	}
}
