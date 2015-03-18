package barmob.persistance.domain;

/**
 * Created by gustavokm90 on 3/17/15.
 */
public enum TableStatus {
    FREE,BUSY,CLOSED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
