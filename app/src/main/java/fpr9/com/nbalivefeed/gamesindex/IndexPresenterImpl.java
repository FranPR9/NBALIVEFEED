package fpr9.com.nbalivefeed.gamesindex;

import org.greenrobot.eventbus.Subscribe;

import fpr9.com.nbalivefeed.gamesindex.event.IndexEvent;
import fpr9.com.nbalivefeed.gamesindex.ui.IndexView;
import fpr9.com.nbalivefeed.lib.EventBus;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public class IndexPresenterImpl implements IndexPresenter{

    private IndexView view;
    private EventBus eventBus;
    private IndexInteractor interactor;

    public IndexPresenterImpl(IndexView view, EventBus eventBus, IndexInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void getGames() {

    }

    @Override
    public void getSchedule() {

    }

    @Override
    public void getScores() {
        interactor.executeScores();
    }

    @Override
    @Subscribe
    public void scoresRespond(IndexEvent event) {
        view.hideProgress();
        if(event.isSuccess()){
            switch (event.getType()){
                case IndexEvent.SCORE_TYPE:
                    view.showGames(event.getGameScore());
                    break;
            }
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }
}
