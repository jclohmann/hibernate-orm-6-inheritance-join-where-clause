package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.persistence.Query;
import org.hibernate.entity.ExtendingEntity;
import org.hibernate.entity.ReferencedEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory( "templatePU" );
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	// Entities are auto-discovered, so just add them anywhere on class-path
	// Add your tests, using standard JUnit.
	@Test
	public void hhh123Test() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		// prepare data
		ReferencedEntity referencedEntity = new ReferencedEntity();
		entityManager.persist(referencedEntity);

		ExtendingEntity extendingEntity = new ExtendingEntity();
		extendingEntity.setCode("ABC");
		extendingEntity.setReferencedEntity(List.of(referencedEntity));
		entityManager.persist(extendingEntity);

		Query query = entityManager.createQuery(
				"select r from ReferencedEntity r join r.extendingEntity e where e.code = 'ABC'");
		List<ExtendingEntity> resultList = query.getResultList();

		/*
		generated query:
		    select
        re1_0.id,
        re1_0.extendingEntity_id
    from
        ReferencedEntity re1_0
    join
        (select
            *
        from
            BaseEntity t
        where
            t.type='E') ee1_0
            on ee1_0.id=re1_0.extendingEntity_id
    where
        ee1_0.code='ABC'
		 */

		/*
		error:
		SQL Error: 42122, SQLState: 42S22
		Column "EE1_0.CODE" not found
		 */

		System.out.println(resultList);

		entityManager.getTransaction().commit();
		entityManager.close();
	}
}
