package fpr9.com.nbalivefeed.entities;

/**
 * Created by FranciscoPR on 30/10/16.
 */
public class PeriodTime {

    private String gameClock;
    private String periodStatus;
    private int gameStatus;
    private int gamePeriod;

    public String getGameClock() {
        return gameClock;
    }

    public void setGameClock(String gameClock) {
        this.gameClock = gameClock;
    }

    public String getPeriodStatus() {
        return periodStatus;
    }

    public void setPeriodStatus(String periodStatus) {
        this.periodStatus = periodStatus;
    }

    public int getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(int gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getGamePeriod() {
        return gamePeriod;
    }

    public void setGamePeriod(int gamePeriod) {
        this.gamePeriod = gamePeriod;
    }
}
