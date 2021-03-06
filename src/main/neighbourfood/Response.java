package main.neighbourfood;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;

    @ManyToOne
    @JoinColumn(name = "responder_id")
    private User responder;

    @ManyToOne
    @JoinColumn(name= "sale_id")
    @JsonIgnore
    private Sale sale_id;

    @ManyToOne
    @JoinColumn(name="order_id")
    @JsonIgnore
    private Orders order_id;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public User getResponder() {
        return responder;
    }

    public void setResponder(User responder) {
        responder.addResponse(this);
        this.responder = responder;
    }

    public Sale getSale_id() {
        return sale_id;
    }

    public void setSale_id(Sale sale_id) {
        this.sale_id = sale_id;
    }

    public Orders getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Orders order_id) {
        this.order_id = order_id;
    }

}
