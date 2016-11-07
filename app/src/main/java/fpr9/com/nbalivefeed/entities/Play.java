package fpr9.com.nbalivefeed.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by FranciscoPR on 30/10/16.
 */
public class Play {

    @SerializedName("c")
    private String clock;

    @SerializedName("d")
    private String description;

    @SerializedName("players")
    private List<String> players;

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPlayers() {
        return players;
    }

    public void setPlayers(List<String> players) {
        this.players = players;
    }
}
