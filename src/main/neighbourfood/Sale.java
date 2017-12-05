package main.neighbourfood;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<Requirement> requirements;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "seller_id")
    private User seller;

    private Date whenReady;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "saleresponse", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Response> responses;


    public Sale() {
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

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Date getWhenReady() {
        return whenReady;
    }

    public void setWhenReady(Date whenReady) {
        this.whenReady = whenReady;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }
}
