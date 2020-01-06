package com.yr.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * ≤‚ ‘∑Ω∑®
 * @author Administrator
 *
 */
public class MainTest {
	public static void main(String[] args) {
		StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure().build();
		SessionFactory buildSessionFactory = new MetadataSources(build).buildMetadata().buildSessionFactory();
		Session session = buildSessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		transaction.commit();
		session.close();
		buildSessionFactory.close();
	}
}
