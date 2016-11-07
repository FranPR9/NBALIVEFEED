package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpr9.com.nbalivefeed.R;
import fpr9.com.nbalivefeed.entities.Play;
import fpr9.com.nbalivefeed.lib.ImageLoader;

/**
 * Created by FranciscoPR on 30/10/16.
 */
public class PlaysAdapter extends RecyclerView.Adapter<PlaysAdapter.ViewHolder> {

    ImageLoader imageLoader;
    List<Play> plays;


    public PlaysAdapter(ImageLoader imageLoader, List<Play> plays) {
        this.imageLoader = imageLoader;
        this.plays = plays;
    }

    public void setPlays(List<Play> plays) {
        this.plays = plays;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.play_row, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Play aux = plays.get(position);

        holder.playDescription.setText(aux.getDescription());
        holder.playClock.setText(aux.getClock());

    }


    @Override
    public int getItemCount() {
        return plays.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.play_description)
        TextView playDescription;
        @Bind(R.id.play_clock)
        TextView playClock;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
