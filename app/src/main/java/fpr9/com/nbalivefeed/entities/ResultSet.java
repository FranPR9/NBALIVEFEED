package fpr9.com.nbalivefeed.entities;

import java.util.List;

/**
 * Created by FranciscoPR on 01/11/16.
 */
public class ResultSet {

    private List<String> rowSet;
    private List<Player> playerStats;
    private List<String> gameIds;



    public List<String> getGameIds() {
        return gameIds;
    }

    public void setGameIds(List<String> gameIds) {
        this.gameIds = gameIds;
    }


    public List<Player> getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(List<Player> playerStats) {
        this.playerStats = playerStats;
    }

    public List<String> getRowSet() {
        return rowSet;
    }

    public void setRowSet(List<String> rowSet) {
        this.rowSet = rowSet;
    }

    /*
    public List<TeamRowSetInfo> getRowSet() {
        return rowSet;
    }

    public void setRowSet(List<TeamRowSetInfo> rowSet) {
        this.rowSet = rowSet;
    }
    */
}
