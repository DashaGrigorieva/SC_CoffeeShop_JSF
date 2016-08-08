package by.sc.coffeeshop.order;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;

/**
 * Created by User on 04.08.2016.
 */

@Entity
@Table(name = "orders")
@ManagedBean
@SessionScoped
public class CoffeeOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "coffee_type")
    private String coffeeType;

    @Column(name = "amount")
    private double amount;

    @Column(name = "delivery")
    private boolean delivery;

    @Column(name = "from_time")
    private int fromTime;

    @Column(name = "to_time")
    private int toTime;

    @Column(name = "cost")
    private int cost;

    private final static String type1 = "Kenyan Coffee";
    private final static String type2 = "Tanzania Peaberry Coffee";
    private final static String type3 = "Ethiopian Coffee";
    private final static String type4 = "Sumatra Mandheling Coffee";
    private final static String type5 = "Italian Roast";

    public CoffeeOrder() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(String coffeeType) {
        this.coffeeType = coffeeType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public int getFromTime() {
        return fromTime;
    }

    public void setFromTime(int fromTime) {
        this.fromTime = fromTime;
    }

    public int getToTime() {
        return toTime;
    }

    public void setToTime(int toTime) {
        this.toTime = toTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public CoffeeOrder(String coffeeType, double amount, boolean delivery, int fromTime, int toTime) {

        this.coffeeType = coffeeType;
        this.amount = amount;
        this.delivery = delivery;
        this.fromTime = fromTime;
        this.toTime = toTime;
        this.cost = calcCost();
    }

    public int calcCost() {
        int resultCost = 0;
        if (coffeeType != null) {
            switch (coffeeType) {
                case type1:
                    resultCost += 800;
                    break;
                case type2:
                    resultCost += 1000;
                    break;
                case type3:
                    resultCost += 600;
                    break;
                case type4:
                    resultCost += 700;
                    break;
                case type5:
                    resultCost += 900;
                    break;
                default:
                    resultCost += 0;
            }
        }
        resultCost *= amount;
        if(delivery && resultCost != 0) {
            resultCost += 40000;
        }
        cost = resultCost;
        return resultCost;
    }

    @Override
    public String toString() {
        String del;
        if(delivery) {
            del = " Доставка курьером";
        } else {
            del = " Самостоятельно";
        }
        return ("#" + id + "  " + coffeeType + " " + amount + "г " + del + " c " + fromTime + " до " + toTime + "" +
                " Цена : " + cost + "  ");
    }
}
