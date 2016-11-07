package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.di;


import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import fpr9.com.nbalivefeed.api.StatsClient;
import fpr9.com.nbalivefeed.api.StatsService;
import fpr9.com.nbalivefeed.entities.Game;
import fpr9.com.nbalivefeed.entities.Play;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailInteractor;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailInteractorImpl;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailPresenter;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailPresenterImpl;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailRepository;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailRepositoryImpl;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.DetailFeedView;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.adapter.PlaysAdapter;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.GamesAdapter;
import fpr9.com.nbalivefeed.lib.EventBus;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 29/10/16.
 */
@Module
public class DetailModule {

    DetailFeedView view;

    public DetailModule(DetailFeedView view) {
        this.view = view;
    }

    @Singleton
    @Provides
    DetailPresenter provideDetailPresenter(EventBus eventBus, DetailInteractor interactor){
        return  new DetailPresenterImpl(eventBus,view,interactor);
    }

    @Singleton
    @Provides
    DetailInteractor provideDetailInteractor(DetailRepository repository){
        return new DetailInteractorImpl(repository);
    }

    @Singleton
    @Provides
    DetailRepository provideDetailRepository(EventBus eventBus,StatsService service){
        return new DetailRepositoryImpl(eventBus,service);
    }

    @Singleton
    @Provides
    StatsService providesStatsService(){
        StatsClient client = new StatsClient();
        return client.getStatsService();
    }

    @Singleton
    @Provides
    PlaysAdapter providesPlaysAdapter(ImageLoader imageLoader, List<Play> plays){
        return new PlaysAdapter(imageLoader, plays);
    }

    @Singleton
    @Provides
    List<Play> providePlaysList(){
        return new ArrayList<Play>();
    }

}
