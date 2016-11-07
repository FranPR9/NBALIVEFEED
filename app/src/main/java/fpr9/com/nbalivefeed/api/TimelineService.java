package fpr9.com.nbalivefeed.api;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;


/**
 * Created by ykro.
 */
public interface TimelineService {
    @GET("https://api.twitter.com/1.1/search/tweets.json")
    void querySearch(@Query("q") String query,
                      Callback<List<Tweet>> callback);
}
