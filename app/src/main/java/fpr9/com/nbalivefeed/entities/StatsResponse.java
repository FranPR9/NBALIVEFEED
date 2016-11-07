package fpr9.com.nbalivefeed.entities;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class StatsResponse {

    private String arena;
    private String gameDate;
    private String gameTime;
    private String location;

    private StatsHomeAway stats;
    private StatScore score;


    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDay) {
        this.gameDate = gameDay;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public StatScore getScore() {
        return score;
    }

    public void setScore(StatScore score) {
        this.score = score;
    }

    public String getArena() {
        return arena;
    }

    public void setArena(String arena) {
        this.arena = arena;
    }



    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public StatsHomeAway getStats() {
        return stats;
    }

    public void setStats(StatsHomeAway stats) {
        this.stats = stats;
    }
}
