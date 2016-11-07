package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.event;

import java.util.List;

import fpr9.com.nbalivefeed.entities.CommonStats;
import fpr9.com.nbalivefeed.entities.Injury;
import fpr9.com.nbalivefeed.entities.Odd;
import fpr9.com.nbalivefeed.entities.PlaysResponse;
import fpr9.com.nbalivefeed.entities.Pregame;
import fpr9.com.nbalivefeed.entities.StatsResponse;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class DetailEvent {

    private int type;
    private int size;
    private String error;
    private List<Odd> odds;
    private boolean success;
    private PlaysResponse playsResponse;
    private StatsResponse statsResponse;
    private CommonStats commonStats;
    private Injury injuries;
    private Pregame pregame;

    public static final int STATS_TYPE = 1;
    public static final int PBP_TYPE = 2;
    public static final int COMMON_STATS_HOME = 3;
    public static final int COMMON_STATS_AWAY = 4;
    public static final int STATS_HOME = 5;
    public static final int STATS_AWAY = 6;
    public static final int GAMES_HOME = 7;
    public static final int GAMES_AWAY = 8;
    public static final int INJURY_REPORT = 9;
    public static final int ODDS = 10;
    public static final int PRE_GAME = 11;

    public Pregame getPregame() {
        return pregame;
    }

    public void setPregame(Pregame pregame) {
        this.pregame = pregame;
    }

    public Injury getInjuries() {
        return injuries;
    }

    public void setInjuries(Injury injuries) {
        this.injuries = injuries;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public CommonStats getCommonStats() {
        return commonStats;
    }

    public void setCommonStats(CommonStats commonStats) {
        this.commonStats = commonStats;
    }

    public PlaysResponse getPlaysResponse() {
        return playsResponse;
    }

    public void setPlaysResponse(PlaysResponse playsResponse) {
        this.playsResponse = playsResponse;
    }

    public StatsResponse getStatsResponse() {
        return statsResponse;
    }

    public void setStatsResponse(StatsResponse statsResponse) {
        this.statsResponse = statsResponse;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setOdds(List<Odd> odds) {
        this.odds = odds;
    }

    public List<Odd> getOdds() {
        return odds;
    }
}
