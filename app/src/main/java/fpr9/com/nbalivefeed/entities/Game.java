package fpr9.com.nbalivefeed.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class Game {
    @SerializedName("gid")
    private String id;

    @SerializedName("p")
    private int period;

    @SerializedName("st")
    private int state;

    @SerializedName("stt")
    private String status;

    @SerializedName("cl")
    private String time_left;

    @SerializedName("v")
    private GameTeam visitor;

    @SerializedName("h")
    private GameTeam local;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime_left() {
        return time_left;
    }

    public void setTime_left(String time_left) {
        this.time_left = time_left;
    }

    public GameTeam getVisitor() {
        return visitor;
    }

    public void setVisitor(GameTeam visitor) {
        this.visitor = visitor;
    }

    public GameTeam getLocal() {
        return local;
    }

    public void setLocal(GameTeam local) {
        this.local = local;
    }
}
