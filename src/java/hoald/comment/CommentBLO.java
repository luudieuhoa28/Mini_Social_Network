/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.comment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author dell
 */
public class CommentBLO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiniSocialNetworkPU");

    public void updateStatusComment(int commentId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Comment comment = entityManager.find(Comment.class, commentId);
            entityManager.getTransaction().begin();
            comment.setStatus(false);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("Error at updateStatusComment: " + e);
        } finally {
            entityManager.close();
        }

    }

    public List<Comment> getListCommentByArticleId(int articleId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Query query = entityManager.createQuery("SELECT c FROM Comment c WHERE c.status = true AND c.articleId.articleId = :articleId")
                    .setParameter("articleId", articleId);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }

    }

    public void postComment(Comment comment) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(comment);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("Error at postComment: " + e);
        } finally {
            entityManager.close();
        }

    }
}
