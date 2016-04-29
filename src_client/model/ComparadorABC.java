package model;

import java.util.Comparator;

import model.struct.users.PublicUser;

public class ComparadorABC implements Comparator<PublicUser> {
	public int compare(PublicUser u1, PublicUser u2){
		return u1.getName().compareTo(u2.getName());
	}
}
