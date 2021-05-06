package no.hvl.dat110.aciotdevice.client;

import java.io.IOException;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RestClient {

	public RestClient() {
		// TODO Auto-generated constructor stub
	}

	private static String logpath = "/accessdevice/log/";
	public static final MediaType JSON
		= MediaType.parse("application/json; charset=utf-8");

	public void doPostAccessEntry(String message) {

		// TODO: implement a HTTP POST on the service to post the message
		
		RequestBody body = RequestBody.create(JSON, message.getBytes());
		
		new Request.Builder()
				.url("http://localhost:8080" + logpath)
				.post(body)
				.build();
		
	}
	
	private static String codepath = "/accessdevice/code";
	
	public AccessCode doGetAccessCode() {
		// TODO: implement a HTTP GET on the service to get current access code
		AccessCode code = null;
		
		Request req = new Request.Builder()
				  .url("http://localhost:8080" + codepath)
				  .get()
				  .build();
		
		OkHttpClient client = new OkHttpClient();
		
		try {
			Response res = client.newCall(req).execute();
			String resTxt = res.body().string();
			
			Gson gson = new Gson();
			code = gson.fromJson(resTxt, AccessCode.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return code;
	}
}
