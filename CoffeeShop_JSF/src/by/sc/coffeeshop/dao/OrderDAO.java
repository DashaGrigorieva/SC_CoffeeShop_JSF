package by.sc.coffeeshop.dao;

import by.sc.coffeeshop.order.CoffeeOrder;
import by.sc.coffeeshop.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.faces.bean.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 25.07.2016.
 */

@ManagedBean
@RequestScoped
//@ViewScoped
public class OrderDAO {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @ManagedProperty("#{param.id}")
    private String id;

    private Session session;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public OrderDAO() {
    }

    public OrderDAO(Session session) {
        this.session = session;
    }


    private static List<CoffeeOrder> orders = new ArrayList<>();

    public String addOrder(CoffeeOrder coffeeOrder) {
        session = sessionFactory.openSession();
        coffeeOrder.calcCost();
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.save(coffeeOrder);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
        return "main";
    }

    public void updateOrder(CoffeeOrder coffeeOrder) {
        Transaction t = null;
        try {
            t = session.beginTransaction();
            session.update(coffeeOrder);
            t.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
    }
     
    public void deleteOrderById() {
        session = sessionFactory.openSession();
        CoffeeOrder coffeeOrder = (CoffeeOrder) session.load(CoffeeOrder.class, new Integer(Integer.parseInt(id)));
        if(coffeeOrder != null) {
            session.delete(coffeeOrder);
        }
    }

    public String deleteOrder() {
        session = sessionFactory.openSession();
        Transaction t = null;
        try{
            t = session.beginTransaction();
            CoffeeOrder coffeeOrder = (CoffeeOrder) session.load(CoffeeOrder.class, new Integer(Integer.parseInt(id)));
            session.delete(coffeeOrder);
            t.commit();
        } catch(HibernateException e) {
            e.printStackTrace();
            t.rollback();
        }
        return "main";
    }

     
    public CoffeeOrder getOrderById() {
        session = sessionFactory.openSession();
        return (CoffeeOrder) session.load(CoffeeOrder.class, new Integer(Integer.parseInt(id)));
    }

     
    public List<CoffeeOrder> getOrders() {
        session = sessionFactory.openSession();
        return (List<CoffeeOrder>) session.createQuery("FROM CoffeeOrder").list();
    }

    public int ordersCount() {
        session = sessionFactory.openSession();
        return ((Long)session.createQuery("SELECT COUNT (*) FROM CoffeeOrder ").uniqueResult()).intValue();
    }

    public static void setOrders(List<CoffeeOrder> orders) {
        OrderDAO.orders = orders;
    }
}
