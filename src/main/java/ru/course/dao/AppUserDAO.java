package ru.course.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


import ru.course.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class AppUserDAO {

    @Autowired
    private EntityManager entityManager;

    public AppUser findUserAccount(String userName) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";

            Query query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", userName);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public void addUser(AppUser User) {
        try {


            Query query = entityManager.createNativeQuery("INSERT INTO app_user ( USER_NAME, ENCRYTED_PASSWORD,ENABLED) VALUES (:name , :pass, :enabled);");

            query.setParameter("name", User.getUserName());
            query.setParameter("pass", User.getEncrytedPassword());
            query.setParameter("enabled", true);
            query.executeUpdate();

            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.userName = :userName ";

             query = entityManager.createQuery(sql, AppUser.class);
            query.setParameter("userName", User.getUserName());

            User =  (AppUser) query.getSingleResult();
             query = entityManager.createNativeQuery("INSERT INTO user_role ( USER_ID, ROLE_ID) VALUES (:User_id, :User_Role);");
            query.setParameter("User_id", User.getUserId());
            query.setParameter("User_Role", 2);
            query.executeUpdate();
        } catch (NoResultException e) {

        }
    }

}