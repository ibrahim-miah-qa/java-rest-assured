package light.core.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class RequestMaker {
	String base_url = "";
	String segment = "";
	Request request;
	OkHttpClient client = new OkHttpClient();
	
	protected RequestMaker() { }
	
	
	public RequestMaker url(String url) {
		this.segment = url;
		return this;
	}
	
	public void make() {
		request = new Request
			.Builder()
			.url(base_url + segment)
			.build();
		
		try {
			Response response = client.newCall(request).execute();
			System.out.println(response.body().string());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static class Builder {
		private String base_url = "";
		
		public Builder setBaseUrl(String url) {
			this.base_url = url;
			return this;
		}
		
		public RequestMaker build() {
			RequestMaker requestMaker = new RequestMaker();
			requestMaker.base_url = this.base_url;
			
			return requestMaker;
		}
	}
}
