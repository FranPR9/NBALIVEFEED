package fpr9.com.nbalivefeed.gamedetails.TwitterFeed;

import org.greenrobot.eventbus.Subscribe;

import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.event.TweetEvent;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.TwitterMediaView;
import fpr9.com.nbalivefeed.lib.EventBus;

/**
 * Created by FranciscoPR on 31/10/16.
 */
public class TwitterMediaPresenterImpl implements TwitterMediaPresenter {

    EventBus eventBus;
    TwitterMediaView view;
    TwitterMediaInteractor interactor;

    public TwitterMediaPresenterImpl(EventBus eventBus, TwitterMediaView view, TwitterMediaInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
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

    @Override
    public void getTweets(String query) {
        view.showProgress();
        interactor.executeTwitterMedia(query);
    }

    @Override
    @Subscribe
    public void tweetsResponse(TweetEvent event) {
        view.hideProgress();
        if(event.isSuccess()){
            switch (event.getType()){
                case TweetEvent.TWEET_SEARCH_TYPE:
                    view.showTweets(event.getTweets());
                    break;
            }
        }
    }
}
