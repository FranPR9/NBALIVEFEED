package fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fpr9.com.nbalivefeed.R;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 31/10/16.
 */
public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {

    private static final String TAG = "TweetAdapter";
    private ImageLoader imageLoader;
    private List<Tweet> tweets;
    private Context context;

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
        notifyDataSetChanged();
    }

    public TweetAdapter(ImageLoader imageLoader, List<Tweet> tweets) {
        this.imageLoader = imageLoader;
        this.tweets = tweets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        context = parent.getContext();
        return vh;
    }

    private  int count=0;
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Tweet aux = tweets.get(position);
        //Log.d(TAG,"new tweet view");

            //holder.tweetContainer.addView(new TweetView(context, aux));
            holder.tweetView.setTweet(aux);


        /*
        holder.tweetText.setText(aux.text);
        if (checkIfTweetHasImage(aux)) {
            holder.tweetImage.setVisibility(View.VISIBLE);
            imageLoader.load(holder.tweetImage, aux.entities.media.get(0).mediaUrl);
        }
        if (checkIfTweetHasExtendedMedia(aux)) {
            holder.tweetImage.setVisibility(View.VISIBLE);
            if (aux.extendedEtities.media.get(0).videoInfo.variants != null)
                Log.d(TAG, "videoUrl:" + aux.extendedEtities.media.get(0).videoInfo.variants.get(0).url);
            //imageLoader.load(holder.tweetImage,aux.extendedEtities.media.get(0).videoInfo.variants.get(0).url);
        }
        */
    }

    private boolean checkIfTweetHasImage(Tweet tweet) {
        return tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private boolean checkIfTweetHasExtendedMedia(Tweet tweet) {
        /*return tweet.extendedEtities != null &&
                tweet.extendedEtities.media != null &&
                !tweet.extendedEtities.media.isEmpty();*/
        return false;
    }

    @Override
    public void onViewDetachedFromWindow(ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        //context = null;
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        /*
        @Bind(R.id.tweet_image)
        ImageView tweetImage;
        @Bind(R.id.tweet_text)
        TextView tweetText;
        */

        @BindView(R.id.tweetView)
        TweetView tweetView;
        @BindView(R.id.tweetContainer)
        LinearLayout tweetContainer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
