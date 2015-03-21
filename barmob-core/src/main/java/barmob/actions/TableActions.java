package barmob.actions;

import barmob.exceptions.BarmobRestException;
import barmob.resttypes.Counters;
import barmob.resttypes.Table;
import barmob.resttypes.TableStatus;
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
public class TableActions extends Action {

    private TableRepository tableRepository = ctx.getBean(TableRepository.class);

    public Table createTable(int tableNumber, String password){
        mongoTemplate.updateFirst(query(where("id").is("table_id")), update("seq", sequence.getSeq("table_id").getSeq() + 1), Counters.class);
        Table table = new Table(sequence.getSeq("table_id").getSeq(),tableNumber,0,password,TableStatus.FREE.toString());
        table = tableRepository.insert(table);
        return table;
    }

    public boolean deleteTable(int tableId){
        tableRepository.delete(tableRepository.getTableById(tableId));
        return tableRepository.getTableById(tableId) == null;
    }

    public Table getTable(int table_id){
        Table table = tableRepository.getTableById(table_id);
        if (table != null){
            return table;
        }else{
            throw new BarmobRestException("TODO table not found","TODO GET MESSAGE ERROR CODE");
        }
    }

    public List<Table> getAllTables(){
        List<Table> tableList = tableRepository.findAll();
        if(!tableList.isEmpty()) {
            return tableList;
        }else{
            throw new BarmobRestException("TODO no tables registered","TODO GET MESSAGE ERROR CODE");
        }
    }


    public HashMap<String,String> startTable(int tableId){
        String tableStatus = tableRepository.getTableById(tableId).getStatus();
        if (tableStatus.equals(TableStatus.FREE.toString())){
            mongoTemplate.updateFirst(query(where("id").is(tableId)), update("status", TableStatus.BUSY.toString()), Table.class);
            mongoTemplate.updateFirst(query(where("id").is("clientId")), update("seq", sequence.getSeq("clientId").getSeq() + 1), Counters.class);
            mongoTemplate.updateFirst(query(where("id").is(tableId)), update("clientId", sequence.getSeq("clientId").getSeq()), Table.class);
            HashMap<String,String> ret = new HashMap<String,String>();
            ret.put("status",TableStatus.BUSY.toString());
            return ret;
        }else {
            throw new BarmobRestException("TODO table should be free and it is actually "+tableStatus,"TODO GET MESSAGE ERROR CODE");
        }
    }

    public HashMap<String,String> closeTable(int tableId){
        String tableStatus = tableRepository.getTableById(tableId).getStatus();
        if (tableStatus.equals(TableStatus.BUSY.toString())){
            mongoTemplate.updateFirst(query(where("id").is(tableId)), update("status", TableStatus.CLOSED.toString()), Table.class);
            HashMap<String,String> ret = new HashMap<String,String>();
            ret.put("status", TableStatus.CLOSED.toString());
            return ret;
        }else {
            throw new BarmobRestException("TODO table should be busy and it is actually "+tableStatus,"TODO GET MESSAGE ERROR CODE");
        }
    }


    public HashMap<String,String> resetTable(int tableId){
        String tableStatus = tableRepository.getTableById(tableId).getStatus();
        if (tableStatus.equals(TableStatus.CLOSED.toString())){
            mongoTemplate.updateFirst(query(where("id").is(tableId)), update("status", TableStatus.FREE.toString()), Table.class);
            HashMap<String,String> ret = new HashMap<String,String>();
            ret.put("status", TableStatus.FREE.toString());
            return ret;
        }else {
            throw new BarmobRestException("TODO table should be closed and it is actually "+tableStatus,"TODO GET MESSAGE ERROR CODE");
        }
    }

}
