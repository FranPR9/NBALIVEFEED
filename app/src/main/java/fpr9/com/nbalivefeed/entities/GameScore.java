package fpr9.com.nbalivefeed.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class GameScore {

    @SerializedName("gdte")
    private String date;

    @SerializedName("g")
    private List<Game> games;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
