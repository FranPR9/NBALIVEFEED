package fpr9.com.nbalivefeed.api;


import fpr9.com.nbalivefeed.entities.BetContainer;
import fpr9.com.nbalivefeed.entities.CommonStats;
import fpr9.com.nbalivefeed.entities.InjuryContainer;
import fpr9.com.nbalivefeed.entities.PlaysResponse;
import fpr9.com.nbalivefeed.entities.Pregame;
import fpr9.com.nbalivefeed.entities.RecordContainer;
import fpr9.com.nbalivefeed.entities.StatsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by FranciscoPR on 17/08/16.
 */
public interface StatsService {

    /*
    @POST("login")
    Call<Auth> login(@Field("email") String email, @Field("password") String password);

    @POST("register")
    Call<Auth> register(@Field("name") String name,
                        @Field("mail") String email,
                        @Field("password") String password,
                        @Field("img") String img,
                        @Field("fb") String oauth);
                        */

    @GET
    Call<StatsResponse> getScores(@Url String url, @Query("t") String timestamp);

    @GET
    Call<PlaysResponse> getPbP(@Url String url, @Query("t") String timestamp);

    @GET
    Call<CommonStats> getCommonStats(@Url String url, @Query("TeamID") String teamid
                                                    , @Query("LeagueID") String leagueid
                                                    , @Query("SeasonType") String seasonType
                                                    , @Query("season") String season
    );

    @GET
    Call<CommonStats> getStats(@Url String url, @Query("TeamID") String teamid
                                , @Query("DateFrom") String datefrom
                                , @Query("DateTo") String dateto
                                , @Query("GameSegment") String gamesegment
                                , @Query("LastNGames") String lastngame
                                , @Query("LeagueID") String leagueid
                                , @Query("Location") String location
                                , @Query("MeasureType") String mesuretype
                                , @Query("Month") String month
                                , @Query("OpponentTeamID") String opponentteamid
                                , @Query("Outcome") String outcome
                                , @Query("PaceAdjust") String paceadjust
                                , @Query("PerMode") String permode
                                , @Query("Period") String period
                                , @Query("PlusMinus") String plusminus
                                , @Query("Rank") String rank
                                , @Query("Season") String season
                                , @Query("SeasonSegment") String seasonsegment
                                , @Query("SeasonType") String seasonType
                                , @Query("VsConference") String vsconference
                                , @Query("VsDivision") String vsdivision
                                );

    @GET("http://stats.nba.com/stats/teamgamelog")
    Call<CommonStats> getTeamGameLog( @Query("TeamID") String teamid
                                    , @Query("LeagueID") String leagueid
                                    , @Query("SeasonType") String seasonType
                                    , @Query("Season") String season
                                    , @Query("DateFrom") String dateFrom
                                    , @Query("DateTo") String dateTo);

    @GET("http://stats.nba.com/stats/boxscoresummaryv2")
    Call<CommonStats> getPastGamesScores(@Query("GameID") String gameid);

    @GET
    Call<InjuryContainer> getInjuryReport(@Url String url, @Query("home") String home, @Query("away") String away);

    @GET
    Call<BetContainer>getOdds(@Url String url, @Query("league") String league, @Query("auth") String auth);

    @GET
    Call<Pregame>getRecords(@Url String url);
}
