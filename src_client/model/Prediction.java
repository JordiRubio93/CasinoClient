package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import model.struct.horses.HorseData;
import controller.FileManager;
import controller.HorsesExecutor;
import controller.RouletteExecutor;

public class Prediction {
	private HorsesExecutor horsesExecutor;
	private RouletteExecutor rouletteExecutor;
	private LinkedList<HorseData> list;
	private static final int max = 37;
	
	public Prediction(RouletteExecutor rouletteExecutor){
		this.rouletteExecutor = rouletteExecutor;
	}
	
	public Prediction(HorsesExecutor horsesExecutor) {
		list = (LinkedList<HorseData>) new FileManager().getHorsesList();
		this.horsesExecutor = horsesExecutor;
	}

	public String predictWinnerNumber(){
		if(rouletteExecutor.getNumber() == null){
			int[] nums = new int[3];
			nums[0] = new Random().nextInt(max);
			do{
				nums[1] = new Random().nextInt(max);
			}while(nums[0] == nums[1]);
			do{
				nums[2] = new Random().nextInt(max);
			}while(nums[1] == nums[2] || nums[1] == nums[2]);
			Collections.shuffle(Arrays.asList(nums));
			return "The winner number may be " + nums[0] + ", " + nums[1] + " or " + nums[2] + " ";
		}else{
			int[] nums = new int[3];
			nums[0] = Integer.parseInt(rouletteExecutor.getNumber());
			do{
				nums[1] = new Random().nextInt(max);
			}while(nums[0] == nums[1]);
			do{
				nums[2] = new Random().nextInt(max);
			}while(nums[1] == nums[2] || nums[1] == nums[2]);
			Collections.shuffle(Arrays.asList(nums));
			return "The winner number may be " + nums[0] + ", " + nums[1] + " or " + nums[2] + " ";
		}
	}
	
	public String predictWinnerHorse(){
		Collections.shuffle(list);
		if(horsesExecutor.getHorse() == null){
			String[] horses = new String[2];
			horses[0] = list.get(new Random().nextInt(12)).getName();
			do{
				horses[1] = list.get(new Random().nextInt(12)).getName();
			}while(horses[0].equals(horses[1]));
			Collections.shuffle(Arrays.asList(horses));
			return "The winner horse may be " + horses[0] + " or " + horses[1] + " ";
		}else{
			String[] horses = new String[2];
			horses[0] = horsesExecutor.getHorse();
			do{
				horses[1] = list.get(new Random().nextInt(12)).getName();
			}while(horses[0].equals(horses[1]));
			Collections.shuffle(Arrays.asList(horses));
			return "The winner horse may be " + horses[0] + " or " + horses[1] + " ";
		}
	}
}
