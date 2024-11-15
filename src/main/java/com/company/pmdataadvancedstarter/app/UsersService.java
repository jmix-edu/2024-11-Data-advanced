package com.company.pmdataadvancedstarter.app;

import com.company.pmdataadvancedstarter.entity.Project;
import com.company.pmdataadvancedstarter.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsersService {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<User> getUsersNotInProject(Project project, int firstResult, int maxResult) {
        return entityManager.createQuery("SELECT u FROM User u " +
                        "WHERE u.id NOT IN " +
                        "(SELECT u1.id FROM User u1 " +
                        " INNER JOIN u1.projects pul " +
                        " WHERE pul.id = :projId)", User.class)
                .setParameter("projId", project.getId())
                .setFirstResult(firstResult)
                .setMaxResults(maxResult)
                .getResultList();
    }
}