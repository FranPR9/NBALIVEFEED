package fpr9.com.nbalivefeed.gamedetails.TwitterFeed.di;


import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fpr9.com.nbalivefeed.api.CustomTwitterApiClient;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaInteractor;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaInteractorImpl;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaPresenter;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaPresenterImpl;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaRepository;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaRepositoryImpl;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.TwitterMediaView;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.adapter.TweetAdapter;
import fpr9.com.nbalivefeed.lib.EventBus;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 29/10/16.
 */
@Module
public class TweetModule {

    TwitterMediaView view;

    public TweetModule(TwitterMediaView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    TwitterMediaPresenter providePresenter(EventBus eventBus, TwitterMediaInteractor interactor)
    {
        return new TwitterMediaPresenterImpl(eventBus,view,interactor);
    }

    @Singleton
    @Provides
    TwitterMediaInteractor providesTwitterMediaInteractor(TwitterMediaRepository repository){
        return new TwitterMediaInteractorImpl(repository);
    }

    @Singleton
    @Provides
    TwitterMediaRepository providesTwitterMediaRepository(EventBus eventBus){//}, CustomTwitterApiClient apiClient){
        return new TwitterMediaRepositoryImpl(eventBus);//,apiClient);
    }

    /*
    @Singleton
    @Provides
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession provideTwitterSession() {
        return Twitter. getSessionManager().getActiveSession();
    }
    */

    @Provides
    @Singleton
    TweetAdapter providesTweetAdapter(ImageLoader imageLoader, List<Tweet> tweets){
        return new TweetAdapter(imageLoader,tweets);
    }

    @Provides
    @Singleton
    List<Tweet> providesTweets(){
        return new ArrayList<Tweet>();
    }


}
