package fpr9.com.nbalivefeed.gamesindex.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpr9.com.nbalivefeed.R;
import fpr9.com.nbalivefeed.entities.Game;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.ViewHolder> {

    private static final String TAG = "GamesAdapter";
    ImageLoader imageLoader;
    List<Game> games;
    OnGameListener listener;

    private int size = 0;

    public GamesAdapter(ImageLoader imageLoader, List<Game> games, OnGameListener listener) {
        this.imageLoader = imageLoader;
        this.games = games;
        this.listener = listener;
        if (games == null) size = 0;
        else size = games.size();
    }

    public void setGames(List<Game> games) {
        this.games = games;
        if (games != null)
            size = games.size();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (games != null) {

            Game aux = games.get(position);

            holder.nameAway.setText(aux.getVisitor().getName());
            holder.cityAway.setText(aux.getVisitor().getCity());
            holder.scoreAway.setText(aux.getVisitor().getScore() + "");
            imageLoader.load(holder.awayImg, aux.getVisitor().getImg());

            if (aux.getPeriod() <= 0) {
                //holder.gameInfoContainer.setVisibility(View.GONE);
                holder.timeLeft.setText("@");
                if(aux.getLocal().getDate()!=null){
                    String date = aux.getLocal().getDate().replace("T","");
                    date = date.substring(0,10);
                    holder.period.setText(date);
                }else {
                    holder.period.setVisibility(View.GONE);
                }
                holder.status.setText(aux.getStatus());

            }else{
                holder.period.setText(aux.getPeriod() + " Qtr");
                holder.timeLeft.setText(aux.getTime_left());
                holder.status.setText(aux.getStatus());
                holder.period.setVisibility(View.VISIBLE);
                holder.status.setVisibility(View.VISIBLE);
            }



            holder.nameHome.setText(aux.getLocal().getName());
            holder.cityHome.setText(aux.getLocal().getCity());
            holder.scoreHome.setText(aux.getLocal().getScore() + "");
            imageLoader.load(holder.homeImg, aux.getLocal().getImg());

            if (listener != null)
                holder.setOnGameClick(aux, listener);
        }


    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.away_img)
        ImageView awayImg;
        @Bind(R.id.name_away)
        TextView nameAway;
        @Bind(R.id.city_away)
        TextView cityAway;
        @Bind(R.id.score_away)
        TextView scoreAway;
        @Bind(R.id.period)
        TextView period;
        @Bind(R.id.time_left)
        TextView timeLeft;
        @Bind(R.id.status)
        TextView status;
        @Bind(R.id.home_img)
        ImageView homeImg;
        @Bind(R.id.name_home)
        TextView nameHome;
        @Bind(R.id.city_home)
        TextView cityHome;
        @Bind(R.id.score_home)
        TextView scoreHome;
        @Bind(R.id.gameInfoContainer)
        LinearLayout gameInfoContainer;

        View view;

        public void setOnGameClick(final Game game, final OnGameListener listener) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnGameClick(game);
                }
            });
        }

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
