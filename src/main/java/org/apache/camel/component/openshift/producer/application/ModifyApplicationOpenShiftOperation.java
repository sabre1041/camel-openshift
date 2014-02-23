package org.apache.camel.component.openshift.producer.application;

import org.apache.camel.Exchange;
import org.apache.camel.component.openshift.ApplicationOperationType;
import org.apache.camel.component.openshift.InvalidOpenShiftApplicationOperationException;
import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.helper.OpenShiftHelper;

import com.openshift.client.IApplication;

/**
 * Performs operations against a given Application 
 * 
 * @author Andrew Block
 *
 */
public class ModifyApplicationOpenShiftOperation implements
		OpenShiftOperation<IApplication> {

	private OpenShiftEndpoint endpoint;
	private String applicationUUID;
	private ApplicationOperationType operationType;

	public ModifyApplicationOpenShiftOperation(OpenShiftEndpoint endpoint,
			String applicationUUID, ApplicationOperationType operationType) {
		this.endpoint = endpoint;
		this.applicationUUID = applicationUUID;
		this.operationType = operationType;
	}

	@Override
	public IApplication execute(Exchange e) {

		IApplication application = OpenShiftHelper.getApplicationByUUID(
				endpoint.getConfiguration(), applicationUUID, true);

		switch (operationType) {
		case START:
			application.start();
			break;
		case STOP:
			application.stop();
			break;
		case RESTART:
			application.restart();
			break;
		case DELETE:
			application.destroy();
			break;
		case SCALEDOWN:
			application.scaleDown();
			break;
		case SCALEUP:
			application.scaleUp();
			break;
		default:
			throw new InvalidOpenShiftApplicationOperationException("Invalid Application Operation");
		}
		
		return OpenShiftHelper.getApplicationByUUID(
				endpoint.getConfiguration(), applicationUUID, true);

	}

}
