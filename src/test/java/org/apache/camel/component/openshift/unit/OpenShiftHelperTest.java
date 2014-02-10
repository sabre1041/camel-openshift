package org.apache.camel.component.openshift.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.camel.Message;
import org.apache.camel.component.openshift.OpenShiftConfiguration;
import org.apache.camel.component.openshift.OpenShiftConstants;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;
import org.apache.camel.impl.DefaultMessage;
import org.junit.Test;

public class OpenShiftHelperTest {
	
	
	@Test
	public void authenticationHeaderTest() {
		
		String username = "username";
		String password = "password";
		
		OpenShiftConfiguration config = new OpenShiftConfiguration();
		
		Message message = new DefaultMessage();
		message.setHeader(OpenShiftConstants.USERNAME, username);
		message.setHeader(OpenShiftConstants.PASSWORD, password);
		
		
		OpenShiftHelper.parseAuthenticationHeaders(message, config);
		
		assertNotNull(config.getUserName());
		assertNotNull(config.getPassword());
		
		assertEquals(password, config.getPassword());
		assertEquals(username, config.getUserName());
		
	}
	
	

}
