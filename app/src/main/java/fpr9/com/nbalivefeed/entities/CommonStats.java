package fpr9.com.nbalivefeed.entities;

import java.util.List;

/**
 * Created by FranciscoPR on 01/11/16.
 */
public class CommonStats {

    List<ResultSet> resultSets;
    private List<String> TeamHome;
    private List<String> TeamAway;

    public List<String> getTeamHome() {
        return TeamHome;
    }

    public void setTeamHome(List<String> teamHome) {
        TeamHome = teamHome;
    }

    public List<String> getTeamAway() {
        return TeamAway;
    }

    public void setTeamAway(List<String> teamAway) {
        TeamAway = teamAway;
    }

    public List<ResultSet> getResultSets() {
        return resultSets;
    }

    public void setResultSets(List<ResultSet> resultSets) {
        this.resultSets = resultSets;
    }
}
