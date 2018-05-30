package fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.FixedTweetTimeline;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import fpr9.com.nbalivefeed.NBAlivefeedApp;
import fpr9.com.nbalivefeed.R;
import fpr9.com.nbalivefeed.gamedetails.GameDetailActivity;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.TwitterMediaPresenter;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.di.TweetComponent;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.adapter.TweetAdapter;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class TwitterMediaFragment extends Fragment implements TwitterMediaView {

    TweetAdapter adapter;
    ImageLoader imageLoader;
    TwitterMediaPresenter presenter;
    @BindView(R.id.tweets_recycler)
    RecyclerView tweetsRecycler;
    @BindView(R.id.tweets_progressBar)
    ProgressBar tweetsProgressBar;
    @BindView(R.id.tweetList)
    ListView tweetList;
    private Unbinder unbinder;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setInyection();
        presenter.onCreate();
    }

    private void setInyection() {
        TweetComponent component = ((NBAlivefeedApp) getActivity().getApplication()).getTweetComponent((GameDetailActivity) getActivity(), this);
        adapter = component.getTweetAdapter();
        imageLoader = component.getImageLoader();
        presenter = component.getTwitterMediaPresenter();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_twitter_feed, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        presenter.getTweets(query);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tweetsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        tweetsRecycler.setAdapter(adapter);
    }

    @Override
    public void showTweets(List<Tweet> tweets) {

        //adapter.setTweets(tweets);
        final FixedTweetTimeline timeline = new FixedTweetTimeline.Builder()
                .setTweets(tweets)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(getActivity())
                .setTimeline(timeline)
                .build();
        tweetList.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        tweetsProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        tweetsProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showTweetsError() {

    }

    private String query;

    @Override
    public void setAcronymsAndNames(String homea, String awaya, String homeName, String awayName, String homeC, String awayC) {
        query = "\"" + homea + "\" OR " + "\"" + awaya + "\" OR " + "\"" + homeName + "\" OR " + "\"" + awayName + "\" OR "
                + " \"@" + homeName + "\" OR " + "\"@" + awayName + "\" OR "
                + " \"" + homeC + "\" OR " + "\"" + awayC + "\" OR "
                + "\"@" + homeC + homeName + "\" OR \"@" + awayC + awayName + "\"";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDestroy();
        unbinder.unbind();
    }
}
