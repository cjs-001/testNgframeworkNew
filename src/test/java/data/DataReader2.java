package data;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class DataReader2 {

	
	public static JSONArray datatoobject () throws IOException, ParseException {
		
		JSONParser jsonParser = new JSONParser();
		FileReader reader = new FileReader("C:\\charan's\\Jpractice\\Selenium\\selenium\\src\\test\\java\\jsondata\\data2.json");
		Object obj = jsonParser.parse(reader);

        // Convert the parsed object to JSONObject
        JSONArray Object = (JSONArray) obj;
        return Object;
	}
}
