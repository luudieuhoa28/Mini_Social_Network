/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.notification;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author dell
 */
public class NotificationBLO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiniSocialNetworkPU");

    public void insertNotification(Notification notification) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(notification);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("Error at insertNotification: " + e);
        } finally {
            entityManager.close();
        }

    }

    public List<Notification> getNotificationList(String userId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT n FROM Notification n WHERE n.articleId.userId.userId = :userId  and n.articleId.status = true ORDER BY n.date desc ")
                    .setParameter("userId", userId);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }

    }

}
