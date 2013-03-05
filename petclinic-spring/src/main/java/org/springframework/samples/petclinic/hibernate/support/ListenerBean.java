package org.springframework.samples.petclinic.hibernate.support;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListenerBean {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private IdTransferringMergeEventListener listener;

	@PostConstruct
	public void registerListener() {
		EventListenerRegistry registry = ((SessionFactoryImpl) sessionFactory)
				.getServiceRegistry().getService(EventListenerRegistry.class);
		registry.getEventListenerGroup(EventType.MERGE).appendListener(listener);
	}
}