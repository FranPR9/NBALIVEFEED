package fpr9.com.nbalivefeed.gamedetails.TwitterFeed;

import android.util.Log;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Search;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;

import java.util.Calendar;
import java.util.List;

import fpr9.com.nbalivefeed.api.CustomTwitterApiClient;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.event.TweetEvent;
import fpr9.com.nbalivefeed.lib.EventBus;
import retrofit2.Call;

/**
 * Created by FranciscoPR on 31/10/16.
 */
public class TwitterMediaRepositoryImpl implements TwitterMediaRepository {

    private static final String TAG = "TwitterMediaRepository";
    EventBus eventBus;
    //CustomTwitterApiClient client;

    public TwitterMediaRepositoryImpl(EventBus eventBus){//, CustomTwitterApiClient customTwitterApiClient) {
        this.eventBus = eventBus;
        //this.client = customTwitterApiClient;

    }

    @Override
    public void getNBATweets(String query) {
        //String query="from:@NBA since:2016-10-30 \"Russell\" OR \"Durant\" ";
        String date = getDate();
        query="from:@NBA since:"+date+" "+query+" -RT";

        Log.d(TAG,query);
        Twitter.getApiClient().getSearchService().tweets(query,null,null,null,null,null,null,null,null,null).enqueue(new Callback<Search>() {
            @Override
            public void success(Result<Search> result) {
                if(result.response.isSuccessful()){
                    if(result.data.tweets.size()>0){
                        List<Tweet> tweets = removeReps(result.data.tweets);
                        post(null, TweetEvent.TWEET_SEARCH_TYPE, true, tweets);
                    }else{
                        post("empty list",TweetEvent.TWEET_SEARCH_TYPE,false,null);
                    }
                }else {
                    post("null list",TweetEvent.TWEET_SEARCH_TYPE,false,null);
                }

            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getMessage(),TweetEvent.TWEET_SEARCH_TYPE,false,null);
            }
        });

        /*
        client.getTimelineService().querySearch(query, new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                if(result!=null){
                    if(result.data.size()>0){

                    }
                    else{
                        post("empty list",TweetEvent.TWEET_SEARCH_TYPE,false,null);
                    }
                }else {
                    post("null list",TweetEvent.TWEET_SEARCH_TYPE,false,null);
                }
            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getMessage(),TweetEvent.TWEET_SEARCH_TYPE,false,null);
            }
        });*/
    }

    private List<Tweet> removeReps(List<Tweet> tweets) {
        List<Tweet> auxTweets = tweets;
        for(int i =0; i<tweets.size();i++){
            Tweet tweet = tweets.get(i);
            for (int j=i+1;j<tweets.size();j++){
                if(tweet.getId()==tweets.get(j).getId()){
                    tweets.remove(j);
                    j--;

                }
            }
        }

        return tweets;
    }

    private void post(String error, int tweetSearchType, boolean success, List<Tweet> tweetList) {
        TweetEvent event = new TweetEvent();
        event.setError(error);
        event.setType(tweetSearchType);
        event.setSuccess(success);
        event.setTweets(tweetList);

        eventBus.post(event);
    }

    public String getDate() {
        Calendar cal = Calendar.getInstance();

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        String date = year+"-"+month+"-"+day;
        return date;
    }
}
