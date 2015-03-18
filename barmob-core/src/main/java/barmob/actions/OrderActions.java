package barmob.actions;

import barmob.persistance.domain.*;
import barmob.persistance.repositories.MenuRepository;
import barmob.persistance.repositories.OrderRepository;
import barmob.persistance.repositories.TableRepository;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@Component
public class OrderActions extends Action{

    private MenuRepository menuRepository = ctx.getBean(MenuRepository.class);
    private TableRepository tableRepository = ctx.getBean(TableRepository.class);
    private OrderRepository orderRepository = ctx.getBean(OrderRepository.class);

    public Order makeOrder(int menu_id, int table_id, int amount, String observation){
        Menu menu = menuRepository.getMenuById(menu_id);
        Table table = tableRepository.getTableById(table_id);
        mongoTemplate.updateFirst(query(where("id").is("order_id")), update("seq", sequence.getSeq("order_id").getSeq() + 1), Counters.class);
        Order order = new Order(sequence.getSeq("order_id").getSeq(),menu,table,amount,observation, OrderStatus.INPROGRESS.toString());
        order = orderRepository.insert(order);
        return order;
    }

    public boolean deleteOrder(int order_id){
        orderRepository.delete(orderRepository.getOrderById(order_id));
        return orderRepository.getOrderById(order_id) == null;
    }

    public List<Order> getOrdersByTable(int table_id){
        List<Order> orderList = orderRepository.getOrdersByTable(table_id);
        return orderList;
    }

    public List<Order> getOrdersByMenu(int menu_id){
        List<Order> orderList = orderRepository.getOrderByMenu(menu_id);
        return orderList;
    }

    public boolean changeOrderState(int order_id, OrderStatus orderStatus){
        mongoTemplate.updateFirst(query(where("id").is(order_id)), update("status", orderStatus.toString()) , Order.class);
        return mongoTemplate.find(query(where("status").is(orderStatus.toString()).andOperator(where("id").is(order_id))), Order.class) != null;
    }
}
