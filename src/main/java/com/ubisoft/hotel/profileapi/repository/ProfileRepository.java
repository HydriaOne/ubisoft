package com.ubisoft.hotel.profileapi.repository;

import com.ubisoft.hotel.profileapi.vo.Profile;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class ProfileRepository extends AbstractRepository<Profile, Long> {

    @Inject
    @PersistenceContext(name = "apiGradle")    
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public ProfileRepository() {
        super(Profile.class);
    }

}
