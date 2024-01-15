package com.globalids.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalids.forms.Application;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AppHttp extends GidHttp{
	private OkHttpClient client = new OkHttpClient();
	
	private enum Segments {
		ALL_APP("/api/users?page=2");
		
		String url;
		
		Segments(String url) {
			this.url = url;
		}
		
		
	};
	
//	{
//		client = new OkHttpClient();
//	}
	
	public List<Application> getApplication() {
		OkHttpClient client = new OkHttpClient();
		try {
			Response response = client
				.newCall(new Request
					.Builder()
					.url("https://reqres.in/api/users?page=2")
					.build())
				.execute();
			
			String body = response.body().string();
			
			if(body != null) {
				ObjectMapper objectMapper = new ObjectMapper()
					.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				
				JsonNode root = objectMapper.readTree(body);
				JsonNode data = root.get("data");
				List<Application> apps = objectMapper.readValue(data.toString(), new TypeReference<List<Application>>() {});
				return apps;
			} else{
				throw new Exception("Invalid Response!");
			}
		} catch (IOException e) {
			System.out.println("Request make error!");
			e.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return new ArrayList<>();
	}
}
