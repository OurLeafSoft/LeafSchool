package com.leafsoft.org.rest.util;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyClient {
	
	public static String get(String url,String inputString){
		String output = "";
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource(url);

			//String inputString = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
			   .post(ClientResponse.class, inputString);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			output = response.getEntity(String.class);
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
		return output;

		}
	
	public static void post(String url,String inputString) {
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource(url);

			//String input = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";

			ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
			   .post(ClientResponse.class, inputString);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
				     + response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		  } catch (Exception e) {

			e.printStackTrace();

		  }
	}
}
