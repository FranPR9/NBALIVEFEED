package fpr9.com.nbalivefeed.entities;

import java.util.List;

/**
 * Created by FranciscoPR on 03/11/16.
 */
public class Injury {

    List<String> homeTeam;
    List<String> awayTeam;

    public List<String> getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(List<String> homeTeam) {
        this.homeTeam = homeTeam;
    }

    public List<String> getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(List<String> awayTeam) {
        this.awayTeam = awayTeam;
    }
}
