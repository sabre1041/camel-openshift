package org.apache.camel.component.openshift;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

/**
 * Represents the component that manages {@link OpenShiftEndpoint}.
 * 
 * @author Andrew Block
 *  
 */
public class OpenShiftComponent extends DefaultComponent {

	private String userName;
	private String password;
	private String host;
	
    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        
    	OpenShiftConfiguration properties = new OpenShiftConfiguration();
    	properties.setHost(host);
    	properties.setPassword(password);
    	properties.setUserName(userName);
    	    	
        setProperties(properties, parameters);

    	Endpoint endpoint = new OpenShiftEndpoint(uri, this, properties);
        
    	return endpoint;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getHost() {
		return password;
	}

	public void setHost(String host) {
		this.host = host;
	}
    
    
}
