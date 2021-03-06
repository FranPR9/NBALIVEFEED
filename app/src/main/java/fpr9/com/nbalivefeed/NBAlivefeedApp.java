package fpr9.com.nbalivefeed;

import android.app.Application;
import android.util.Log;


import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import fpr9.com.nbalivefeed.gamedetails.GameDetailActivity;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.di.DaggerDetailComponent;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.di.DetailComponent;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.di.DetailModule;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.DetailFeedView;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.di.DaggerTweetComponent;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.di.TweetComponent;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.di.TweetModule;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.TwitterMediaView;
import fpr9.com.nbalivefeed.gamesindex.di.DaggerIndexComponent;
import fpr9.com.nbalivefeed.gamesindex.di.IndexComponent;
import fpr9.com.nbalivefeed.gamesindex.di.IndexModule;
import fpr9.com.nbalivefeed.gamesindex.ui.GamesIndex;
import fpr9.com.nbalivefeed.gamesindex.ui.IndexView;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.OnGameListener;
import fpr9.com.nbalivefeed.lib.di.LibsModule;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public class NBAlivefeedApp extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = BuildConfig.TWITTER_KEY;
    private static final String TWITTER_SECRET = BuildConfig.TWITTER_SECRET;


    @Override
    public void onCreate() {
        super.onCreate();
        //TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET))
                .debug(true)
                .build();
        Twitter.initialize(config);
        //Fabric.with(this, new Twitter(authConfig));
        DBinit();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBFTearDown();
    }

    private void DBinit(){
        //FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void DBFTearDown(){
        //FlowManager.destroy();
    }

    public IndexComponent getIndexComponent(GamesIndex activity, IndexView view, OnGameListener listener){

        return DaggerIndexComponent.builder()
                    .libsModule(new LibsModule(activity))
                    .indexModule(new IndexModule(view,listener))
                    .build();
    }

    public DetailComponent getDetailComponent(GameDetailActivity activity, DetailFeedView view){
        return DaggerDetailComponent.builder()
                .libsModule(new LibsModule(activity))
                .detailModule(new DetailModule(view))
                .build();

    }

    public TweetComponent getTweetComponent(GameDetailActivity activity, TwitterMediaView view){
        return DaggerTweetComponent.builder()
                .libsModule(new LibsModule(activity))
                .tweetModule(new TweetModule(view))
                .build();
    }

}
