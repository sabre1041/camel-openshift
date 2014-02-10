package org.apache.camel.component.openshift;

/**
 * Operations which can be performed on an Application
 * 
 * @author Andrew Block
 *
 */
public enum ApplicationOperationType {
	
	START,STOP,RESTART,DELETE,SCALEUP,SCALEDOWN,ADDCARTRIDGE,ADDALIAS;

}
