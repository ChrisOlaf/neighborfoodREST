package main.neighbourfood;


import javax.persistence.*;

@Entity
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String requirement;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders orders;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public Requirement() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequirement() {
        return requirement;
    }

    public void setRequirement(String requirement) {
        this.requirement = requirement;
    }

    public Orders getOrder() {
        return orders;
    }

    public void setOrder(Orders order) {
        this.orders = orders;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
