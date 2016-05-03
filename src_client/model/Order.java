package model;

import com.google.common.collect.Ordering;

import model.struct.horses.HorseData;

public class Order extends Ordering<HorseData> {
	@Override
	public int compare(HorseData h1, HorseData h2) {
		return ((Integer)h1.getSegons()).compareTo((Integer)h2.getSegons());
	}
}
