package fpr9.com.nbalivefeed.gamesindex.di;

import javax.inject.Singleton;

import dagger.Component;
import fpr9.com.nbalivefeed.gamesindex.IndexPresenter;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.GamesAdapter;
import fpr9.com.nbalivefeed.lib.di.LibsModule;

/**
 * Created by FranciscoPR on 29/10/16.
 */
@Singleton
@Component(modules = {LibsModule.class,IndexModule.class})
public interface IndexComponent {

    IndexPresenter getIndexPresenter();
    GamesAdapter getGamesAdapter();
}
