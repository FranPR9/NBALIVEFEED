package fpr9.com.nbalivefeed.entities;

import java.util.List;

/**
 * Created by FranciscoPR on 05/11/16.
 */
public class BetContainer {

    List<Odd> odds;

    public List<Odd> getOdds() {
        return odds;
    }

    public void setOdds(List<Odd> odds) {
        this.odds = odds;
    }
}
