package fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui;

import com.twitter.sdk.android.core.models.Tweet;

import java.util.List;

/**
 * Created by FranciscoPR on 31/10/16.
 */
public interface TwitterMediaView {

    void showTweets(List<Tweet> tweets);
    void showProgress();
    void hideProgress();

    void showTweetsError();

    void setAcronymsAndNames(String homea, String awaya, String homeName, String awayName,String homeC, String awayC);
}
