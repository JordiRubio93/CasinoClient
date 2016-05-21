package model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import model.struct.horses.HorseData;

public class JsonExecutor {
	private static LinkedList<HorseData> hdList;
	
	public static boolean llegeixFitxer(String ruta){
		Gson gson = new GsonBuilder().create();
		JsonReader reader;
		hdList = new LinkedList<HorseData>();
		
		try {
			reader = new JsonReader(new FileReader(ruta));
			JsonArray array = gson.fromJson(reader, JsonArray.class);

			int index = 1;
			for (JsonElement element : array) {
				String name = element.getAsJsonObject().get("name").getAsString();
				String color = element.getAsJsonObject().get("color").getAsString();
				
				HorseData hd = new HorseData(name, color, index);
				
				hdList.add(hd);
				index++;
			}
		} catch (FileNotFoundException | JsonSyntaxException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static LinkedList<HorseData> getList() {
		return hdList;
	}
}
