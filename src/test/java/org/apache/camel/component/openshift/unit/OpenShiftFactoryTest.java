package org.apache.camel.component.openshift.unit;

import org.apache.camel.component.openshift.OpenShiftEndpoint;
import org.apache.camel.component.openshift.OpenShiftFactory;
import org.apache.camel.component.openshift.OpenShiftOperation;
import org.apache.camel.component.openshift.consumer.application.ViewApplicationOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.application.ViewApplicationsOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.domain.ViewDomainOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.domain.ViewDomainsOpenShiftOperation;
import org.apache.camel.component.openshift.consumer.user.ViewUserOpenShiftOperation;
import org.apache.camel.component.openshift.producer.application.CreateApplicationOpenShiftOperation;
import org.apache.camel.component.openshift.producer.application.ModifyApplicationOpenShiftOperation;
import org.apache.camel.component.openshift.producer.domain.CreateDomainOpenShiftOperation;
import org.apache.camel.component.openshift.producer.domain.ModifyDomainOpenShiftOperation;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class OpenShiftFactoryTest {
	
	private OpenShiftEndpoint endpoint;
	
	@Test
	public void testProducerViewUser() {
		String applicationUri = "openshift://user";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewUserOpenShiftOperation.class));
	}
	
	@Test
	public void testConsumerViewUser() {
		String applicationUri = "openshift://user";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getConsumerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewUserOpenShiftOperation.class));
	}
	
	@Test
	public void testConsumerViewApplications() {
		String applicationUri = "openshift://user";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getConsumerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewUserOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerViewApplications() {
		String applicationUri = "openshift://applications/somedomain";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewApplicationsOpenShiftOperation.class));
	}
	
	@Test
	public void testConsumerViewApplication() {
		String applicationUri = "openshift://application/appid";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getConsumerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewApplicationOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerViewApplication() {
		String applicationUri = "openshift://application/appid";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewApplicationOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerViewDomains() {
		String applicationUri = "openshift://domains";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewDomainsOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerViewDomain() {
		String applicationUri = "openshift://domain/domainid";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewDomainOpenShiftOperation.class));
	}
	
	@Test
	public void testConsumerViewDomain() {
		String applicationUri = "openshift://domain/domainid";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getConsumerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewDomainOpenShiftOperation.class));
	}
	
	@Test
	public void testConsumerViewDomains() {
		String applicationUri = "openshift://domains";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getConsumerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewDomainsOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerStartApplication() {
		String applicationUri = "openshift://application/appid/start";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ModifyApplicationOpenShiftOperation.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProducerBadApplicationOperation() {
		String applicationUri = "openshift://application/appid/melt";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ModifyApplicationOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerDeleteDomain() {
		String applicationUri = "openshift://domain/domainId/delete";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ModifyDomainOpenShiftOperation.class));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testProducerBadDomainOperation() {
		String applicationUri = "openshift://domain/domainId/add";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ModifyDomainOpenShiftOperation.class));
	}
	
	@Test
	public void testConsumerApplicationOperation() {
		String applicationUri = "openshift://application/appid/start";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getConsumerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(ViewApplicationOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerApplicationCreationOperation() {
		String applicationUri = "openshift://domain/domainId/createapplication";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(CreateApplicationOpenShiftOperation.class));
	}
	
	@Test
	public void testProducerDomainCreationOperation() {
		String applicationUri = "openshift://user/createdomain";
		
		OpenShiftOperation<?> op = OpenShiftFactory.getProducerOperation(endpoint, applicationUri);
		Assert.assertThat(op, CoreMatchers.instanceOf(CreateDomainOpenShiftOperation.class));
	}

	
	

}
