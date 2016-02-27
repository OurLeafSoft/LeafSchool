package com.leafsoft.jersey;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import com.leafsoft.jersey.provider.AuthenticationFilter;


public class CustomApplication extends ResourceConfig 
{
	public CustomApplication() 
	{
		packages("com.leafsoft.jersey");
		register(LoggingFilter.class);
		register(AuthenticationFilter.class);
		//Manually adding MOXyJSONFeature
		register(org.glassfish.jersey.moxy.json.MoxyJsonFeature.class);
 
        //Configure Moxy behavior
		//register(JsonMoxyConfigurationContextResolver.class);
	} 
}
