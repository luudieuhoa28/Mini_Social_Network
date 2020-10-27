/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.notification;

import hoald.user.Users;
import hoald.article.Article;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dell
 */
@Entity
@Table(name = "Notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n")
    , @NamedQuery(name = "Notification.findByNotificationId", query = "SELECT n FROM Notification n WHERE n.notificationId = :notificationId")
    , @NamedQuery(name = "Notification.findByActivityType", query = "SELECT n FROM Notification n WHERE n.activityType = :activityType")
    , @NamedQuery(name = "Notification.findByIsSeen", query = "SELECT n FROM Notification n WHERE n.isSeen = :isSeen")
    , @NamedQuery(name = "Notification.findByDate", query = "SELECT n FROM Notification n WHERE n.date = :date")})
public class Notification implements Serializable {

    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Basic(optional = false)
    @Column(name = "notificationId")
    private Integer notificationId;
    @Basic(optional = false)
    @Column(name = "activityType")
    private int activityType;
    @Basic(optional = false)
    @Column(name = "isSeen")
    private boolean isSeen;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @JoinColumn(name = "articleId", referencedColumnName = "articleId")
    @ManyToOne(optional = false)
    private Article articleId;
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @ManyToOne(optional = false)
    private Users userId;

    public Notification() {
    }

    public Notification(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public Notification(Integer notificationId, int activityType, boolean isSeen, Date date) {
        this.notificationId = notificationId;
        this.activityType = activityType;
        this.isSeen = isSeen;
        this.date = date;
    }

    public Notification(int activityType, boolean isSeen, Date date, Article articleId, Users userId) {
        this.activityType = activityType;
        this.isSeen = isSeen;
        this.date = date;
        this.articleId = articleId;
        this.userId = userId;
    }
    
    

    public Integer getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Integer notificationId) {
        this.notificationId = notificationId;
    }

    public int getActivityType() {
        return activityType;
    }

    public void setActivityType(int activityType) {
        this.activityType = activityType;
    }

    public boolean getIsSeen() {
        return isSeen;
    }

    public void setIsSeen(boolean isSeen) {
        this.isSeen = isSeen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Article getArticleId() {
        return articleId;
    }

    public void setArticleId(Article articleId) {
        this.articleId = articleId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationId != null ? notificationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationId == null && other.notificationId != null) || (this.notificationId != null && !this.notificationId.equals(other.notificationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Notification[ notificationId=" + notificationId + " ]";
    }
    
}
