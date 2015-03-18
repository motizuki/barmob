package barmob.persistance.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by gustavokm90 on 3/17/15.
 */
@Document(collection = "table")
public class Table {

    @Id
    private int id;

    private int tableNumber;
    private String password;
    private String status;

    @PersistenceConstructor
    public Table(int id, int tableNumber, String password, String status) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.password = password;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id='" + id + '\'' +
                ", tableNumber=" + tableNumber +
                ", password='" + password + '\'' +
                ", status=" + status +
                '}';
    }
}
