package org.apache.camel.component.openshift;

import java.io.InputStream;
import java.util.Properties;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.BeforeClass;

public abstract class BaseOpenShiftTest extends CamelTestSupport {

	private static final String OPENSHIFT_PROP_FILE = "openshift-test.properties";
	private static final String OPENSHIFT_USERNAME_PROP="openshift.username";
	private static final String OPENSHIFT_PASSWORD_PROP="openshift.password";
	protected static String USERNAME;
	protected static String PASSWORD;
	protected static String OPENSHIFT_CREDENTIALS_OPTIONS;
	
	@BeforeClass
	public static void beforeClass() throws Exception {
		InputStream is = null;
		try {
			Properties props = new Properties();
			is = BaseOpenShiftTest.class.getClassLoader().getResourceAsStream(OPENSHIFT_PROP_FILE);
			props.load(is);
			
			USERNAME = props.getProperty(OPENSHIFT_USERNAME_PROP);
			PASSWORD = props.getProperty(OPENSHIFT_PASSWORD_PROP);
			
			StringBuilder sb = new StringBuilder();
			sb.append("userName=");
			sb.append(USERNAME);
			sb.append("&password=");
			sb.append(PASSWORD);
		
			OPENSHIFT_CREDENTIALS_OPTIONS = sb.toString();
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			if(is != null) {
			is.close();
			}
		}
	}
	
}
