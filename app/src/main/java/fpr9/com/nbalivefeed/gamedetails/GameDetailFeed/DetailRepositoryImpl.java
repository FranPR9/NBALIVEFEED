package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed;

import android.util.Log;

import java.util.List;

import fpr9.com.nbalivefeed.BuildConfig;
import fpr9.com.nbalivefeed.api.StatsService;
import fpr9.com.nbalivefeed.entities.BetContainer;
import fpr9.com.nbalivefeed.entities.CommonStats;
import fpr9.com.nbalivefeed.entities.Injury;
import fpr9.com.nbalivefeed.entities.InjuryContainer;
import fpr9.com.nbalivefeed.entities.Odd;
import fpr9.com.nbalivefeed.entities.PlaysResponse;
import fpr9.com.nbalivefeed.entities.Pregame;
import fpr9.com.nbalivefeed.entities.RecordContainer;
import fpr9.com.nbalivefeed.entities.StatsResponse;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.event.DetailEvent;
import fpr9.com.nbalivefeed.lib.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class DetailRepositoryImpl implements DetailRepository {

    private static final String TAG = "DetailRepository";
    EventBus eventBus;
    StatsService statsService;
    String base_url = BuildConfig.base_url;
    String pbp_url = BuildConfig.pbp_url;
    String common_stats =BuildConfig.common_stats;
    String stats = BuildConfig.stats;
    String injury_report =BuildConfig.injury_report;
    String oddUrl = BuildConfig.oddUrl;
    String pre_game = BuildConfig.pre_game;

    public DetailRepositoryImpl(EventBus eventBus, StatsService statsService) {
        this.eventBus = eventBus;
        this.statsService = statsService;
    }

    @Override
    public void getStats(String gameid) {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        Log.d(TAG,"ts:"+ts);
        Call<StatsResponse> call = statsService.getScores(base_url+gameid+".js",ts);
        call.enqueue(new Callback<StatsResponse>() {
            @Override
            public void onResponse(Call<StatsResponse> call, Response<StatsResponse> response) {

                if(response.isSuccessful()){
                    StatsResponse statsResponse= response.body();
                    if(statsResponse!=null){
                        post(null, DetailEvent.STATS_TYPE,statsResponse,true);
                    }else{
                        post("stats is null", DetailEvent.STATS_TYPE,null,false);
                    }
                }else {
                    post("stats is not success", DetailEvent.STATS_TYPE,null,false);
                }
            }

            @Override
            public void onFailure(Call<StatsResponse> call, Throwable t) {
                Log.d(TAG,"Error:"+t.getMessage());
                post(t.getMessage(), DetailEvent.STATS_TYPE,null,false);
            }
        });
    }

    @Override
    public void getPlaybyPlay(String gameid) {
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        Call<PlaysResponse> call = statsService.getPbP(pbp_url+gameid+".js",ts);
        call.enqueue(new Callback<PlaysResponse>() {
            @Override
            public void onResponse(Call<PlaysResponse> call, Response<PlaysResponse> response) {
                if(response.isSuccessful()){
                    PlaysResponse aux = response.body();
                    if(aux!=null){
                        postPbP(null,DetailEvent.PBP_TYPE,aux,true);
                    }
                    else{
                        postPbP("response is null",DetailEvent.PBP_TYPE,null,false);
                    }
                }else{
                    postPbP("response had error",DetailEvent.PBP_TYPE,null,false);
                }
            }

            @Override
            public void onFailure(Call<PlaysResponse> call, Throwable t) {
                postPbP(t.getMessage(),DetailEvent.PBP_TYPE,null,false);
            }
        });
    }

    @Override
    public void getHomeCommonStats(String teamid) {
        Log.d(TAG,"commonhomestats:"+teamid);
        Call<CommonStats> call = statsService.getCommonStats(common_stats,teamid,"00","Regular Season","2016-17");
        call.enqueue(CommonCallBack(DetailEvent.COMMON_STATS_HOME));
    }

    private Callback<CommonStats> CommonCallBack(final int type){
        return new Callback<CommonStats>() {
            @Override
            public void onResponse(Call<CommonStats> call, Response<CommonStats> response) {
                if(response.isSuccessful()){
                    postCS(null,type,response.body(),true);
                }
                else {
                    postCS("not successfull",type,null,false);
                }
            }

            @Override
            public void onFailure(Call<CommonStats> call, Throwable t) {
                postCS(t.getMessage(),type,null,false);
            }
        };
    }

    private void postCS(String error, int type, CommonStats commonStats, boolean success) {
        DetailEvent event = new DetailEvent();
        event.setError(error);
        event.setType(type);
        event.setSuccess(success);
        event.setCommonStats(commonStats);

        if(error!=null){
            Log.d(TAG,"about to send event:"+type+" "+success+" error:"+error);
        }
        else{
            Log.d(TAG,"about to send event:"+type+" "+success);
        }

        eventBus.post(event);
    }


    @Override
    public void getAwayCommonStats(String teamid) {
        Log.d(TAG,"commonAwaystats:"+teamid);
        Call<CommonStats> call = statsService.getCommonStats(common_stats,teamid,"00","Regular Season","2016-17");
        call.enqueue(CommonCallBack(DetailEvent.COMMON_STATS_AWAY));
    }

    @Override
    public void getHomeStats(String teamid) {
        Call<CommonStats> call = statsService.getStats(stats,teamid,"","","","0","00","","Base","0","0","","N","PerGame",
                "0","N","N","2016-17","","Regular Season","","");

        call.enqueue(getTeamStatsCallback(DetailEvent.STATS_HOME));
    }

    @Override
    public void getAwayStats(String teamid) {
        Call<CommonStats> call = statsService.getStats(stats,teamid,"","","","0","00","","Base","0","0","","N","PerGame",
                                                        "0","N","N","2016-17","","Regular Season","","");

        call.enqueue(getTeamStatsCallback(DetailEvent.STATS_AWAY));

    }


    @Override
    public void getLastGamesAway(String teamid) {
        Call<CommonStats> call = statsService.getTeamGameLog(teamid,"00","Regular Season","2016-17","","");
        call.enqueue(new Callback<CommonStats>() {
            @Override
            public void onResponse(Call<CommonStats> call, Response<CommonStats> response) {
                if(response.isSuccessful()){
                    getLastGamesScores(response.body().getResultSets().get(0).getGameIds(),DetailEvent.GAMES_AWAY);
                }
                else{
                    postCS("unSuccessful",DetailEvent.GAMES_AWAY,null,false);
                }
            }

            @Override
            public void onFailure(Call<CommonStats> call, Throwable t) {
                postCS(t.getMessage(),DetailEvent.GAMES_AWAY,null,false);
            }
        });
    }

    @Override
    public void getInjuryReport(String cityHome, String cityAway) {
        Call<InjuryContainer> call = statsService.getInjuryReport(injury_report,cityHome,cityAway);
        call.enqueue(new Callback<InjuryContainer>() {
            @Override
            public void onResponse(Call<InjuryContainer> call, Response<InjuryContainer> response) {
                if(response.isSuccessful()){
                    if(response.body().isSuccess()){
                        postI(null,DetailEvent.INJURY_REPORT,true,response.body().getResponse());
                    }else{
                        postI("not success",DetailEvent.INJURY_REPORT,false,null);
                    }
                }
                else{
                    postI("not success",DetailEvent.INJURY_REPORT,false,null);
                }
            }

            @Override
            public void onFailure(Call<InjuryContainer> call, Throwable t) {
                Log.d(TAG,"injury Failed");
                postI(t.getMessage(),DetailEvent.INJURY_REPORT,false,null);
            }
        });
    }

    @Override
    public void getOdds() {
        Call<BetContainer> call = statsService.getOdds(oddUrl,"nba","3");
        call.enqueue(new Callback<BetContainer>() {
            @Override
            public void onResponse(Call<BetContainer> call, Response<BetContainer> response) {
                if(response.isSuccessful()){
                    if (response.body().getOdds()!=null){
                        if(response.body().getOdds().size()>0){
                            postOdds(null,DetailEvent.ODDS,true,response.body().getOdds());
                            return;
                        }else{
                            postOdds("cero odds",DetailEvent.ODDS,false,null);
                        }
                    }else{
                        postOdds("ErrorOdds",DetailEvent.ODDS,false,null);
                    }
                }else{
                    postOdds("Error unsuccessful",DetailEvent.ODDS,false,null);
                }

            }

            @Override
            public void onFailure(Call<BetContainer> call, Throwable t) {
                postOdds(t.getMessage(),DetailEvent.ODDS,false,null);
            }
        });
    }

    @Override
    public void getRecord(String gameid) {
        Call<Pregame> call = statsService.getRecords(pre_game+gameid+".js");

        call.enqueue(new Callback<Pregame>() {
            @Override
            public void onResponse(Call<Pregame> call, Response<Pregame> response) {
                if(response.isSuccessful()){
                    postRecord(null,DetailEvent.PRE_GAME,true,response.body());
                }
                else{
                    postRecord("unsuccessful",DetailEvent.PRE_GAME,false,null);
                }
            }

            @Override
            public void onFailure(Call<Pregame> call, Throwable t) {
                postRecord(t.getMessage(),DetailEvent.PRE_GAME,false,null);
            }
        });
    }

    private void postRecord(String error, int type, boolean success, Pregame pregame) {
        DetailEvent event =  new DetailEvent();
        event.setError(error);
        event.setType(type);
        event.setSuccess(success);
        event.setPregame(pregame);
        if(error!=null){
            Log.d(TAG,"about to send event:"+type+" "+success+" error:"+error);
        }
        else{
            Log.d(TAG,"about to send event:"+type+" "+success);
        }
        eventBus.post(event);
    }

    private void postOdds(String error, int type, boolean success, List<Odd> odds) {
        DetailEvent event =  new DetailEvent();
        event.setError(error);
        event.setType(type);
        event.setSuccess(success);
        event.setOdds(odds);
        if(error!=null){
            Log.d(TAG,"about to send event:"+type+" "+success+" error:"+error);
        }
        else{
            Log.d(TAG,"about to send event:"+type+" "+success);
        }
        eventBus.post(event);
    }

    private void postI(String error, int type, boolean success, Injury injuries) {
        DetailEvent event =  new DetailEvent();
        event.setError(error);
        event.setType(type);
        event.setSuccess(success);
        event.setInjuries(injuries);
        if(error!=null){
            Log.d(TAG,"about to send event:"+type+" "+success+" error:"+error);
        }
        else{
            Log.d(TAG,"about to send event:"+type+" "+success);
        }
        eventBus.post(event);
    }

    @Override
    public void getLastGamesHome(String teamid) {
        Call<CommonStats> call = statsService.getTeamGameLog(teamid,"00","Regular Season","2016-17","","");
        call.enqueue(new Callback<CommonStats>() {
            @Override
            public void onResponse(Call<CommonStats> call, Response<CommonStats> response) {
                if(response.isSuccessful()){
                    getLastGamesScores(response.body().getResultSets().get(0).getGameIds(),DetailEvent.GAMES_HOME);
                }
                else{
                    postCS("unSuccessful",DetailEvent.GAMES_HOME,null,false);
                }
            }

            @Override
            public void onFailure(Call<CommonStats> call, Throwable t) {
                postCS(t.getMessage(),DetailEvent.GAMES_HOME,null,false);
            }
        });
    }

    private void getLastGamesScores(List<String> gameIDs,int type) {
        if(gameIDs.size()>5)gameIDs = gameIDs.subList(0,5);

        for(String gameid : gameIDs){
            Call<CommonStats> call = statsService.getPastGamesScores(gameid);
            call.enqueue(GameScoresCallback(type,gameIDs.size()));
        }
    }

    private Callback<CommonStats> GameScoresCallback(final int type,final int size) {
        return new Callback<CommonStats>() {
            @Override
            public void onResponse(Call<CommonStats> call, Response<CommonStats> response) {
                if (response.isSuccessful()){
                    postGS(null,type,response.body(),true,size);
                }else{
                    postCS("unSuccessful",type,null,false);
                }
            }

            @Override
            public void onFailure(Call<CommonStats> call, Throwable t) {
                postCS(t.getMessage(),type,null,false);
            }
        };
    }

    private void postGS(String error, int type, CommonStats commonStats, boolean success, int size) {
        DetailEvent event = new DetailEvent();
        event.setError(error);
        event.setType(type);
        event.setCommonStats(commonStats);
        event.setSuccess(success);
        event.setSize(size);

        Log.d(TAG,"about to send event:"+type+" "+success);

        eventBus.post(event);
    }

    private void postPbP(String message, int statsType, PlaysResponse playsResponse, boolean success) {
        DetailEvent event = new DetailEvent();
        event.setError(message);
        event.setType(statsType);
        event.setSuccess(success);
        event.setPlaysResponse(playsResponse);

        Log.d(TAG,"about to send event:"+statsType+" "+success);

        eventBus.post(event);

    }

    private void post(String message, int statsType, StatsResponse statsResponse, boolean success) {
        DetailEvent event = new DetailEvent();
        event.setError(message);
        event.setType(statsType);
        event.setSuccess(success);
        event.setStatsResponse(statsResponse);

        if(message!=null){
            Log.d(TAG,"about to send event:"+statsType+" "+success+" error:"+message);
        }
        else{
            Log.d(TAG,"about to send event:"+statsType+" "+success);
        }
        eventBus.post(event);

    }

    public Callback<CommonStats> getTeamStatsCallback(final int type) {
        return new Callback<CommonStats>() {
            @Override
            public void onResponse(Call<CommonStats> call, Response<CommonStats> response) {
                if(response.isSuccessful()){
                    postTStats(null,type,response.body(),true);
                }
                else{
                    postTStats("response is null",type,null,false);
                }
            }

            @Override
            public void onFailure(Call<CommonStats> call, Throwable t) {
                postTStats(t.getMessage(),type,null,false);
            }
        };
    }

    private void postTStats(String error, int type, CommonStats commonStats, boolean success) {
        DetailEvent event = new DetailEvent();
        event.setError(error);
        event.setType(type);
        event.setSuccess(success);
        event.setCommonStats(commonStats);

        if(error!=null){
            Log.d(TAG,"about to send event:"+type+" "+success+" error:"+error);
        }
        else{
            Log.d(TAG,"about to send event:"+type+" "+success);
        }

        eventBus.post(event);
    }
}
