package fpr9.com.nbalivefeed.gamedetails;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import fpr9.com.nbalivefeed.R;
import fpr9.com.nbalivefeed.gamedetails.GameDetailFeed.ui.DetailFeedFragment;
import fpr9.com.nbalivefeed.gamedetails.TwitterFeed.ui.TwitterMediaFragment;

public class GameDetailActivity extends AppCompatActivity {

    @Bind(R.id.main_content)
    CoordinatorLayout mainContent;
    String imghome,imgaway;
    private String homea,awaya,homeName,awayName,homeC,awayC;
    private String homeid,awayid,gameStatus;
    private int period;
    /**
     * The {@link PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        imghome = getIntent().getStringExtra("imghome");
        imgaway = getIntent().getStringExtra("imgaway");
        homea = getIntent().getStringExtra("homeA");
        awaya = getIntent().getStringExtra("awayA");
        homeName = getIntent().getStringExtra("homeName");
        awayName = getIntent().getStringExtra("awayName");
        homeC = getIntent().getStringExtra("homeC");
        awayC = getIntent().getStringExtra("awayC");
        homeid = getIntent().getStringExtra("homeid");
        awayid = getIntent().getStringExtra("awayid");
        gameStatus = getIntent().getStringExtra("status");
        String acrHome,acrAway;
        acrHome = getIntent().getStringExtra("acrHome");
        acrAway = getIntent().getStringExtra("acrAway");

        toolbar.setTitle(awayName+"@"+homeName);
        setSupportActionBar(toolbar);

        String gameid = getIntent().getStringExtra("gameid");
        period = getIntent().getIntExtra("period",1);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),imgaway,imghome,
                                                            gameid,period,homea,awaya,
                                                            homeName,awayName,homeC,awayC,
                                                            homeid,awayid,gameStatus,
                                                            acrHome,acrAway
                );

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


    }

    public void showMessage(String message){
        Snackbar.make(mainContent,message,Snackbar.LENGTH_SHORT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_detail, menu);
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



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String imgA,imgH;
        private String gameid;
        private DetailFeedFragment detailFeedFragment;
        private TwitterMediaFragment twitterMediaFragment;
        private String homea,awaya,homeName,awayName,homeC,awayC;
        private String homeid,awayid,gameStatus;
        private String acrHome,acrAway;
        private int period;
        public SectionsPagerAdapter(FragmentManager fm,String imgA,String imgH,String gameid,int period,
                                    String homea,String awaya,String homeName,String awayName,
                                    String homeC, String awayC,
                                    String homeid,String awayid,String gameStatus,
                                    String acrHome,String acrAway
                                    ) {
            super(fm);
            detailFeedFragment = new DetailFeedFragment();
            twitterMediaFragment = new TwitterMediaFragment();
            this.imgA = imgA;
            this.imgH = imgH;
            this.period = period;
            this.gameid = gameid;
            this.homea = homea;
            this.awaya = awaya;
            this.homeName = homeName;
            this.awayName = awayName;
            this.homeC = homeC;
            this.awayC = awayC;
            this.homeid = homeid;
            this.awayid = awayid;
            this.gameStatus = gameStatus;
            this.acrHome = acrHome;
            this.acrAway = acrAway;
        }


        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    detailFeedFragment.setStatus(gameStatus);
                    detailFeedFragment.setIds(homeid,awayid);
                    detailFeedFragment.setNames(homeName,awayName,homeC,awayC,acrHome,acrAway);
                    detailFeedFragment.setGameId(gameid);
                    detailFeedFragment.setImages(imgH,imgA);
                    detailFeedFragment.setPeriod(period);
                    return detailFeedFragment;
                case 1:
                    twitterMediaFragment.setAcronymsAndNames(homea,awaya,homeName,awayName,homeC,awayC);
                    return twitterMediaFragment;
            }
            return twitterMediaFragment;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Stats";
                case 1:
                    return "Media";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
