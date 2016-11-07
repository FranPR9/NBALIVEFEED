package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed;

import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.event.DetailEvent;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public interface DetailPresenter {

    void onCreate();
    void onDestroy();

    void getGameStats(String gameid);
    void getPlaybyPlay(String gameid);

    void GameInfoRespond(DetailEvent event);

    void getCommonHomeStats(String teamid);
    void getCommonAwayStats(String teamid);

    void getHomeStats(String teamid);
    void getAwayStats(String teamid);

    void getGamesHome(String teamid);
    void getGamesAway(String teamid);

    void getInjuryReport(String cityHome,String cityAway);

    void getOdds();

    void getRecord(String gameid);

}
