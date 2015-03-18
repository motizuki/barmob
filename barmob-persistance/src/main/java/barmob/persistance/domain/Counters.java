package barmob.persistance.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by gustavokm90 on 3/18/15.
 */
@Document(collection = "counters")
public class Counters {

    @Id
    private String id;
    private int seq;

    public Counters(String id, int seq) {
        this.id = id;
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Counters{" +
                "id='" + id + '\'' +
                ", seq=" + seq +
                '}';
    }
}
