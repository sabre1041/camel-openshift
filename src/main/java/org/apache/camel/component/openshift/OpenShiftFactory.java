package org.apache.camel.component.openshift;

import java.util.regex.Pattern;

import org.apache.camel.component.openshift.consumer.application.ViewApplicationOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.application.ViewApplicationsOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.domain.ViewDomainOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.domain.ViewDomainsOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.user.ViewUserOpenShiftOperation;
import org.apache.camel.component.openshift.producer.application.CreateApplicationOpenShiftOperation;
import org.apache.camel.component.openshift.producer.application.ModifyApplicationOpenShiftOperation;
import org.apache.camel.component.openshift.producer.domain.CreateDomainOpenShiftOperation;
import org.apache.camel.component.openshift.producer.domain.ModifyDomainOpenShiftOperation;
import org.apache.camel.component.openshift.util.OperationType;

public class OpenShiftFactory {
	
	public static OpenShiftOperation<?> getConsumerOperation(OpenShiftEndpoint endpoint, String uri) {
		
        String[] uriSplit = splitUri(uri);

        if (uriSplit.length > 0) {
            switch (OperationType.fromUri(uriSplit[0])) {
            case USER:
            	return new ViewUserOpenShiftOperation(endpoint);
            case APPLICATIONS:
            	if(uriSplit.length > 1) {
                	String domain = uriSplit[1];
                	return new ViewApplicationsOpenShiftOperation(endpoint, domain);
            	}
            	break;
            case APPLICATION:
            	if(uriSplit.length > 1) {
                	String appUUID = uriSplit[1];
                	return new ViewApplicationOpenShiftOperation(endpoint, appUUID);
            	}
            	break;
            case DOMAINS:
            	return new ViewDomainsOpenShiftOperation(endpoint);
            case DOMAIN:
            	if(uriSplit.length > 1) {
                	String domainId = uriSplit[1];
                	return new ViewDomainOpenShiftOperation(endpoint, domainId);
            	}
            	break;
			default:
				break;
            }
        }

		
        throw new IllegalArgumentException("Cannot create any consumer with uri " + uri
                + ". A consumer type was not provided (or an incorrect pairing was used).");
	}
	
	
	public static OpenShiftOperation<?> getProducerOperation(OpenShiftEndpoint endpoint, String uri) {
       
		String[] uriSplit = splitUri(uri);

        if (uriSplit.length > 0) {
            switch (OperationType.fromUri(uriSplit[0])) {
            case USER:
            	
            	if(uriSplit.length > 1) {
            		try {
            			UserOperationType userOperationType = UserOperationType.valueOf(uriSplit[1].toUpperCase());
            			switch(userOperationType) {
            				default: 
                    			return new CreateDomainOpenShiftOperation(endpoint);
            			}
            			
            		}
            		catch(Exception e) {
            			throw new IllegalArgumentException("Invalid Application Operation");
            		}
            		
            	}
            	
            	return new ViewUserOpenShiftOperation(endpoint);
            	
            case APPLICATIONS:
            	if(uriSplit.length > 1) {
                	String domain = uriSplit[1];                	
                	return new ViewApplicationsOpenShiftOperation(endpoint, domain);
            	}
            	break;
            case APPLICATION:
            	if(uriSplit.length > 1) {
                	String appUUID = uriSplit[1];
                	
                	if(uriSplit.length > 2) {
                		try {
                			ApplicationOperationType appOperationType = ApplicationOperationType.valueOf(uriSplit[2].toUpperCase());
                			switch(appOperationType) {
                				default: 
                        			return new ModifyApplicationOpenShiftOperation(endpoint, appUUID, appOperationType);
                			}
                			
                		}
                		catch(Exception e) {
                			throw new IllegalArgumentException("Invalid Application Operation");
                		}
                		
                	}
                	
                	return new ViewApplicationOpenShiftOperation(endpoint, appUUID);
            	}
            	break;
            case DOMAINS:
            	return new ViewDomainsOpenShiftOperation(endpoint);
            case DOMAIN:
            	if(uriSplit.length > 1) {
            		String domainId = uriSplit[1];
                	
                	if(uriSplit.length > 2) {
                		try {
                			DomainOperationType domainOperationType = DomainOperationType.valueOf(uriSplit[2].toUpperCase());
                			switch(domainOperationType) {
                				case CREATEAPPLICATION:
                					return new CreateApplicationOpenShiftOperation(endpoint, domainId);
                				default:
                					return new ModifyDomainOpenShiftOperation(endpoint, domainId, domainOperationType);
                			}
                			
                		}
                		catch(Exception e) {
                			throw new IllegalArgumentException("Invalid Domain Operation");
                		}
                		
                	}
                	
                	return new ViewDomainOpenShiftOperation(endpoint, domainId);
            	}
            	break;
            default:
				break;
            }
        }
		
		
		throw new IllegalArgumentException("Cannot create any producer with uri " + uri
                + ". A producer type was not provided (or an incorrect pairing was used).");

	}
	
    private static String[] splitUri(String uri) {
        Pattern p1 = Pattern.compile("openshift:(//)*");
        Pattern p2 = Pattern.compile("\\?.*");

        uri = p1.matcher(uri).replaceAll("");
        uri = p2.matcher(uri).replaceAll("");

        return uri.split("/");
    }


}
