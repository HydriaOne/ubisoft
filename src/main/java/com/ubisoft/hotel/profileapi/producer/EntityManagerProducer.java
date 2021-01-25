package com.ubisoft.hotel.profileapi.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Producer for injectable EntityManager
 *
 */
@RequestScoped
public class EntityManagerProducer {

    @PersistenceContext(unitName = "apiGradle")
    private EntityManager em;

    @Produces
    public EntityManager getEntityManager() {
        return em;
    }

}
