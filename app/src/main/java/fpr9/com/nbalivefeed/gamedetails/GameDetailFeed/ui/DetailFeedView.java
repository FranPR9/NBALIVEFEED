package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui;

import java.util.List;

import fpr9.com.nbalivefeed.entities.CommonStats;
import fpr9.com.nbalivefeed.entities.Game;
import fpr9.com.nbalivefeed.entities.Odd;
import fpr9.com.nbalivefeed.entities.Player;
import fpr9.com.nbalivefeed.entities.PlaysResponse;
import fpr9.com.nbalivefeed.entities.Pregame;
import fpr9.com.nbalivefeed.entities.StatsResponse;
import fpr9.com.nbalivefeed.entities.TeamStats;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public interface DetailFeedView {

    void showGameInfo(StatsResponse response);
    void showGamePlays(PlaysResponse response);

    void showServerError();

    void showProgress();
    void hideProgress();

    void showImages(String home, String away);
    void setImages(String home, String away);

    void setGameId(String gameid);

    void setPeriod(int period);

    void setIds(String homeid, String awayid);

    void setStatus(String gameStatus);

    void showHomeCommonStats(CommonStats commonStats);
    void showAwayCommonStats(CommonStats commonStats);

    void setNames(String homeName, String awayName, String homeC, String awayC,String acrHome,String acrAway);

    void showHomeStats(CommonStats commonStats);
    void showAwayStats(CommonStats commonStats);

    void showTeamLeadersHome(List<Player> teamLeaders);
    void showTeamLeadersAway(List<Player> teamLeaders);

    void showHomeTeamPrevGames(List<Game> games);
    void showAwayTeamPrevGames(List<Game> games);

    void showTeamLeaders2Home(List<TeamStats> teamStats);
    void showTeamLeaders2Away(List<TeamStats> teamStats);

    void showInjuryReportHome(List<String> injuries);
    void showInjuryReportAway(List<String> injuries);

    void showOdds(List<Odd> odds);

    void showPreGame(Pregame pregame);

    void hideRefresh();
}
