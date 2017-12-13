package main.neighbourfood;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.*;


@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    private String title;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

    private Date whenReady;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orders", cascade = CascadeType.ALL)
    @JsonIgnore //Jos tämän ottaa ppois
    private Set<Requirement> requirements = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order_id", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Response> responses;


    public Orders() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
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

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.addOrder(this);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                "title: " +title+
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", whenReady=" + whenReady +
                ", user=" + user +
                ", responses=" + responses +
                '}';
    }

    public void addRequirement(Requirement requirement) {
        requirements.add(requirement);
    }

    protected Orders(OrderTO ot) {
        this.id = ot.getId();
        this.content  = ot.getContent();
        title = ot.getTitle();
        createDate = ot.getCreateDate();
        whenReady = ot.getWhenReady();
        requirements = new HashSet<>(ot.getRequirements());
        user = ot.getUser();
        //responses = new HashSet<>(ot.getResponses());
    }
}


class OrderTO {
    private Integer id;
    private String content;
    private String title;
    private Date createDate;
    private Date whenReady;
    private Set<Requirement> requirements = new HashSet<>();
    private User user;
    @JsonIgnore
    private Set<Response> responses;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getWhenReady() {
        return whenReady;
    }

    public void setWhenReady(Date whenReady) {
        this.whenReady = whenReady;
    }

    public Set<Requirement> getRequirements() {
        return requirements;
    }

    public void setRequirements(Set<Requirement> requirements) {
        this.requirements = requirements;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Response> getResponses() {
        return responses;
    }

    public void setResponses(Set<Response> responses) {
        this.responses = responses;
    }
}