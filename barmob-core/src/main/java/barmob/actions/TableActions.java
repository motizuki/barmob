package barmob.actions;

import barmob.persistance.domain.Counters;
import barmob.persistance.domain.Table;
import barmob.persistance.domain.TableStatus;
import barmob.persistance.repositories.MenuRepository;
import barmob.persistance.repositories.OrderRepository;
import barmob.persistance.repositories.TableRepository;
import org.springframework.stereotype.Component;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@Component
public class TableActions extends Action {

    private TableRepository tableRepository = ctx.getBean(TableRepository.class);

    public Table createTable(int tableNumber, String password, TableStatus status){
        mongoTemplate.updateFirst(query(where("id").is("table_id")), update("seq", sequence.getSeq("table_id").getSeq() + 1), Counters.class);
        Table table = new Table(sequence.getSeq("table_id").getSeq(),tableNumber,password,status.toString());
        table = tableRepository.insert(table);
        return table;
    }

    public boolean deleteTable(int tableId){
        tableRepository.delete(tableRepository.getTableById(tableId));
        return tableRepository.getTableById(tableId) == null;
    }


    public boolean startTable(int tableId){
        String tableStatus = tableRepository.getTableById(tableId).getStatus();
        if (tableStatus.equals(TableStatus.FREE.toString())){
            mongoTemplate.updateFirst(query(where("id").is(tableId)), update("status", TableStatus.BUSY.toString()), Table.class);
            return tableRepository.getTableById(tableId).getStatus().equals(TableStatus.BUSY.toString());
        }else {
            return false;
        }
    }

    public boolean closeTable(int tableId){
        String tableStatus = tableRepository.getTableById(tableId).getStatus();
        if (tableStatus.equals(TableStatus.BUSY.toString())){
            mongoTemplate.updateFirst(query(where("id").is(tableId)), update("status", TableStatus.CLOSED.toString()), Table.class);
            return tableRepository.getTableById(tableId).getStatus().equals(TableStatus.CLOSED.toString());
        }else {
            return false;
        }
    }


    public boolean resetTable(int tableId){
        String tableStatus = tableRepository.getTableById(tableId).getStatus();
        if (tableStatus.equals(TableStatus.CLOSED.toString())){
            mongoTemplate.updateFirst(query(where("id").is(tableId)), update("status", TableStatus.FREE.toString()), Table.class);
            return tableRepository.getTableById(tableId).getStatus().equals(TableStatus.FREE.toString());
        }else {
            return false;
        }
    }

}
