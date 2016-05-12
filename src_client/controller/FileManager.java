package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import tools.excepcions.FileException;

public class FileManager {
	private final String[] param = { "IP_SBD", "PORT_Client" };
	
	private Gson gson;
	private BufferedReader br = null;
	private JsonObject objecte = null;
	private ConfigurationFile cf = null;
	public FileManager() {
		gson = new GsonBuilder().create();
	}

	public ConfigurationFile obtenirConfiguracio(String ruta) throws FileException {
		validarConfiguracio(cf = carregarConfiguracio(ruta));
		return cf;
	}

	private void validarConfiguracio(ConfigurationFile cf) throws FileException {
		cf.isValidPort();
		if(!cf.getIP_SDB().toLowerCase().equals("localhost")) cf.isValidIPV4();
	}

	private ConfigurationFile carregarConfiguracio(String ruta) throws FileException {
		try {
			gson = new GsonBuilder().create();
			br = new BufferedReader(new FileReader(ruta));
			objecte = (gson.fromJson(br, JsonObject.class)).getAsJsonObject("configuration");
		} catch (FileNotFoundException e1) {
			throw new FileException("File Not Found");
		} catch (NullPointerException e1) {
			throw new FileException("NullPointerException");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ConfigurationFile((objecte).get(param[0]).getAsString(), (objecte).get(param[1]).getAsInt());
	}
}
