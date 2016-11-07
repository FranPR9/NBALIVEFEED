package fpr9.com.nbalivefeed.gamesindex.ui;

import fpr9.com.nbalivefeed.entities.GameScore;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public interface IndexView {

    void showProgress();
    void hideProgress();

    void showGames(GameScore gameScore);
    void gamesRetriveError();
    void localGamesError();
}
