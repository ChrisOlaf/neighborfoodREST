package main.neighbourfood;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "responder_id")
    private User responder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "sale_id")
    private Sale saleresponse;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "orders_id")
    private Orders ordersresponse;

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
        this.responder = responder;
    }

    public Sale getSaleresponse() {
        return saleresponse;
    }

    public void setSaleresponse(Sale saleresponse) {
        this.saleresponse = saleresponse;
    }

    public Orders getOrdersresponse() {
        return ordersresponse;
    }

    public void setOrdersresponse(Orders ordersresponse) {
        this.ordersresponse = ordersresponse;
    }
}
