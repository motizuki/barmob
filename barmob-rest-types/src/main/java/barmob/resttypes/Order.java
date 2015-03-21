package barmob.resttypes;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by gustavokm90 on 3/17/15.
 */
@Document(collection = "order")
public class Order {

    @Id
    private int id;

    @DBRef
    private Menu menu;
    @DBRef
    private Table table;

    private int clientId;
    private int amount;
    private String observation;
    private String status;

    @PersistenceConstructor
    public Order(int id,Menu menu, Table table, int clientId, int amount, String observation, String status) {
        this.menu = menu;
        this.table = table;
        this.clientId = clientId;
        this.id = id;
        this.amount = amount;
        this.observation = observation;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", menu_id='" + menu + '\'' +
                ", table_id='" + table + '\'' +
                ", amount=" + amount +
                ", observation='" + observation + '\'' +
                ", status=" + status +
                '}';
    }
}
