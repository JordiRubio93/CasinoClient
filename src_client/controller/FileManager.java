package controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Base64;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import model.struct.horses.HorseData;
import model.struct.user.LoginInfo;
import tools.excepcions.FileException;

public class FileManager {
	private static LinkedList<HorseData> hdList;
	private final String[] param = { "IP_SBD", "PORT_Client" };
	private final String user = "client.dat";
	private final String horses = "horses.txt";
	private Gson gson;
	private BufferedReader br = null;
	private JsonObject objecte = null;
	private ConfigurationFile cf = null;

	public FileManager() {
		gson = new GsonBuilder().create();
	}

	public ConfigurationFile obtenirConfiguracio(String rute) throws FileException {
		validarConfiguracio(cf = carregarConfiguracio(rute));
		return cf;
	}

	public LoginInfo carregarDades() {
		// Obtenemos los datos!
		FileInputStream fileIn = null;
		ObjectInputStream in = null;
		LoginInfo objecte = null;
		try {
			fileIn = new FileInputStream(user);
			in = new ObjectInputStream(fileIn);

			String str = deserialize(in);
			String[] split = str.split("@@");

			objecte = new LoginInfo(split[0], split[1], true);
		} catch (Exception e) {
			return null;
		} finally {
			try {
				if (in != null)
					in.close();
				if (fileIn != null)
					fileIn.close();
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
		if (!li.getEmail().equals("")) {
			FileOutputStream fileOut = null;
			ObjectOutputStream out = null;
			try {
				fileOut = new FileOutputStream(user);
				out = new ObjectOutputStream(fileOut);
				serialize(li.toString(), out);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (out != null)
						out.close();
					if (fileOut != null)
						fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void logout() {
		try {
			File file = new File(user);
			if (file.delete())
				System.out.println(file.getName() + " is deleted!");
			else
				System.out.println("Delete operation is failed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void serialize(String str, OutputStream outputStream) {
		try {
			OutputStream wrappedOS = Base64.getEncoder().wrap(outputStream);
			wrappedOS.write(str.toString().getBytes("utf-8"));
		} catch (IOException e) {
		}
	}

	public static String deserialize(InputStream inputStream) {
		try {

			InputStream unWrappedIS = Base64.getDecoder().wrap(inputStream);
			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[100];

			while ((nRead = unWrappedIS.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();

			return new String(buffer.toByteArray(), "utf-8");
		} catch (IOException e) {
		}
		return null;
	}

	public LinkedList<HorseData> getHorsesList() {
		JsonReader reader = null;
		LinkedList<HorseData> hdList = new LinkedList<HorseData>();
		try {
			reader = new JsonReader(new FileReader(horses));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		JsonArray array = gson.fromJson(reader, JsonArray.class);

		int index = 1;
		for (JsonElement element : array) {
			String name = element.getAsJsonObject().get("name").getAsString();
			String color = element.getAsJsonObject().get("color").getAsString();
			HorseData hd = new HorseData(name, color, index); 
			// TODO treure  aixo de aqui
			hdList.add(hd);
			index++;
		}
		return hdList;
	}

	public static LinkedList<HorseData> getList() {
		return hdList;
	}
}
