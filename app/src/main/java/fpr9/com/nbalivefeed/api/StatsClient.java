package fpr9.com.nbalivefeed.api;

import android.util.Log;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import fpr9.com.nbalivefeed.entities.ResponseInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class StatsClient {

    private Retrofit retrofit;
    //private final static String BASE_URL = "https://neulionmdnyc-a.akamaihd.net/fs/nba/feeds_s2012/stats/2016/boxscore/";

    public StatsClient() {
        Calendar c = Calendar.getInstance();
        String BASE_URL = "https://neulionmdnyc-a.akamaihd.net/fs/nba/feeds_s2012/stats/"+c.get(Calendar.YEAR)+"/boxscore/";
        Log.d("StatsClient",BASE_URL);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        //OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …

// add logging as last interceptor
        //httpClient.addInterceptor(logging);  // <-- this is the important line!

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(new ResponseInterceptor())
                .addInterceptor(logging)
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public StatsService getStatsService() {
        return retrofit.create(StatsService.class);
    }

}
