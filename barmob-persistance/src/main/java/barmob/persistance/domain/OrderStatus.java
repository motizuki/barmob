package barmob.persistance.domain;

/**
 * Created by gustavokm90 on 3/17/15.
 */
public enum OrderStatus {
    INPROGRESS, WAITINGCHEF, INPREPARATION, READY, CANCELED;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }


}
