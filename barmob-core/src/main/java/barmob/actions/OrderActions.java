package barmob.actions;

import barmob.exceptions.BarmobRestException;
import barmob.resttypes.*;
import barmob.persistance.repositories.MenuRepository;
import barmob.persistance.repositories.OrderRepository;
import barmob.persistance.repositories.TableRepository;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

    public Order makeOrder(int menu_id, int table_id, int amount, String observation) throws BarmobRestException {
        Menu menu = menuRepository.getMenuById(menu_id);
        Table table = tableRepository.getTableById(table_id);
        if(table.getStatus().equals(TableStatus.BUSY.toString())){
            mongoTemplate.updateFirst(query(where("id").is("order_id")), update("seq", sequence.getSeq("order_id").getSeq() + 1), Counters.class);
            Order order = new Order(sequence.getSeq("order_id").getSeq(),menu,table,table.getClientId(),amount,observation, OrderStatus.INPROGRESS.toString());
            order = orderRepository.insert(order);
            return order;
        }else{
            throw new BarmobRestException("TODO TABLE IS NOT PREPARED TO RECEIVE ORDERS", "TODO GET MESSAGE ERROR CODE");
        }

    }

    public boolean deleteOrder(int order_id){
        orderRepository.delete(orderRepository.getOrderById(order_id));
        if (orderRepository.getOrderById(order_id) == null){
            return true;
        }else{
            throw new BarmobRestException("TODO - DELETE FAIL", "TODO GET MESSAGE ERROR CODE");
        }
    }

    public List<Order> getOrdersByClient(int clientId){
        List<Order> orderList = orderRepository.getOrdersByTable(clientId);
        if (!orderList.isEmpty()){
            return orderList;
        }else{
            throw new BarmobRestException("TODO no orders registered for the clientId","TODO GET MESSAGE ERROR CODE");
        }
    }

    public List<Order> getOrdersByStatus(OrderStatus orderStatus){
        List<Order> orderList = orderRepository.getOrderByStatus(orderStatus.toString());
        if (!orderList.isEmpty()){
            return orderList;
        }else{
            throw new BarmobRestException("TODO no orders registered with this status","TODO GET MESSAGE ERROR CODE");
        }
    }

    public HashMap<String,String> changeOrderState(int order_id, OrderStatus orderStatus){
        mongoTemplate.updateFirst(query(where("id").is(order_id)), update("status", orderStatus.toString()) , Order.class);
        if (mongoTemplate.find(query(where("status").is(orderStatus.toString()).andOperator(where("id").is(order_id))), Order.class) != null){
            HashMap<String,String> status = new HashMap<String,String>();
            status.put("status",orderStatus.toString());
            return status;
        }else{
            throw new BarmobRestException("TODO Status could not be changed.","TODO GET MESSAGE ERROR CODE");
        }
    }
}
