package org.apache.camel.component.openshift.producer.application;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.RuntimeCamelException;
import org.apache.camel.component.openshift.OpenShiftConstants;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;

import com.openshift.client.IApplication;
import com.openshift.client.IDomain;
import com.openshift.client.cartridge.StandaloneCartridge;

/**
 * Creates an Application in a given Domain
 * 
 * @author Andrew Block
 *
 */
public class CreateApplicationOpenShiftOperation implements OpenShiftOperation<IApplication> {

	private OpenShiftEndpoint endpoint;
	private String domainId;
	
	public CreateApplicationOpenShiftOperation(OpenShiftEndpoint endpoint,
			String domainId) {
		this.endpoint = endpoint;
		this.domainId = domainId;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public IApplication execute(Exchange e) {
		
		IDomain domain = OpenShiftHelper.getDomainById(endpoint.getConfiguration(), domainId, true);
		
		Message message = e.getIn();
		
		if(message.getBody() instanceof Map) {
			
			Map<String,String> createApplicationMap = (Map<String, String>) message.getBody();
			
			String appName = createApplicationMap.get(OpenShiftConstants.NEW_APPLICATION_NAME);
			String cartridge = createApplicationMap.get(OpenShiftConstants.NEW_APPLICATION_CARTRIDGE);
			
			if(appName == null || cartridge == null) {
				throw new RuntimeCamelException("Application and Cartridge Must be Provided");
			}
			
			return domain.createApplication(appName, new StandaloneCartridge(cartridge));
			
			// TODO: Provide Support for Scaled, Gear Profile and Git Repository for App Creation
		
		}
		else {
			throw new RuntimeCamelException("Body must be a Map containing new Application Parameters");
		}
	}

}
