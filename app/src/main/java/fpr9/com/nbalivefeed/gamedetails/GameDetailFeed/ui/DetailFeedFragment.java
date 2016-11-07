package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpr9.com.nbalivefeed.BuildConfig;
import fpr9.com.nbalivefeed.NBAlivefeedApp;
import fpr9.com.nbalivefeed.R;
import fpr9.com.nbalivefeed.entities.CommonStats;
import fpr9.com.nbalivefeed.entities.Game;
import fpr9.com.nbalivefeed.entities.Odd;
import fpr9.com.nbalivefeed.entities.Play;
import fpr9.com.nbalivefeed.entities.Player;
import fpr9.com.nbalivefeed.entities.PlaysResponse;
import fpr9.com.nbalivefeed.entities.Pregame;
import fpr9.com.nbalivefeed.entities.StatsHomeAway;
import fpr9.com.nbalivefeed.entities.StatsResponse;
import fpr9.com.nbalivefeed.entities.TeamStats;
import fpr9.com.nbalivefeed.gamedetails.GameDetailActivity;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailPresenter;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.di.DetailComponent;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.adapter.PlaysAdapter;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.GamesAdapter;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class DetailFeedFragment extends Fragment implements DetailFeedView {


    private static final String TAG = "DetailFeedFragment";
    @Bind(R.id.img_pts_away)
    ImageView imgPtsAway;
    @Bind(R.id.name_pts_away)
    TextView namePtsAway;
    @Bind(R.id.pts_leader_away)
    TextView ptsLeaderAway;
    @Bind(R.id.pts_leader_home)
    TextView ptsLeaderHome;
    @Bind(R.id.img_pts_home)
    ImageView imgPtsHome;
    @Bind(R.id.name_pts_home)
    TextView namePtsHome;
    @Bind(R.id.img_assists_away)
    ImageView imgAssistsAway;
    @Bind(R.id.name_assists_away)
    TextView nameAssistsAway;
    @Bind(R.id.assists_leader_away)
    TextView assistsLeaderAway;
    @Bind(R.id.assists_leader_home)
    TextView assistsLeaderHome;
    @Bind(R.id.img_assists_home)
    ImageView imgAssistsHome;
    @Bind(R.id.name_assists_home)
    TextView nameAssistsHome;
    @Bind(R.id.img_reb_away)
    ImageView imgRebAway;
    @Bind(R.id.name_reb_away)
    TextView nameRebAway;
    @Bind(R.id.reb_leader_away)
    TextView rebLeaderAway;
    @Bind(R.id.reb_leader_home)
    TextView rebLeaderHome;
    @Bind(R.id.img_reb_home)
    ImageView imgRebHome;
    @Bind(R.id.name_reb_home)
    TextView nameRebHome;
    @Bind(R.id.img_stls_away)
    ImageView imgStlsAway;
    @Bind(R.id.name_stls_away)
    TextView nameStlsAway;
    @Bind(R.id.stls_leader_away)
    TextView stlsLeaderAway;
    @Bind(R.id.stls_leader_home)
    TextView stlsLeaderHome;
    @Bind(R.id.img_stls_home)
    ImageView imgStlsHome;
    @Bind(R.id.name_stls_home)
    TextView nameStlsHome;
    @Bind(R.id.img_blks_away)
    ImageView imgBlksAway;
    @Bind(R.id.name_blks_away)
    TextView nameBlksAway;
    @Bind(R.id.blks_leader_away)
    TextView blksLeaderAway;
    @Bind(R.id.blks_leader_home)
    TextView blksLeaderHome;
    @Bind(R.id.img_blks_home)
    ImageView imgBlksHome;
    @Bind(R.id.name_blks_home)
    TextView nameBlksHome;
    @Bind(R.id.preGamesAway)
    RecyclerView preGamesAway;
    @Bind(R.id.preGamesHome)
    RecyclerView preGamesHome;
    @Bind(R.id.prevGamesContainer)
    LinearLayout prevGamesContainer;
    @Bind(R.id.injuries_home)
    ListView injuriesHome;
    @Bind(R.id.injuries_away)
    ListView injuriesAway;

    @Bind(R.id.homeInjuryName)
    TextView homeInjuryName;


    @Bind(R.id.injuryHomeTitlesContainer)
    LinearLayout injuryHomeTitlesContainer;
    @Bind(R.id.awayInjuryName)
    TextView awayInjuryName;


    @Bind(R.id.injuryAwayTitlesContainer)
    LinearLayout injuryAwayTitlesContainer;
    @Bind(R.id.home_common_img)
    ImageView homeCommonImg;
    @Bind(R.id.away_common_img)
    ImageView awayCommonImg;
    @Bind(R.id.preGames)
    TextView preGames;
    @Bind(R.id.ouOdd)
    TextView ouOdd;


    @Bind(R.id.oddAwayImg)
    ImageView oddAwayImg;
    @Bind(R.id.mlAwayOdd)
    TextView mlAwayOdd;

    @Bind(R.id.oddHomeImg)
    ImageView oddHomeImg;
    @Bind(R.id.mlHomeOdd)
    TextView mlHomeOdd;

    @Bind(R.id.spreadOddImg)
    ImageView spreadOddImg;
    @Bind(R.id.spreadOdd)
    TextView spreadOdd;
    @Bind(R.id.oddContainer)
    CardView oddContainer;
    @Bind(R.id.injuryText)
    TextView injuryText;

    private String imgUrl = BuildConfig.player_url;

    @Bind(R.id.assists_away)
    TextView assistsAway;
    @Bind(R.id.assists_home)
    TextView assistsHome;
    @Bind(R.id.fg_away)
    TextView fgAway;
    @Bind(R.id.fg_home)
    TextView fgHome;
    @Bind(R.id.freethrows_away)
    TextView freethrowsAway;
    @Bind(R.id.freethrows_home)
    TextView freethrowsHome;
    @Bind(R.id.triples_away)
    TextView triplesAway;
    @Bind(R.id.triples_home)
    TextView triplesHome;
    @Bind(R.id.rebounds_away)
    TextView reboundsAway;
    @Bind(R.id.rebounds_home)
    TextView reboundsHome;
    @Bind(R.id.or_away)
    TextView orAway;
    @Bind(R.id.or_home)
    TextView orHome;
    @Bind(R.id.dreb_away)
    TextView drebAway;
    @Bind(R.id.dreb_home)
    TextView drebHome;
    @Bind(R.id.stl_away)
    TextView stlAway;
    @Bind(R.id.stl_home)
    TextView stlHome;
    @Bind(R.id.blks_away)
    TextView blksAway;
    @Bind(R.id.blks_home)
    TextView blksHome;
    @Bind(R.id.to_away)
    TextView toAway;
    @Bind(R.id.to_home)
    TextView toHome;
    @Bind(R.id.fouls_away)
    TextView foulsAway;
    @Bind(R.id.fouls_home)
    TextView foulsHome;

    DetailPresenter presenter;
    ImageLoader imageLoader;
    PlaysAdapter playsAdapter;
    GamesAdapter gamesAdapterHome;

    @Bind(R.id.away_img)
    ImageView awayImg;
    @Bind(R.id.name_away)
    TextView nameAway;
    @Bind(R.id.city_away)
    TextView cityAway;
    @Bind(R.id.score_away)
    TextView scoreAway;
    @Bind(R.id.arena)
    TextView arena;
    @Bind(R.id.gameday)
    TextView gameday;
    @Bind(R.id.gametime)
    TextView gametime;
    @Bind(R.id.period)
    TextView period;
    @Bind(R.id.time_left)
    TextView timeLeft;
    @Bind(R.id.status)
    TextView status;
    @Bind(R.id.home_img)
    ImageView homeImg;
    @Bind(R.id.name_home)
    TextView nameHome;
    @Bind(R.id.city_home)
    TextView cityHome;
    @Bind(R.id.score_home)
    TextView scoreHome;
    @Bind(R.id.playbyplay)
    RecyclerView playbyplay;
    @Bind(R.id.home_team_name_cs)
    TextView homeTeamNameCs;
    @Bind(R.id.PPGH)
    TextView PPGH;
    @Bind(R.id.RPGH)
    TextView RPGH;
    @Bind(R.id.APGH)
    TextView APGH;
    @Bind(R.id.OPPGH)
    TextView OPPGH;
    @Bind(R.id.away_team_name_cs)
    TextView awayTeamNameCs;
    @Bind(R.id.PPGA)
    TextView PPGA;
    @Bind(R.id.RPGA)
    TextView RPGA;
    @Bind(R.id.APGA)
    TextView APGA;
    @Bind(R.id.OPPGA)
    TextView OPPGA;
    @Bind(R.id.commonStatsContainer)
    LinearLayout commonStatsContainer;
    private GamesAdapter gamesAdapterAway;

    private ArrayAdapter<String> injuriesHomeA;
    private ArrayAdapter<String> injuriesAwayA;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInjection();
        presenter.onCreate();
    }


    private void setInjection() {
        DetailComponent component = ((NBAlivefeedApp) getActivity().getApplication()).getDetailComponent((GameDetailActivity) getActivity(), this);
        presenter = component.getIndexPresenter();
        imageLoader = component.getImageLoader();
        playsAdapter = component.getPlaysAdapter();
        gamesAdapterHome = new GamesAdapter(imageLoader, null, null);
        gamesAdapterAway = new GamesAdapter(imageLoader, null, null);
        injuriesAwayA = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);
        injuriesHomeA = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game_detail, container, false);
        Log.d(TAG, "status:" + gameStatus);
        if (!gameStatus.equals("1")) {
            presenter.getGameStats(gameid);
            presenter.getPlaybyPlay(gameid);
        } else {
            presenter.getCommonHomeStats(homeid);
            presenter.getCommonAwayStats(awayid);
            presenter.getHomeStats(homeid);
            presenter.getAwayStats(awayid);
            presenter.getGamesHome(homeid);
            presenter.getGamesAway(awayid);
            presenter.getInjuryReport(homeC.toUpperCase(), awayC.toUpperCase());
            presenter.getOdds();
            presenter.getRecord(gameid);
        }


        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        playbyplay.setLayoutManager(new LinearLayoutManager(getActivity()));
        playbyplay.setAdapter(playsAdapter);

        preGamesHome.setLayoutManager(new LinearLayoutManager(getActivity()));
        preGamesHome.setAdapter(gamesAdapterHome);

        preGamesAway.setLayoutManager(new LinearLayoutManager(getActivity()));
        preGamesAway.setAdapter(gamesAdapterAway);

        injuriesHome.setAdapter(injuriesHomeA);
        injuriesAway.setAdapter(injuriesAwayA);

        if (home_img != null) {
            showImages(home_img, away_img);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        presenter.onDestroy();
    }

    @Override
    public void showGameInfo(StatsResponse response) {

        setScoreInfo(response);
        setStatsInfo(response.getStats());
    }

    private void setStatsInfo(StatsHomeAway stats) {

        assistsAway.setText(stats.getVisitor().getTeam().getAssists() + "");
        fgAway.setText(stats.getVisitor().getTeam().getFieldGoalsP() + "%");
        freethrowsAway.setText(stats.getVisitor().getTeam().getFreeThrowsP() + "%");
        triplesAway.setText(stats.getVisitor().getTeam().getTriplesP() + "%");
        reboundsAway.setText(stats.getVisitor().getTeam().getRebounds() + "");
        orAway.setText(stats.getVisitor().getTeam().getOffensiveRebounds() + "");
        drebAway.setText(stats.getVisitor().getTeam().getDefenseRebounds() + "");
        stlAway.setText(stats.getVisitor().getTeam().getSteals() + "");
        blksAway.setText(stats.getVisitor().getTeam().getBlocks() + "");
        toAway.setText(stats.getVisitor().getTeam().getTurnovers() + "");
        foulsAway.setText(stats.getVisitor().getTeam().getFouls() + "");

        assistsHome.setText(stats.getHome().getTeam().getAssists() + "");
        fgHome.setText(stats.getHome().getTeam().getFieldGoalsP() + "%");
        freethrowsHome.setText(stats.getHome().getTeam().getFreeThrowsP() + "%");
        triplesHome.setText(stats.getHome().getTeam().getTriplesP() + "%");
        reboundsHome.setText(stats.getHome().getTeam().getRebounds() + "");
        orHome.setText(stats.getHome().getTeam().getOffensiveRebounds() + "");
        drebHome.setText(stats.getHome().getTeam().getDefenseRebounds() + "");
        stlHome.setText(stats.getHome().getTeam().getSteals() + "");
        blksHome.setText(stats.getHome().getTeam().getBlocks() + "");
        toHome.setText(stats.getHome().getTeam().getTurnovers() + "");
        foulsHome.setText(stats.getHome().getTeam().getFouls() + "");

    }

    private void setScoreInfo(StatsResponse scoreInfo) {
        if (scoreInfo.getArena() != null)
            arena.setText(scoreInfo.getArena());
        if (scoreInfo.getGameDate() != null)
            gameday.setText(scoreInfo.getGameDate());
        if (scoreInfo.getGameTime() != null)
            gametime.setText(scoreInfo.getGameTime());
        if (scoreInfo.getScore() != null) {
            scoreHome.setText(scoreInfo.getScore().getHome().getScore() + "");
            scoreAway.setText(scoreInfo.getScore().getVisitor().getScore() + "");
            if (scoreInfo.getScore().getPeriodTime().getGameClock() != null) {
                timeLeft.setText(scoreInfo.getScore().getPeriodTime().getGameClock());
                timeLeft.setVisibility(View.VISIBLE);
            } else
                timeLeft.setVisibility(View.GONE);
            status.setText(scoreInfo.getScore().getPeriodTime().getPeriodStatus());
            period.setText(scoreInfo.getScore().getPeriodTime().getGamePeriod() + " Qtr");
        }
        if (scoreInfo.getScore() != null) {

        }


    }

    @Override
    public void showGamePlays(PlaysResponse playsResponse) {


        int size = playsResponse.getQuarters().get(period_int - 1).getPlays().size();
        Collections.reverse(playsResponse.getQuarters().get(period_int - 1).getPlays());
        if (size >= 5)
            playsAdapter.setPlays(playsResponse.getQuarters().get(period_int - 1).getPlays().subList(0, 5));
        else
            playsAdapter.setPlays(playsResponse.getQuarters().get(period_int - 1).getPlays());
    }

    @Override
    public void showServerError() {
        ((GameDetailActivity) getActivity()).showMessage(getString(R.string.serverDetailError));
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    private String gameid;

    private String home_img;
    private String away_img;

    @Override
    public void showImages(String home, String away) {
        imageLoader.load(homeImg, home);
        imageLoader.load(awayImg, away);
    }

    @Override
    public void setImages(String home, String away) {
        home_img = home;
        away_img = away;
    }

    @Override
    public void setGameId(String gameid) {
        this.gameid = gameid;
    }

    private int period_int;

    @Override
    public void setPeriod(int period) {
        this.period_int = period;
    }

    private String homeid;
    private String awayid;

    @Override
    public void setIds(String homeid, String awayid) {
        this.homeid = homeid;
        this.awayid = awayid;
    }

    private String gameStatus;

    @Override
    public void setStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    @Override
    public void showHomeCommonStats(CommonStats commonStats) {
        String PPG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(3);
        String RPG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(5);
        String APG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(7);
        String OPPG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(9);

        PPGH.setText(PPG);
        RPGH.setText(RPG);
        APGH.setText(APG);
        OPPGH.setText(OPPG);

        homeTeamNameCs.setText(homeName);
        imageLoader.load(homeCommonImg, home_img);

        commonStatsContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void showAwayCommonStats(CommonStats commonStats) {
        String PPG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(3);
        String RPG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(5);
        String APG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(7);
        String OPPG = commonStats.getResultSets().get(1).getRowSet()/*.get(0).getInfo()*/.get(9);

        PPGA.setText(PPG);
        RPGA.setText(RPG);
        APGA.setText(APG);
        OPPGA.setText(OPPG);

        awayTeamNameCs.setText(awayName);
        imageLoader.load(awayCommonImg, away_img);

        commonStatsContainer.setVisibility(View.VISIBLE);
    }

    private String homeName;
    private String awayName;
    private String homeC;
    private String awayC;
    private String acrHome;
    private String acrAway;

    @Override
    public void setNames(String homeName, String awayName, String homeC, String awayC, String acrHome, String acrAway) {
        this.homeName = homeName;
        this.awayName = awayName;
        this.homeC = homeC;
        this.awayC = awayC;
        this.acrAway = acrAway;
        this.acrHome = acrHome;
    }

    @Override
    public void showHomeStats(CommonStats commonStats) {

        assistsHome.setText(commonStats.getResultSets().get(0).getRowSet().get(21));
        fgHome.setText(commonStats.getResultSets().get(0).getRowSet().get(11) + "%");
        freethrowsHome.setText(commonStats.getResultSets().get(0).getRowSet().get(17) + "%");
        triplesHome.setText(commonStats.getResultSets().get(0).getRowSet().get(14) + "%");
        reboundsHome.setText(commonStats.getResultSets().get(0).getRowSet().get(20) + "");
        orHome.setText(commonStats.getResultSets().get(0).getRowSet().get(18) + "");
        drebHome.setText(commonStats.getResultSets().get(0).getRowSet().get(19) + "");
        stlHome.setText(commonStats.getResultSets().get(0).getRowSet().get(23) + "");
        blksHome.setText(commonStats.getResultSets().get(0).getRowSet().get(24) + "");
        toHome.setText(commonStats.getResultSets().get(0).getRowSet().get(22) + "");
        foulsHome.setText(commonStats.getResultSets().get(0).getRowSet().get(26) + "");
    }

    @Override
    public void showAwayStats(CommonStats commonStats) {
        assistsAway.setText(commonStats.getResultSets().get(0).getRowSet().get(21));
        fgAway.setText(commonStats.getResultSets().get(0).getRowSet().get(11) + "%");
        freethrowsAway.setText(commonStats.getResultSets().get(0).getRowSet().get(17) + "%");
        triplesAway.setText(commonStats.getResultSets().get(0).getRowSet().get(14) + "%");
        reboundsAway.setText(commonStats.getResultSets().get(0).getRowSet().get(20) + "");
        orAway.setText(commonStats.getResultSets().get(0).getRowSet().get(18) + "");
        drebAway.setText(commonStats.getResultSets().get(0).getRowSet().get(19) + "");
        stlAway.setText(commonStats.getResultSets().get(0).getRowSet().get(23) + "");
        blksAway.setText(commonStats.getResultSets().get(0).getRowSet().get(24) + "");
        toAway.setText(commonStats.getResultSets().get(0).getRowSet().get(22) + "");
        foulsAway.setText(commonStats.getResultSets().get(0).getRowSet().get(26) + "");
    }

    @Override
    public void showTeamLeadersHome(List<Player> teamLeaders) {

        ptsLeaderHome.setText(teamLeaders.get(0).getPTS() + "");
        namePtsHome.setText(teamLeaders.get(0).getPLAYER_NAME());
        imageLoader.load(imgPtsHome, imgUrl + teamLeaders.get(0).getPLAYER_ID() + ".png");

        assistsLeaderHome.setText(teamLeaders.get(1).getAST() + "");
        nameAssistsHome.setText(teamLeaders.get(1).getPLAYER_NAME());
        imageLoader.load(imgAssistsHome, imgUrl + teamLeaders.get(1).getPLAYER_ID() + ".png");

        rebLeaderHome.setText(teamLeaders.get(2).getREB() + "");
        nameRebHome.setText(teamLeaders.get(2).getPLAYER_NAME());
        imageLoader.load(imgRebHome, imgUrl + teamLeaders.get(2).getPLAYER_ID() + ".png");

        stlsLeaderHome.setText(teamLeaders.get(3).getSTL() + "");
        nameStlsHome.setText(teamLeaders.get(3).getPLAYER_NAME());
        imageLoader.load(imgStlsHome, imgUrl + teamLeaders.get(3).getPLAYER_ID() + ".png");

        blksLeaderHome.setText(teamLeaders.get(4).getBLK() + "");
        nameBlksHome.setText(teamLeaders.get(4).getPLAYER_NAME());
        imageLoader.load(imgBlksHome, imgUrl + teamLeaders.get(4).getPLAYER_ID() + ".png");

    }

    @Override
    public void showTeamLeadersAway(List<Player> teamLeaders) {

        ptsLeaderAway.setText(teamLeaders.get(0).getPTS() + "");
        namePtsAway.setText(teamLeaders.get(0).getPLAYER_NAME());
        imageLoader.load(imgPtsAway, imgUrl + teamLeaders.get(0).getPLAYER_ID() + ".png");

        assistsLeaderAway.setText(teamLeaders.get(1).getAST() + "");
        nameAssistsAway.setText(teamLeaders.get(1).getPLAYER_NAME());
        imageLoader.load(imgAssistsAway, imgUrl + teamLeaders.get(1).getPLAYER_ID() + ".png");

        rebLeaderAway.setText(teamLeaders.get(2).getREB() + "");
        nameRebAway.setText(teamLeaders.get(2).getPLAYER_NAME());
        imageLoader.load(imgRebAway, imgUrl + teamLeaders.get(2).getPLAYER_ID() + ".png");

        stlsLeaderAway.setText(teamLeaders.get(3).getSTL() + "");
        nameStlsAway.setText(teamLeaders.get(3).getPLAYER_NAME());
        imageLoader.load(imgStlsAway, imgUrl + teamLeaders.get(3).getPLAYER_ID() + ".png");

        blksLeaderAway.setText(teamLeaders.get(4).getBLK() + "");
        nameBlksAway.setText(teamLeaders.get(4).getPLAYER_NAME());
        imageLoader.load(imgBlksAway, imgUrl + teamLeaders.get(4).getPLAYER_ID() + ".png");
    }

    @Override
    public void showHomeTeamPrevGames(List<Game> games) {
        Log.d(TAG, "PrevHomeGames:" + games.size());
        //preGames.setVisibility(View.VISIBLE);
        gamesAdapterHome.setGames(games);
    }

    @Override
    public void showAwayTeamPrevGames(List<Game> games) {
        Log.d(TAG, "PrevAwayGames:" + games.size());
        preGames.setVisibility(View.VISIBLE);
        gamesAdapterAway.setGames(games);
    }

    @Override
    public void showTeamLeaders2Home(List<TeamStats> teamLeaders) {
        ptsLeaderHome.setText(teamLeaders.get(0).getPoints() + "");
        namePtsHome.setText(teamLeaders.get(0).getCompleteName());
        imageLoader.load(imgPtsHome, imgUrl + teamLeaders.get(0).getId() + ".png");

        assistsLeaderHome.setText(teamLeaders.get(1).getAssists() + "");
        nameAssistsHome.setText(teamLeaders.get(1).getCompleteName());
        imageLoader.load(imgAssistsHome, imgUrl + teamLeaders.get(1).getId() + ".png");

        rebLeaderHome.setText(teamLeaders.get(2).getRebounds() + "");
        nameRebHome.setText(teamLeaders.get(2).getCompleteName());
        imageLoader.load(imgRebHome, imgUrl + teamLeaders.get(2).getId() + ".png");

        stlsLeaderHome.setText(teamLeaders.get(3).getSteals() + "");
        nameStlsHome.setText(teamLeaders.get(3).getCompleteName());
        imageLoader.load(imgStlsHome, imgUrl + teamLeaders.get(3).getId() + ".png");

        blksLeaderHome.setText(teamLeaders.get(4).getBlocks() + "");
        nameBlksHome.setText(teamLeaders.get(4).getCompleteName());
        imageLoader.load(imgBlksHome, imgUrl + teamLeaders.get(4).getId() + ".png");
    }

    @Override
    public void showTeamLeaders2Away(List<TeamStats> teamLeaders) {
        ptsLeaderAway.setText(teamLeaders.get(0).getPoints() + "");
        namePtsAway.setText(teamLeaders.get(0).getCompleteName());
        imageLoader.load(imgPtsAway, imgUrl + teamLeaders.get(0).getId() + ".png");

        assistsLeaderAway.setText(teamLeaders.get(1).getAssists() + "");
        nameAssistsAway.setText(teamLeaders.get(1).getCompleteName());
        imageLoader.load(imgAssistsAway, imgUrl + teamLeaders.get(1).getId() + ".png");

        rebLeaderAway.setText(teamLeaders.get(2).getRebounds() + "");
        nameRebAway.setText(teamLeaders.get(2).getCompleteName());
        imageLoader.load(imgRebAway, imgUrl + teamLeaders.get(2).getId() + ".png");

        stlsLeaderAway.setText(teamLeaders.get(3).getSteals() + "");
        nameStlsAway.setText(teamLeaders.get(3).getCompleteName());
        imageLoader.load(imgStlsAway, imgUrl + teamLeaders.get(3).getId() + ".png");

        blksLeaderAway.setText(teamLeaders.get(4).getBlocks() + "");
        nameBlksAway.setText(teamLeaders.get(4).getCompleteName());
        imageLoader.load(imgBlksAway, imgUrl + teamLeaders.get(4).getId() + ".png");
    }

    @Override
    public void showInjuryReportHome(List<String> injuries) {
        homeInjuryName.setText(homeName);
        injuryHomeTitlesContainer.setVisibility(View.VISIBLE);
        if(injuries.size()<=0)injuries.add(getString(R.string.noInjuries));
        injuriesHomeA.addAll(injuries);
        injuriesHomeA.notifyDataSetChanged();
        setListViewHeightBasedOnItems(injuriesHome);
        injuryText.setVisibility(View.VISIBLE);
    }

    @Override
    public void showInjuryReportAway(List<String> injuries) {
        awayInjuryName.setText(awayName);
        injuryAwayTitlesContainer.setVisibility(View.VISIBLE);
        if(injuries.size()<=0)injuries.add(getString(R.string.noInjuries));
        injuriesAwayA.addAll(injuries);
        injuriesAwayA.notifyDataSetChanged();
        setListViewHeightBasedOnItems(injuriesAway);
    }

    @Override
    public void showOdds(List<Odd> odds) {
        for (Odd odd : odds) {
            if (odd.getAwayTeamName().contains(awayC))
                showOddInfo(odd);
        }
    }

    @Override
    public void showPreGame(Pregame pregame) {
        setDate(pregame.getGameDate());

        scoreHome.setText(pregame.getHome().getRecord().getOverall());
        scoreAway.setText(pregame.getVisitor().getRecord().getOverall());

    }

    private void setDate(String gameDate) {
        String date = gameDate.replace("T"," ");
        date = date.substring(0,date.length()-4);
        Log.d(TAG,"date:"+date);
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        sourceFormat.setTimeZone(TimeZone.getTimeZone("US/Eastern"));
        Date parsed = null; // => Date is in UTC now
        //Date timeparsed = null;
        try {
            parsed = sourceFormat.parse(date);
            //timeparsed = sourceFormat.parse(time);
            TimeZone tz = TimeZone.getDefault();
            SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat timeDestFormat = new SimpleDateFormat("HH:mm");
            destFormat.setTimeZone(tz);
            //for(String id : tz.getAvailableIDs())
            //Log.d(TAG,"availableIDS:"+id);
            Log.d(TAG,"tz"+tz.getDisplayName()+" parsed:"+parsed.toString());
            String result = destFormat.format(parsed);
            String timep = timeDestFormat.format(parsed);
            gameday.setText(result);
            gametime.setText(timep);
            period.setText("@");

        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    private void showOddInfo(Odd odd) {
        ouOdd.setText(odd.getOuTotal());
        imageLoader.load(oddAwayImg, away_img);
        imageLoader.load(oddHomeImg, home_img);
        mlAwayOdd.setText(odd.getMlAwayTeamOdds());
        mlHomeOdd.setText(odd.getMlHomeTeamOdds());
        if (odd.getAtsFavoredTeamAbbrv().contains(acrAway)) {
            imageLoader.load(spreadOddImg, away_img);
        } else {
            imageLoader.load(spreadOddImg, home_img);
        }
        spreadOdd.setText(odd.getAtsHandicap());
        oddContainer.setVisibility(View.VISIBLE);
    }


    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                item.measure(0, 0);
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight;
            listView.setLayoutParams(params);
            listView.requestLayout();

            return true;

        } else {
            return false;
        }

    }

}
