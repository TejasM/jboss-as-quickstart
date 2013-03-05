package org.springframework.samples.petclinic.hibernate.support;

import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.service.jta.platform.internal.AbstractJtaPlatform;

/**
 * Transaction lookup support for Hibernate 3.3 in JBoss AS7
 * @author: Ryan Bradley
 */
public class JBossAs7TransactionManagerLookup extends AbstractJtaPlatform {


	@Override
	protected TransactionManager locateTransactionManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected UserTransaction locateUserTransaction() {
		// TODO Auto-generated method stub
		return null;
	}
}
