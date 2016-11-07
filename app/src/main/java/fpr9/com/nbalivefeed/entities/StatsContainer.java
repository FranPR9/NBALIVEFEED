package fpr9.com.nbalivefeed.entities;

import java.util.List;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class StatsContainer {

    private TeamStats team;
    private List<TeamStats> players;

    public List<TeamStats> getPlayers() {
        return players;
    }

    public void setPlayers(List<TeamStats> players) {
        this.players = players;
    }

    public TeamStats getTeam() {
        return team;
    }

    public void setTeam(TeamStats team) {
        this.team = team;
    }
}
