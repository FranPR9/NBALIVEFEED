package fpr9.com.nbalivefeed.gamedetails.TwitterFeed.event;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by FranciscoPR on 31/10/16.
 */
public class TweetEvent {

    private int type;
    private String error;
    private boolean success;

    private List<Tweet> tweets;

    public static final int TWEET_SEARCH_TYPE = 1;

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
