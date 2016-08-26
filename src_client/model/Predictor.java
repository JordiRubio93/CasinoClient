/**
 * @author
 * Pol Vales - ls30599@salleurl.edu
 * Enric Marin - ls31308@salleurl.edu
 * Diego Bellino - ls30741@salleurl.edu
 * Jordi Rubio - ls31289@salleurl.edu
 * David Estepa - ls30622@salleurl.edu
 * DPO2 (Disseny i programacio orientats a objectes)
 * La Salle, Universitat Ramon Llull
 */

package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import model.struct.horses.HorseData;
import controller.FileManager;
import controller.HorsesExecutor;
import controller.RouletteExecutor;

/**
 * The Class Predictor.
 * (Funcionalitat addicional que prediu el nombre/cavall guanyador de la partida, tot i que només encerta quan el guanyador ja està disponible.)
 */
public class Predictor {
	private HorsesExecutor horsesExecutor;
	private RouletteExecutor rouletteExecutor;
	private LinkedList<HorseData> list;
	private static final int max = 37;

	/**
	 * Instantiates a new predictor.
	 *
	 * @param rouletteExecutor
	 */
	public Predictor(RouletteExecutor rouletteExecutor) {
		this.rouletteExecutor = rouletteExecutor;
	}

	/**
	 * Instantiates a new predictor.
	 *
	 * @param horsesExecutor
	 */
	public Predictor(HorsesExecutor horsesExecutor) {
		list = (LinkedList<HorseData>) new FileManager().getHorsesList();
		this.horsesExecutor = horsesExecutor;
	}

	/**
	 * Predict winner number.
	 *
	 * @return string
	 */
	public String predictWinnerNumber() {
		if (rouletteExecutor.getNumber() == null) {
			int[] nums = new int[3];
			nums[0] = new Random().nextInt(max);
			do {
				nums[1] = new Random().nextInt(max);
			} while (nums[0] == nums[1]);
			do {
				nums[2] = new Random().nextInt(max);
			} while (nums[1] == nums[2] || nums[1] == nums[2]);
			Collections.shuffle(Arrays.asList(nums));
			return "The winner number may be " + nums[0] + ", " + nums[1] + " or " + nums[2] + " ";
		} else {
			int[] nums = new int[3];
			nums[0] = Integer.parseInt(rouletteExecutor.getNumber());
			do {
				nums[1] = new Random().nextInt(max);
			} while (nums[0] == nums[1]);
			do {
				nums[2] = new Random().nextInt(max);
			} while (nums[1] == nums[2] || nums[1] == nums[2]);
			Collections.shuffle(Arrays.asList(nums));
			return "The winner number may be " + nums[0] + ", " + nums[1] + " or " + nums[2] + " ";
		}
	}

	/**
	 * Predict winner horse.
	 *
	 * @return string
	 */
	public String predictWinnerHorse() {
		Collections.shuffle(list);
		if (horsesExecutor.getHorse() == null) {
			String[] horses = new String[2];
			horses[0] = list.get(new Random().nextInt(12)).getName();
			do {
				horses[1] = list.get(new Random().nextInt(12)).getName();
			} while (horses[0].equals(horses[1]));
			Collections.shuffle(Arrays.asList(horses));
			return "The winner horse may be " + horses[0] + " or " + horses[1] + " ";
		} else {
			String[] horses = new String[2];
			horses[0] = horsesExecutor.getHorse();
			do {
				horses[1] = list.get(new Random().nextInt(12)).getName();
			} while (horses[0].equals(horses[1]));
			Collections.shuffle(Arrays.asList(horses));
			return "The winner horse may be " + horses[0] + " or " + horses[1] + " ";
		}
	}
}
