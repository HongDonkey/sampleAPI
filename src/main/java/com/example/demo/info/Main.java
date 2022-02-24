package com.example.demo.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class Main {
	
	private HttpURLConnection http;
	
	public Main(HttpURLConnection http) {
		this.http = http;
	}
	
	public void request(String method, String headerName, String headerValue, JSONObject jsonData) throws IOException{
		http.setRequestMethod(method);
		http.setRequestProperty(headerName, headerValue);
		
		http.setDoOutput(true);
		
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(http.getOutputStream()));
		printWriter.write(jsonData.toString());
		printWriter.flush();
	}
	
	public String response() throws IOException {
		BufferedReader bufferedReader = null;
		
		int status = http.getResponseCode();
		
		if(status == HttpURLConnection.HTTP_OK) {
			bufferedReader = new BufferedReader(new InputStreamReader(http.getInputStream()));
		} else {
			bufferedReader = new BufferedReader(new InputStreamReader(http.getErrorStream()));
		}
		String line;
		StringBuffer response = new StringBuffer();
		
		while((line = bufferedReader.readLine()) != null) {
			response.append(line);
		}
		bufferedReader.close();
		
		JSONObject jsonObject = new JSONObject(response.toString());
		
		System.out.println("response : " + jsonObject);
		
		return jsonObject.toString();
	}
	public static void main(String[] args) {
		try {
			URL url = new URL("http://localhost:8080/api/study");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			
			Main main = new Main(http);
			
			JSONObject jsonData = new JSONObject();
			jsonData.put("idx", 12345);
			jsonData.put("title", "제목");
			jsonData.put("content", "내용");
			jsonData.put("writer", "작성자");
			
			main.request("POST", "Content-Type", "application/json;charset=UTF-8", jsonData);
			main.response();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
