package barmob.persistance.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by gustavokm90 on 3/17/15.
 */
@Document(collection = "menu")
public class Menu {

    @Id
    private int id;

    private String name;
    private double price;
    private boolean availability;
    private String details;
    private String type;

    @PersistenceConstructor
    public Menu(int id, String name, double price, boolean availability, String details, String type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.details = details;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", details='" + details + '\'' +
                ", type=" + type +
                '}';
    }
}
