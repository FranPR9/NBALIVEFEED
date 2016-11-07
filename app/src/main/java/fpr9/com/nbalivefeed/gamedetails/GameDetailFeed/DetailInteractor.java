package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public interface DetailInteractor {

    void executeStats(String gameid);
    void executePlaybyPlay(String gameid);
    void executeHomeCommonStats(String teamid);
    void executeAwayCommonStats(String teamid);
    void executeHomeStats(String teamid);
    void executeAwayStats(String teamid);
    void executeLastGamesHome(String teamid);
    void executeLastGamesAway(String teamid);
    void executeInjuryReport(String cityHome,String cityAway);
    void executeOdds();
    void executeRecord(String gameid);
}
