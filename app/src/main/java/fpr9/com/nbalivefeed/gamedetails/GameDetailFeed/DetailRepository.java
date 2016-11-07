package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public interface DetailRepository {
    void getStats(String gameid);
    void getPlaybyPlay(String gameid);
    void getHomeCommonStats(String teamid);
    void getAwayCommonStats(String teamid);
    void getHomeStats(String teamid);
    void getAwayStats(String teamid);
    void getLastGamesHome(String teamid);
    void getLastGamesAway(String teamid);
    void getInjuryReport(String cityHome,String cityAway);
    void getOdds();
    void getRecord(String gameid);
}
