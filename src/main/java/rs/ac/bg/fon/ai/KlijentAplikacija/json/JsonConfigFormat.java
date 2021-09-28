package rs.ac.bg.fon.ai.KlijentAplikacija.json;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rs.ac.bg.fon.ai.BibliotekaAplikacija.util.PropertyConst;

public class JsonConfigFormat {

	private String url;
	private int port;
	
	public JsonConfigFormat() {
	}

	public JsonConfigFormat(String url, int port) {
		this.url = url;
		this.port = port;
	}
	
	public static JsonConfigFormat readFromFile() throws Exception {
		try (FileReader in = new FileReader(PropertyConst.FILE_JSON)){
			Gson gson = new Gson();
			JsonConfigFormat obj = gson.fromJson(in, JsonConfigFormat.class);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Greska pri citanju json fajla");
		}
	}
	
	public void writeToFile() throws Exception{
		try (FileWriter out = new FileWriter(PropertyConst.FILE_JSON)){
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			gson.toJson(this, out);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Greska pri pisanju json fajla");
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
