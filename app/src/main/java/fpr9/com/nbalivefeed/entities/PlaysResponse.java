package fpr9.com.nbalivefeed.entities;

import java.util.List;

/**
 * Created by FranciscoPR on 30/10/16.
 */
public class PlaysResponse {

    private List<Quarter> quarters;

    public List<Quarter> getQuarters() {
        return quarters;
    }

    public void setQuarters(List<Quarter> quarters) {
        this.quarters = quarters;
    }
}
