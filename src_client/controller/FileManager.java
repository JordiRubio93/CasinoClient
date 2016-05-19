package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import model.struct.user.LoginInfo;
import tools.excepcions.FileException;

public class FileManager {
	private final String[] param = { "IP_SBD", "PORT_Client" };
	private final String ruta = "client.json";
	private Gson gson;
	private BufferedReader br = null;
	private JsonObject objecte = null;
	private ConfigurationFile cf = null;
	private FileWriter fileWriter;

	public FileManager() {
		gson = new GsonBuilder().create();
	}

	public ConfigurationFile obtenirConfiguracio(String rute) throws FileException {
		validarConfiguracio(cf = carregarConfiguracio(rute));
		return cf;
	}

	public LoginInfo carregarDades() {
		// Obtenemos los datos!
		BufferedReader br = null;
		LoginInfo objecte = null;
		try {
			br = new BufferedReader(new FileReader(ruta));
			objecte = (gson.fromJson(br, LoginInfo.class));
			
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (br!=null) br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return objecte;
	}

	private void validarConfiguracio(ConfigurationFile cf) throws FileException {
		cf.isValidPort();
		if (!cf.getIP_SDB().toLowerCase().equals("localhost"))
			cf.isValidIPV4();
	}

	public ConfigurationFile carregarConfiguracio(String rute) throws FileException {
		try {
			br = new BufferedReader(new FileReader(rute));
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

	public void saveLoginInfo(LoginInfo li) {
		if(!li.getEmail().equals("")){
			String json = new Gson().toJson(li);
			try {
				fileWriter = new FileWriter(ruta);
				fileWriter.write(json);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fileWriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void logout() {
		try{
    		File file = new File("client.json");
      		if(file.delete()) System.out.println(file.getName() + " is deleted!");
    		else System.out.println("Delete operation is failed.");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
}
