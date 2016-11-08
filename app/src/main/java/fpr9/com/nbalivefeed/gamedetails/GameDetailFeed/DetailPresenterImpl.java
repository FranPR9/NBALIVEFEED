package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fpr9.com.nbalivefeed.entities.Game;
import fpr9.com.nbalivefeed.entities.GameTeam;
import fpr9.com.nbalivefeed.entities.Player;
import fpr9.com.nbalivefeed.entities.TeamStats;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.event.DetailEvent;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.DetailFeedView;
import fpr9.com.nbalivefeed.lib.EventBus;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class DetailPresenterImpl implements DetailPresenter {

    private static final String TAG = "DetailPresenter";
    EventBus eventBus;
    DetailFeedView view;
    DetailInteractor interactor;
    //private List<Player> playerStats;


    public DetailPresenterImpl(EventBus eventBus, DetailFeedView view, DetailInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
    }

    @Override
    public void getGameStats(String gameid) {
        interactor.executeStats(gameid);
    }

    @Override
    public void getPlaybyPlay(String gameid) {
        interactor.executePlaybyPlay(gameid);
    }

    @Override
    @Subscribe
    public void GameInfoRespond(DetailEvent event) {
        view.hideRefresh();
        Log.d(TAG,"type:"+event.getType());
        if (event.isSuccess()) {
            switch (event.getType()) {
                case DetailEvent.STATS_TYPE:
                    view.showGameInfo(event.getStatsResponse());
                    view.showTeamLeaders2Home(setPlayerStats2(event.getStatsResponse().getStats().getHome().getPlayers()));
                    view.showTeamLeaders2Away(setPlayerStats2(event.getStatsResponse().getStats().getVisitor().getPlayers()));
                    break;
                case DetailEvent.PBP_TYPE:
                    view.showGamePlays(event.getPlaysResponse());
                    break;
                case DetailEvent.COMMON_STATS_AWAY:
                    view.showAwayCommonStats(event.getCommonStats());
                    break;
                case DetailEvent.COMMON_STATS_HOME:
                    view.showHomeCommonStats(event.getCommonStats());
                    break;
                case DetailEvent.STATS_HOME:
                    view.showHomeStats(event.getCommonStats());
                    view.showTeamLeadersHome(setPlayerStats(event.getCommonStats().getResultSets().get(1).getPlayerStats()));
                    break;
                case DetailEvent.STATS_AWAY:
                    view.showAwayStats(event.getCommonStats());
                    view.showTeamLeadersAway(setPlayerStats(event.getCommonStats().getResultSets().get(1).getPlayerStats()));
                    break;
                case DetailEvent.GAMES_AWAY:
                    createPrevGamesResponseAway(event.getCommonStats().getTeamAway(),
                            event.getCommonStats().getTeamHome(),event.getSize());
                    break;
                case DetailEvent.GAMES_HOME:
                    createPrevGamesResponseHome(event.getCommonStats().getTeamAway(),
                            event.getCommonStats().getTeamHome(),event.getSize());
                    break;
                case DetailEvent.INJURY_REPORT:
                    view.showInjuryReportHome(event.getInjuries().getHomeTeam());
                    view.showInjuryReportAway(event.getInjuries().getAwayTeam());
                    break;
                case DetailEvent.ODDS:
                    view.showOdds(event.getOdds());
                    break;
                case DetailEvent.PRE_GAME:
                    view.showPreGame(event.getPregame());
                    break;
            }
        }
    }
    private int teamaway_count = 0;
    private List<Game> awayGames;
    private List<Game> homeGames;
    private void createPrevGamesResponseAway(List<String> teamAway,List<String> teamHome, int size) {
        if(teamaway_count==0){
            awayGames = new ArrayList<Game>();
        }
        teamaway_count++;
        Game game = new Game();
        game.setId(teamAway.get(2));
        GameTeam away = new GameTeam();
        away.setId(teamAway.get(3));away.setAcronym(teamAway.get(4));away.setCity(teamAway.get(5));away.setName(teamAway.get(6));
        away.setScore(Integer.parseInt(teamAway.get(teamAway.size()-1)));
        away.setDate(teamAway.get(0));
        GameTeam home = new GameTeam();
        home.setId(teamHome.get(3));home.setAcronym(teamHome.get(4));home.setCity(teamHome.get(5));home.setName(teamHome.get(6));
        home.setScore(Integer.parseInt(teamHome.get(teamHome.size()-1)));
        home.setDate(teamHome.get(0));
        game.setLocal(home);
        game.setVisitor(away);

        try {
            sortByDateAway(game,size);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //awayGames.add(game);



    }
    private int teamhome_count = 0;
    private void createPrevGamesResponseHome(List<String> teamAway,List<String> teamHome, int size) {
        if(teamhome_count==0){
            homeGames = new ArrayList<Game>();
        }
        teamhome_count++;
        Game game = new Game();
        game.setId(teamAway.get(2));
        GameTeam away = new GameTeam();
        away.setId(teamAway.get(3));away.setAcronym(teamAway.get(4));away.setCity(teamAway.get(5));away.setName(teamAway.get(6));
        away.setScore(Integer.parseInt(teamAway.get(teamAway.size()-1)));
        away.setDate(teamAway.get(0));
        GameTeam home = new GameTeam();
        home.setId(teamHome.get(3));home.setAcronym(teamHome.get(4));home.setCity(teamHome.get(5));home.setName(teamHome.get(6));
        home.setScore(Integer.parseInt(teamHome.get(teamHome.size()-1)));
        home.setDate(teamHome.get(0));
        game.setLocal(home);
        game.setVisitor(away);

        try {
            sortByDate(game,size);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //homeGames.add(game);



    }

    private void sortByDate(Game game,int size) throws ParseException {
        for(int i=0;i<homeGames.size();i++){
            Game aux = homeGames.get(i);
            if(game.getLocal().getGameDate(aux.getLocal().getDate()).after(aux.getLocal().getGameDate(aux.getLocal().getDate()))){
                homeGames.add(i,game);
                return;
            }
        }
        homeGames.add(game);

        if(teamhome_count==size){
            view.showHomeTeamPrevGames(homeGames);
        }
    }
    private void sortByDateAway(Game game,int size) throws ParseException {
        for(int i=0;i<awayGames.size();i++){
            Game aux = awayGames.get(i);
            if(game.getLocal().getGameDate(aux.getLocal().getDate()).after(aux.getLocal().getGameDate(aux.getLocal().getDate()))){
                awayGames.add(i,game);
                return;
            }
        }
        awayGames.add(game);
        if(teamaway_count==size){
            view.showAwayTeamPrevGames(awayGames);
        }
    }


    @Override
    public void getCommonHomeStats(String teamid) {
        interactor.executeHomeCommonStats(teamid);
    }

    @Override
    public void getCommonAwayStats(String teamid) {
        interactor.executeAwayCommonStats(teamid);
    }

    @Override
    public void getHomeStats(String teamid) {
        interactor.executeHomeStats(teamid);
    }

    @Override
    public void getAwayStats(String teamid) {
        interactor.executeAwayStats(teamid);
    }


    @Override
    public void getGamesAway(String teamid) {
        interactor.executeLastGamesAway(teamid);
    }

    @Override
    public void getInjuryReport(String cityHome, String cityAway) {
        interactor.executeInjuryReport(cityHome, cityAway);
    }

    @Override
    public void getOdds() {
        interactor.executeOdds();
    }

    @Override
    public void getRecord(String gameid) {
        interactor.executeRecord(gameid);
    }

    @Override
    public void getGamesHome(String teamid) {
        interactor.executeLastGamesHome(teamid);
    }

    public List<TeamStats> setPlayerStats2(List<TeamStats> playerStats) {

        TeamStats blks = playerStats.get(0);
        TeamStats assists= playerStats.get(0);
        TeamStats points = playerStats.get(0);
        TeamStats stls = playerStats.get(0);
        TeamStats rebs = playerStats.get(0);



        for (TeamStats player:playerStats){
            if (blks.getBlocks()<=player.getBlocks()){
                blks = player;
            }
            if (assists.getAssists()<=player.getAssists()){
                assists = player;
            }
            if (points.getPoints()<=player.getPoints()){
                points = player;
            }
            if (stls.getSteals()<=player.getSteals()){
                stls = player;
            }
            if (rebs.getRebounds()<=player.getRebounds()){
                rebs = player;
            }
        }

        playerStats.clear();
        playerStats.add(points);
        playerStats.add(assists);
        playerStats.add(rebs);
        playerStats.add(stls);
        playerStats.add(blks);

        return playerStats;

    }

    public List<Player> setPlayerStats(List<Player> playerStats) {

        Player blks = playerStats.get(0);
        Player assists= playerStats.get(0);
        Player points = playerStats.get(0);
        Player stls = playerStats.get(0);
        Player rebs = playerStats.get(0);



        for (Player player:playerStats){
            if (blks.getBLK()<=player.getBLK()){
                blks = player;
            }
            if (assists.getAST()<=player.getAST()){
                assists = player;
            }
            if (points.getPTS()<=player.getPTS()){
                points = player;
            }
            if (stls.getSTL()<=player.getSTL()){
                stls = player;
            }
            if (rebs.getREB()<=player.getREB()){
                rebs = player;
            }
        }

        playerStats.clear();
        playerStats.add(points);
        playerStats.add(assists);
        playerStats.add(rebs);
        playerStats.add(stls);
        playerStats.add(blks);

        return playerStats;

    }
}
