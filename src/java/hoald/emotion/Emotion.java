/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.emotion;

import hoald.user.Users;
import hoald.article.Article;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Emotion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emotion.findAll", query = "SELECT e FROM Emotion e")
    , @NamedQuery(name = "Emotion.findByUserId", query = "SELECT e FROM Emotion e WHERE e.emotionPK.userId = :userId")
    , @NamedQuery(name = "Emotion.findByEmotionType", query = "SELECT e FROM Emotion e WHERE e.emotionType = :emotionType")
    , @NamedQuery(name = "Emotion.findByArticleId", query = "SELECT e FROM Emotion e WHERE e.emotionPK.articleId = :articleId")})
public class Emotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EmotionPK emotionPK;
    @Basic(optional = false)
    @Column(name = "emotionType")
    private int emotionType;
    @JoinColumn(name = "articleId", referencedColumnName = "articleId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Article article;
    @JoinColumn(name = "userId", referencedColumnName = "userId", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Emotion() {
    }

    public Emotion(EmotionPK emotionPK) {
        this.emotionPK = emotionPK;
    }

    public Emotion(EmotionPK emotionPK, int emotionType) {
        this.emotionPK = emotionPK;
        this.emotionType = emotionType;
    }

    public Emotion(String userId, int articleId) {
        this.emotionPK = new EmotionPK(userId, articleId);
    }

    public EmotionPK getEmotionPK() {
        return emotionPK;
    }

    public void setEmotionPK(EmotionPK emotionPK) {
        this.emotionPK = emotionPK;
    }

    public int getEmotionType() {
        return emotionType;
    }

    public void setEmotionType(int emotionType) {
        this.emotionType = emotionType;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emotionPK != null ? emotionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Emotion)) {
            return false;
        }
        Emotion other = (Emotion) object;
        if ((this.emotionPK == null && other.emotionPK != null) || (this.emotionPK != null && !this.emotionPK.equals(other.emotionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Emotion[ emotionPK=" + emotionPK + " ]";
    }
    
}
