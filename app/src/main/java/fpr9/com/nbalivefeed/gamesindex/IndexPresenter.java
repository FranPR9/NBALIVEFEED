package fpr9.com.nbalivefeed.gamesindex;

import fpr9.com.nbalivefeed.gamesindex.event.IndexEvent;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public interface IndexPresenter {

    void getGames();
    void getSchedule();
    void getScores();

    void scoresRespond(IndexEvent event);

    void onCreate();
    void onDestroy();

}
