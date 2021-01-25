package com.ubisoft.hotel.profileapi.repository;

import com.ubisoft.hotel.profileapi.vo.Authority;
import javax.inject.Inject;
import javax.persistence.EntityManager;

public class AuthorityRepository extends AbstractRepository<Authority, String> {

    @Inject
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorityRepository() {
        super(Authority.class);
    }
}
