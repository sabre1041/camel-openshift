package org.apache.camel.component.openshift.helper;

import org.apache.camel.Message;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.component.openshift.OpenShiftConfiguration;
import org.apache.camel.component.openshift.OpenShiftConstants;

import com.openshift.client.IApplication;
import com.openshift.client.IDomain;

/**
 * Helper methods for the OpenShift Component
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftHelper {
	
	/**
	 * Sets User Name and Password if they exist on the Message Header
	 * 
	 * @param message The incoming message
	 * @param openShiftConfiguration The OpenShift configuration
	 */
	public static void parseAuthenticationHeaders(Message message, OpenShiftConfiguration openShiftConfiguration) {
		
		String usernameHeader = message.getHeader(OpenShiftConstants.USERNAME, String.class);
		
		if(usernameHeader != null) {
			openShiftConfiguration.setUserName(usernameHeader);
		}
		
		String passwordHeader = message.getHeader(OpenShiftConstants.PASSWORD, String.class);
		
		if(usernameHeader != null) {
			openShiftConfiguration.setPassword(passwordHeader);
		}
		
	}
	
	/**
	 * Retrieve a Domain for a given identifier
	 * 
	 * @param openShiftConfiguration the OpenShift configuration
	 * @param domainId domain identifier
	 * @return the domain
	 */
	public static IDomain getDomainById(OpenShiftConfiguration openShiftConfiguration, String domainId) {
		return getDomainById(openShiftConfiguration, domainId, false);
	}
	
	/**
	 * Retrieve a Domain for a given identifier
	 * 
	 * @param openShiftConfiguration the OpenShift configuration
	 * @param domainId domain identifier
	 * @param required whether a Domain with the identifier specified must be found in the list of Domains belonging to a user. Otherwise a {@link RuntimeCamelException} is thrown
	 * @return the domain
	 */
	public static IDomain getDomainById(OpenShiftConfiguration openShiftConfiguration, String domainId, boolean required) {
		for(IDomain domain : openShiftConfiguration.getOpenShift().getDomains()) {
			if(domain.getId().equals(domainId)) {
				return domain;
			}
		}
		
		if(required) {
			throw new RuntimeCamelException("Domain does not exist");
		}
		else {
			return null;
		}
	}
	
	/**
	 * Retrieve a Application for a given UUID
	 * 
	 * @param openShiftConfiguration the OpenShift configuration
	 * @param appUUID the Application UUID
	 * @return the Application
	 */
	public static IApplication getApplicationByUUID(OpenShiftConfiguration openShiftConfiguration, String appUUID) {			
		return getApplicationByUUID(openShiftConfiguration, appUUID, false);
	}
	
	/**
	 * Retrieve a Application for a given UUID
	 * 
	 * @param openShiftConfiguration the OpenShift configuration
	 * @param appUUID the Application UUID
	 * @return the Application
	 * @param required whether a Application with the UUID specified must be found in the list of Applications belonging to a user. Otherwise a {@link RuntimeCamelException} is thrown
	 */
	public static IApplication getApplicationByUUID(OpenShiftConfiguration openShiftConfiguration, String appUUID, boolean required) {			
		for(IDomain domain : openShiftConfiguration.getOpenShift().getDomains()) {
			for(IApplication application : domain.getApplications()) {
				if(application.getUUID().equals(appUUID)) {
					return application;
				}
			}
		}
		
		if(required) {
			throw new RuntimeCamelException("Application does not exist");
		}
		else {
			return null;
		}
		
	}

}
