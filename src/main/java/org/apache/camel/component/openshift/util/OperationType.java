package org.apache.camel.component.openshift.util;


/**
 * Operation types
 * 
 * @author Andrew Block
 *
 */
public enum OperationType {
	
	USER,DOMAINS,DOMAIN,APPLICATIONS,APPLICATION,UNKNOWN;
	
    public static OperationType fromUri(String uri) {
        for (OperationType consumerType : OperationType.values()) {
            if (consumerType.name().equalsIgnoreCase(uri)) {
                return consumerType;
            }
        }
        return OperationType.UNKNOWN;
    }


}
