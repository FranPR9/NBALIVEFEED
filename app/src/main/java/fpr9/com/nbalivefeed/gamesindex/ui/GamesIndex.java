package fpr9.com.nbalivefeed.gamesindex.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpr9.com.nbalivefeed.NBAlivefeedApp;
import fpr9.com.nbalivefeed.R;
import fpr9.com.nbalivefeed.entities.Game;
import fpr9.com.nbalivefeed.entities.GameScore;
import fpr9.com.nbalivefeed.gamedetails.GameDetailActivity;
import fpr9.com.nbalivefeed.gamesindex.IndexPresenter;
import fpr9.com.nbalivefeed.gamesindex.di.IndexComponent;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.GamesAdapter;
import fpr9.com.nbalivefeed.gamesindex.ui.adapter.OnGameListener;

public class GamesIndex extends AppCompatActivity implements IndexView, OnGameListener, SwipeRefreshLayout.OnRefreshListener {

    private static final String TAG = "GamesIndex";
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.gamesDate)
    TextView gamesDate;
    @Bind(R.id.games_recycler)
    RecyclerView gamesRecycler;
    @Bind(R.id.indexCoordinator)
    CoordinatorLayout indexCoordinator;
    @Bind(R.id.indexProgressBar)
    ProgressBar indexProgressBar;

    IndexPresenter presenter;
    GamesAdapter adapter;
    @Bind(R.id.swipeContainerIndex)
    SwipeRefreshLayout swipeContainerIndex;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_index);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        setInjection();
        swipeContainerIndex.setOnRefreshListener(this);
        presenter.onCreate();
        presenter.getScores();
        setRecycler();
    }

    private void setInjection() {
        IndexComponent component = ((NBAlivefeedApp) getApplication()).getIndexComponent(this, this, this);
        presenter = component.getIndexPresenter();
        adapter = component.getGamesAdapter();
    }

    private void setRecycler() {
        gamesRecycler.setLayoutManager(new LinearLayoutManager(this));
        gamesRecycler.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_games_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        indexProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        indexProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showGames(GameScore gameScore) {
        swipeContainerIndex.setRefreshing(false);
        gamesDate.setText(gameScore.getDate());
        adapter.setGames(gameScore.getGames());
    }

    @Override
    public void gamesRetriveError() {
        Snackbar.make(indexCoordinator, getString(R.string.retrivingGamesError), Snackbar.LENGTH_SHORT);
    }

    @Override
    public void localGamesError() {
        Snackbar.make(indexCoordinator, getString(R.string.retrivingGamesError), Snackbar.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void OnGameClick(Game game) {
        Intent i = new Intent(this, GameDetailActivity.class);
        String imgHome = game.getLocal().getImg();
        String imgAway = game.getVisitor().getImg();
        /*
        if(imgHome.contains("UTA")){
            imgHome = "utah";
        }
        if(imgAway.contains("UTA")){
            imgAway = "utah";
        }
        if(imgHome.contains("NOP")){
            imgHome = "no";
        }
        if(imgAway.contains("NOP")){
            imgAway = "no";
        }
        */
        i.putExtra("imghome", imgHome);
        i.putExtra("imgaway", imgAway);
        i.putExtra("acrHome", game.getLocal().getAcronym());
        i.putExtra("acrAway", game.getVisitor().getAcronym());
        i.putExtra("gameid", game.getId());
        i.putExtra("period", game.getPeriod());
        i.putExtra("homeA", game.getLocal().getAcronym());
        i.putExtra("awayA", game.getVisitor().getAcronym());
        i.putExtra("homeName", game.getLocal().getName());
        i.putExtra("awayName", game.getVisitor().getName());
        i.putExtra("homeC", game.getLocal().getCity());
        i.putExtra("awayC", game.getVisitor().getCity());
        i.putExtra("status", game.getState() + "");
        i.putExtra("homeid", game.getLocal().getId());
        i.putExtra("awayid", game.getVisitor().getId());

        startActivity(i);
    }

    @Override
    protected void onRestart() {
        presenter.getScores();
        super.onRestart();
    }

    @Override
    public void onRefresh() {
        presenter.getScores();
    }
}
