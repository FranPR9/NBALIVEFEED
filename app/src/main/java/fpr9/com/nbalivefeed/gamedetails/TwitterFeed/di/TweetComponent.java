package fpr9.com.nbalivefeed.gamedetails.TwitterFeed.di;

import javax.inject.Singleton;

import dagger.Component;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailPresenter;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.adapter.PlaysAdapter;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaPresenter;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.adapter.TweetAdapter;
import fpr9.com.nbalivefeed.lib.ImageLoader;
import fpr9.com.nbalivefeed.lib.di.LibsModule;

/**
 * Created by FranciscoPR on 29/10/16.
 */
@Singleton
@Component(modules = {LibsModule.class,TweetModule.class})
public interface TweetComponent {

    TwitterMediaPresenter getTwitterMediaPresenter();
    ImageLoader getImageLoader();
    TweetAdapter getTweetAdapter();

}
