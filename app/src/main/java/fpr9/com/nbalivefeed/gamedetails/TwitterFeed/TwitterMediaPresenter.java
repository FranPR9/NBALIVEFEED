package fpr9.com.nbalivefeed.gamedetails.TwitterFeed;

import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.event.TweetEvent;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public interface TwitterMediaPresenter {

    void onCreate();
    void onDestroy();

    void getTweets(String query);

    void tweetsResponse(TweetEvent event);

}
