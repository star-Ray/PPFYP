package system;

import java.text.SimpleDateFormat;

import org.json.simple.parser.JSONParser;

public class Config {
	public static final JSONParser JPARSER = new JSONParser();
	public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	//TODO change to china?
	public static final String TIMEZONE = "Singapore";

	
}
