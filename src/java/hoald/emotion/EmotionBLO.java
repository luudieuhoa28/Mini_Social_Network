/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.emotion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * @author dell
 */
public class EmotionBLO {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiniSocialNetworkPU");

    public long getEmotion(int articleId, int typeEmotion) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT COUNT (e) FROM Emotion e WHERE e.emotionType = :typeEmotion AND e.emotionPK.articleId = :articleId")
                    .setParameter("typeEmotion", typeEmotion)
                    .setParameter("articleId", articleId);
            return (long) query.getSingleResult();
        } catch (Exception e) {
            return 0;
        } finally {
            entityManager.close();
        }

    }

    public boolean makeEmotion(Emotion emotion) {
        boolean result = true;
        EntityManager entityManager = emf.createEntityManager();
        try {
            Emotion tmpEmotion = entityManager.find(Emotion.class, emotion.emotionPK);
            if (tmpEmotion == null) {
                entityManager.getTransaction().begin();
                entityManager.persist(emotion);
                entityManager.getTransaction().commit();
            } else {
                if (tmpEmotion.getEmotionType() == emotion.getEmotionType()) {
                    tmpEmotion.setEmotionType(0);
                    result = false;
                } else {
                    tmpEmotion.setEmotionType(emotion.getEmotionType());
                }
                entityManager.getTransaction().begin();
                entityManager.merge(tmpEmotion);
                entityManager.getTransaction().commit();
            }

            return result;
        } catch (Exception e) {
            return false;
        } finally {
            entityManager.close();
        }

    }

}
