package org.springframework.samples.petclinic.hibernate.support;

import java.io.Serializable;
import java.util.Map;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.event.internal.DefaultMergeEventListener;
import org.hibernate.event.spi.MergeEvent;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

public class IdTransferringMergeEventListener extends DefaultMergeEventListener {
	
	@Override
	protected void entityIsTransient(MergeEvent event, Map copyCache) {
		super.entityIsTransient(event, copyCache);
		SessionImplementor session = event.getSession();
		EntityPersister persister = session.getEntityPersister(event.getEntityName(), event.getEntity());
		// Extract id from merged copy (which is currently registered with Session).
		Serializable id = persister.getIdentifier(event.getResult(), session);
		// Set id on original object (which remains detached).
		persister.setIdentifier(event.getOriginal(), id, session);
	}

}
