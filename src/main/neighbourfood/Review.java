package main.neighbourfood;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    private int stars;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "writer_id")
    private User writer;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn (name= "target_id")
    private User target;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "sale_id")
    @JsonIgnore
    private Sale sale;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    public Review() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }

    public User getTarget() {
        return target;
    }

    public void setTarget(User target) {
        this.target = target;
        this.target.addReview(this);
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", stars=" + stars +
                ", writer=" + writer +
                ", target=" + target +
                ", sale=" + sale +
                ", createDate=" + createDate +
                '}';
    }
}
