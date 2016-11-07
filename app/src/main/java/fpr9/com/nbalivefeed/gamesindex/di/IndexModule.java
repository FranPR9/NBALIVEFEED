package fpr9.com.nbalivefeed.gamesindex.di;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fpr9.com.nbalivefeed.api.FeedClient;
import fpr9.com.nbalivefeed.api.FeedService;
import fpr9.com.nbalivefeed.entities.Game;
import fpr9.com.nbalivefeed.gamesindex.IndexInteractor;
import fpr9.com.nbalivefeed.gamesindex.IndexInteractorImpl;
import fpr9.com.nbalivefeed.gamesindex.IndexPresenter;
import fpr9.com.nbalivefeed.gamesindex.IndexPresenterImpl;
import fpr9.com.nbalivefeed.gamesindex.IndexRepository;
import fpr9.com.nbalivefeed.gamesindex.IndexRepositoryImpl;
import fpr9.com.nbalivefeed.gamesindex.ui.IndexView;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.GamesAdapter;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.OnGameListener;
import fpr9.com.nbalivefeed.lib.EventBus;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 29/10/16.
 */
@Module
public class IndexModule {

    IndexView view;
    OnGameListener listener;

    public IndexModule(IndexView view, OnGameListener listener) {
        this.view = view;
        this.listener = listener;
    }

    @Singleton
    @Provides
    IndexPresenter providesIndexPresenter(EventBus eventBus, IndexInteractor indexInteractor){
        return  new IndexPresenterImpl(view,eventBus,indexInteractor);
    }

    @Singleton
    @Provides
    IndexInteractor providesIndexInteractor(IndexRepository repository){
        return new IndexInteractorImpl(repository);
    }

    @Singleton
    @Provides
    IndexRepository providesIndexRepository(EventBus eventBus, FeedService feedService){
        return new IndexRepositoryImpl(eventBus,feedService);
    }

    @Singleton
    @Provides
    FeedService providesFeedService(){
        FeedService feedService = new FeedClient().getFeedService();
        return feedService;
    }

    @Singleton
    @Provides
    GamesAdapter providesGamesAdapter(ImageLoader imageLoader, List<Game> games){
      return new GamesAdapter(imageLoader,games,listener);
    }

    @Singleton
    @Provides
    List<Game> providesGameList(){
        return new ArrayList<Game>();
    }





}
