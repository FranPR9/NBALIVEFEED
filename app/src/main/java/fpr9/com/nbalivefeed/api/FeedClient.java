package fpr9.com.nbalivefeed.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FranciscoPR on 17/08/16.
 */
public class FeedClient {

    private Retrofit retrofit;
    private final static String BASE_URL = "http://data.nba.com/data/v2015/json/mobile_teams/nba/2016/scores/";
    //private final static String BASE_URL = "http://192.168.0.3:8008/api/";

    public FeedClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public FeedService getFeedService() {
        return retrofit.create(FeedService.class);
    }

}