package org.apache.camel.component.openshift;

import org.apache.camel.Exchange;

/**
 * @author Andrew Block
 *
 * @param <T> type which should be handled by the Operation
 */
public interface OpenShiftOperation<T> {

	public T execute(Exchange e);
	
}
