package antony.athul.com.expandingrecyclerview;

/**
 * Created by athul on 12/6/17.
 */

public class Models{

    String name;
    boolean preselect;
    boolean exclusive;

    public boolean isExclusive() {
        return exclusive;
    }

    public void setExclusive(boolean exclusive) {
        this.exclusive = exclusive;
    }

    public boolean isPreselect() {
        return preselect;
    }

    public void setPreselect(boolean preselect) {
        this.preselect = preselect;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}