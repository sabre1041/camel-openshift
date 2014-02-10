package org.apache.camel.component.openshift;

import org.apache.camel.RuntimeCamelException;
import org.apache.camel.spi.UriParam;

import com.openshift.client.IOpenShiftConnection;
import com.openshift.client.OpenShiftConnectionFactory;

/**
 * Configuration for the {@link OpenShiftComponent}
 * 
 * @author Andrew Block
 *
 */
public class OpenShiftConfiguration {

	@UriParam
	private String userName;

	@UriParam
	private String password;

	@UriParam
	private String host;
	
	private IOpenShiftConnection openshift;

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

	public IOpenShiftConnection getOpenShift() {

		try {
				
			if(userName == null || password == null) {
				throw new IllegalArgumentException("Required authentication parameters not provided");
			}

			String serverName = new com.openshift.client.configuration.DefaultConfiguration()
					.getLibraServer();

			if(host != null) {
				serverName = host;
			}

			this.openshift = new OpenShiftConnectionFactory()
					.getConnection("camel-openshift-component", userName,
							password, serverName);

			return openshift;

		} catch (Exception e) {
			// TODO: Figure out how to handle exceptions
			throw new RuntimeCamelException(e.getMessage());
		}
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
