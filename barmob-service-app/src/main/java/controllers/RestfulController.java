package controllers;

import barmob.actions.MenuActions;
import barmob.actions.OrderActions;
import barmob.actions.TableActions;
import barmob.resttypes.*;
import barmob.exceptions.BarmobRestException;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.web.bind.annotation.*;
import barmob.templates.ErrorTemplate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gustavokm90 on 3/19/15.
 */
@RestController
@RequestMapping(value = "/restful")
public class RestfulController {

    //Business objects
    private final MenuActions menuActions = new MenuActions();
    private final TableActions tableActions = new TableActions();
    private final OrderActions orderActions = new OrderActions();


    @ExceptionHandler(BarmobRestException.class)
    public ErrorTemplate handleErrors(BarmobRestException ex) {
        return new ErrorTemplate(ex.getErrCode(),ex.getErrMsg());
    }

    /*
    * Menu REST mapping
    *
    * Controller should performs little or no processing itself and instead delegates
    * responsibility for the business logic to one or more service objects. Thus we have
    * created objects to handle the business logic.
    * */

    @RequestMapping(value = "/menu/type/{type}", method = RequestMethod.GET)
    public List<Menu> getMenu(@PathVariable(value="type") String type) throws BarmobRestException {
        MenuTypes menuType;
        try {
            menuType = MenuTypes.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new BarmobRestException("TODO INPUT VALIDATION - ERROR CODE ENUM", "TODO GET MESSAGE ERROR CODE");
        }
        return menuActions.getMenuType(menuType);
    }

    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
    public Menu getMenu(@PathVariable(value="id") int id) throws BarmobRestException {
        return menuActions.getMenu(id);
    }

    @RequestMapping(value = "/menu/{id}/details/{field}", method = RequestMethod.GET)
    public HashMap<String,String> getMenu(@PathVariable int id,@PathVariable String field) throws BarmobRestException, InvocationTargetException, IllegalAccessException {
        HashMap<String,String> jsonResp = new HashMap<String, String>();
        //Using reflection to get the detail inside the menu object
        try {
            Method method = menuActions.getMenu(id).getClass().getMethod("get"+(WordUtils.capitalizeFully(field)));
            Object obj = method.invoke(menuActions.getMenu(id));
            jsonResp.put(field.toLowerCase(),obj.toString());
        } catch (NoSuchMethodException e) {
            throw new BarmobRestException("TODO INPUT VALIDATION - field not found","TODO GET MESSAGE ERROR CODE");
        }
        return jsonResp;
    }

    @RequestMapping(value = "/menu/type", method = RequestMethod.GET)
    public HashMap<String,String> getMenuTypes() throws BarmobRestException {
        HashMap<String,String> jsonResp = new HashMap<String, String>();
        for (MenuTypes a : MenuTypes.values()){
            jsonResp.put(a.ordinal()+"", a.toString());
        }
        return jsonResp;
    }


    /*
    * Table REST mapping
    * */

    @RequestMapping(value = "/table/{id}", method = RequestMethod.GET)
    public Table getTable(@PathVariable(value="id") int id) throws BarmobRestException {
        return tableActions.getTable(id);
    }

    @RequestMapping(value = "/table/{id}/details/{field}", method = RequestMethod.GET)
    public HashMap<String,String> getTable(@PathVariable int id,@PathVariable String field) throws BarmobRestException, InvocationTargetException, IllegalAccessException {
        HashMap<String,String> jsonResp = new HashMap<String, String>();
        //Using reflection to get the detail inside the menu object
        try {
            Method method = tableActions.getTable(id).getClass().getMethod("get"+(WordUtils.capitalizeFully(field)));
            Object obj = method.invoke(tableActions.getTable(id));
            jsonResp.put(field.toLowerCase(),obj.toString());
        } catch (NoSuchMethodException e) {
            throw new BarmobRestException("TODO INPUT VALIDATION - field not found","TODO GET MESSAGE ERROR CODE");
        }
        return jsonResp;
    }

    @RequestMapping(value = "/table", method = RequestMethod.GET)
    public List<Table> getAllTables() throws BarmobRestException {
        return tableActions.getAllTables();
    }

    @RequestMapping(value = "/table/{id}/start", method = RequestMethod.GET)
    public HashMap<String,String> startTable(@PathVariable int id) throws BarmobRestException {
        return tableActions.startTable(id);
    }

    @RequestMapping(value = "/table/{id}/close", method = RequestMethod.GET)
    public HashMap<String,String> closeTable(@PathVariable int id) throws BarmobRestException {
        return tableActions.closeTable(id);

    }

    @RequestMapping(value = "/table/{id}/reset", method = RequestMethod.GET)
    public HashMap<String,String> resetTable(@PathVariable int id) throws BarmobRestException {
        return tableActions.resetTable(id);
    }

    /*
    * Order REST mapping
    * */

    @RequestMapping(value = "/order/create", method = RequestMethod.GET)
    public Order makeOrder(@RequestParam(value = "menu_id") int menu_id, @RequestParam(value = "table_id") int table_id,@RequestParam(value = "amount") int amount,@RequestParam(value = "observation") String observation){
        return orderActions.makeOrder(menu_id, table_id, amount, observation);
    }

    @RequestMapping(value = "/order/{id}/{status}", method = RequestMethod.GET)
    public HashMap<String,String> changeOrderStatus(@PathVariable(value="id") int id, @PathVariable(value="status") String status) throws BarmobRestException {
        OrderStatus orderStatus;
        try {
            orderStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new BarmobRestException("TODO - INPUT VALIDATION ERROR CODE ENUM", "TODO GET MESSAGE ERROR CODE");
        }
        return orderActions.changeOrderState(id, orderStatus);

    }

    @RequestMapping(value = "/order/client/{id}", method = RequestMethod.GET)
    public List<Order> getOrderByClient(@PathVariable(value="id") int id) throws BarmobRestException {
        return orderActions.getOrdersByClient(id);
    }

    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    public Order getOrderById(@PathVariable(value="id") int id) throws BarmobRestException {
        return orderActions.getOrderById(id);
    }

    @RequestMapping(value = "/order/status/{status}", method = RequestMethod.GET)
    public List<Order> getOrderByStatus(@PathVariable(value="status") String status) throws BarmobRestException {

        OrderStatus orderStatus;
        try {
            orderStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new BarmobRestException("TODO - INPUT VALIDATION ERROR CODE ENUM", "TODO GET MESSAGE ERROR CODE");
        }
        return orderActions.getOrdersByStatus(orderStatus);

    }





}

