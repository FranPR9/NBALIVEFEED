package fpr9.com.nbalivefeed.api;


import fpr9.com.nbalivefeed.entities.GameScore;
import fpr9.com.nbalivefeed.entities.GameScoreRespond;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by FranciscoPR on 17/08/16.
 */
public interface FeedService {

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

    @GET("00_todays_scores.json")
    Call<GameScoreRespond> getScores();

}
