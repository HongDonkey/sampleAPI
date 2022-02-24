package com.example.demo.info;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.info.model.Sample;
import com.example.demo.info.model.Sample.Samples;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InfoController {
//	FileWriter writer = null;

	@GetMapping("/info")
	public Object projectInfo() {
		log.debug("/info start");

		Sample s = new Sample();
		Samples sp = s.new Samples();
		sp.firstName = "Ki";
		sp.lastName = "Hong";
		sp.id = "HDK";

		log.info("return {}", sp.toString());
		return sp;
	}

	@GetMapping("/info2")
	public Object projectInfo2() {
		log.debug("/info2 start");
		JsonObject jo = new JsonObject();
		String Alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		jo.addProperty("firstName", "first");
		jo.addProperty("lastName", "last");

		JsonArray ja = new JsonArray();
		for (int i = 1; i <= Alpha.length(); i++) {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("id", Alpha.substring(i - 1, i));
			ja.add(jObj);
		}

		jo.add("Alphabet", ja);

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(jo);

//		try {
//			writer = new FileWriter("C:\\Users\\pharos_1\\Documents\\code\\export_tool\\json.txt");
//			writer.write(json);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} finally {
//			try {
//				writer.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		return jo.toString();
	}

}
