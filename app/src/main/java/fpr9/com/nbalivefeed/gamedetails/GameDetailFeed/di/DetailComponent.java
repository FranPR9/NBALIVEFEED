package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.di;

import javax.inject.Singleton;

import dagger.Component;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.DetailPresenter;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.adapter.PlaysAdapter;
import fpr9.com.nbalivefeed.gamesindex.IndexPresenter;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.GamesAdapter;
import fpr9.com.nbalivefeed.lib.ImageLoader;
import fpr9.com.nbalivefeed.lib.di.LibsModule;

/**
 * Created by FranciscoPR on 29/10/16.
 */
@Singleton
@Component(modules = {LibsModule.class,DetailModule.class})
public interface DetailComponent {

    DetailPresenter getIndexPresenter();
    ImageLoader getImageLoader();
    PlaysAdapter getPlaysAdapter();
}
