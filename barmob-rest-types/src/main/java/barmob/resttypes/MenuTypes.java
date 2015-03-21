package barmob.resttypes;

/**
 * Created by gustavokm90 on 3/17/15.
 */
public enum MenuTypes {
    ENTRANCE, MAIN, DRINK, DESSERT, BREAKFAST, PROMOTION;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }

}
