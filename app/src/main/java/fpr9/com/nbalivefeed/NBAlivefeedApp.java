package fpr9.com.nbalivefeed;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
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
import io.fabric.sdk.android.Fabric;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public class NBAlivefeedApp extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "SrWIeCbiqBAHSdPxbfVVAod7z";
    private static final String TWITTER_SECRET = "003SoYlZrDKwIwY7najuuIsoeJWPdOgvq6YzhIr0atvihMqyZM";


    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        DBinit();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBFTearDown();
    }

    private void DBinit(){
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    private void DBFTearDown(){
        FlowManager.destroy();
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
