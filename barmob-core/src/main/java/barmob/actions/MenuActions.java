package barmob.actions;

import barmob.exceptions.BarmobRestException;
import barmob.resttypes.Counters;
import barmob.resttypes.MenuTypes;
import barmob.persistance.repositories.MenuRepository;
import barmob.resttypes.Menu;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@Component
public class MenuActions extends Action{

    private MenuRepository menuRepository = ctx.getBean(MenuRepository.class);

    public Menu createMenu(String name, double price, boolean availability, String details, MenuTypes type){
        mongoTemplate.updateFirst(query(where("id").is("menu_id")), update("seq", sequence.getSeq("menu_id").getSeq() + 1), Counters.class);
        Menu menu = new Menu(sequence.getSeq("menu_id").getSeq(),name,price,availability,details,type.toString());
        menu = menuRepository.insert(menu);
        return menu;
    }

    public boolean deleteMenu(int menuid){
        menuRepository.delete(menuRepository.getMenuById(menuid));
        return menuRepository.getMenuById(menuid) == null;
    }

    public boolean updateMenu(int menuid, String field, Object value){
        mongoTemplate.updateFirst(query(where("id").is(menuid)), update(field, value) , Menu.class);
        return mongoTemplate.find(query(where(field).is(value).andOperator(where("id").is(menuid))), Menu.class) != null;
    }

    public Menu getMenu(int menuid){
        Menu menu = menuRepository.getMenuById(menuid);
        if (menu != null){
            return menu;
        }else{
            throw new BarmobRestException("TODO Menu not found","TODO GET MESSAGE ERROR CODE");
        }
    }

    public List<Menu> getMenuType(MenuTypes type){
        List<Menu> menuList = menuRepository.getMenuByType(type.toString());
        if(menuList.size()>0){
            return menuList;
        }else{
            throw new BarmobRestException("TODO no menu registered","TODO GET MESSAGE ERROR CODE");
        }

    }



}
