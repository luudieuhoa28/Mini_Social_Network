/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.article;

import javax.persistence.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author dell
 */
public class ArticleBLO {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MiniSocialNetworkPU");

    public Article getArticle(int articleId) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            TypedQuery<Article> query = entityManager.createQuery("SELECT a FROM Article a WHERE a.articleId = :articleId and a.status = true", Article.class)
                    .setParameter("articleId", articleId);
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }

    }

    public List<Article> getListArticles(String search, int page) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            TypedQuery<Article> query = entityManager.createQuery("SELECT a from Article a " +
                    "where (a.title like :search or a.description like :search) and a.status = true " +
                    "order by a.date desc", Article.class)
                    .setParameter("search", "%" + search + "%")
                    .setFirstResult(-20 + page * 20)
                    .setMaxResults(20);
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            entityManager.close();
        }

    }

    public void updateStatusArticle(int articleId) {
        EntityManager entityManager = emf.createEntityManager();
        try {

            entityManager.getTransaction().begin();
            entityManager.createQuery("UPDATE Article a SET a.status = false WHERE a.articleId = :articleId")
                    .setParameter("articleId", articleId)
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("Error at updateStatusArticle: " + e);
        } finally {
            entityManager.close();
        }
    }

    public void insertArticle(Article article) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(article);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger("Error at insertArticle: " + e);
        } finally {
            entityManager.close();
        }
    }

}
