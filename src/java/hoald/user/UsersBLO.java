/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.user;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.logging.Logger;

/**
 * @author dell
 */
public class UsersBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiniSocialNetworkPU");

    public Users checkLogin(String userId, String password) {
        EntityManager entityManager = emf.createEntityManager();
        try {

            TypedQuery<Users> query = entityManager.createQuery("SELECT u from Users u where u.userId = :userId and u.password = :password", Users.class)
                    .setParameter("userId", userId)
                    .setParameter("password", password);

            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public void registerAccount(Users user) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            Users tmpUser = entityManager.find(Users.class, user.getUserId());
            if (tmpUser == null) {
                entityManager.persist(user);
            } else {
                tmpUser.setFullName(user.getFullName());
                tmpUser.setPassword(user.getPassword());
                tmpUser.setVerifyCode(user.getVerifyCode());
                entityManager.merge(tmpUser);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("Error at registerAccount: " + e);
        } finally {
            entityManager.close();
        }

    }

    public Users getUser(String userId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            return entityManager.find(Users.class, userId);
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }

    }

    public Users getUserActive(String userId) {
        EntityManager entityManager = emf.createEntityManager();
        try {

            TypedQuery<Users> query = entityManager.createQuery("SELECT u from Users u where u.status = 'Active' and u.userId = :userId", Users.class)
                    .setParameter("userId", userId);

            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public void activateAccount(String userId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Users user = entityManager.find(Users.class, userId);
            entityManager.getTransaction().begin();
            user.setStatus("Active");
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("Error at activateAccount: " + e);
        } finally {
            entityManager.close();
        }

    }


}
