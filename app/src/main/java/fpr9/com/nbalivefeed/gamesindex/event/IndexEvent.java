package fpr9.com.nbalivefeed.gamesindex.event;

import fpr9.com.nbalivefeed.entities.GameScore;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public class IndexEvent {

    private int type;
    private String error;
    private boolean success;
    private GameScore gameScore;

    public static final int SCORE_TYPE = 1;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public GameScore getGameScore() {
        return gameScore;
    }

    public void setGameScore(GameScore gameScore) {
        this.gameScore = gameScore;
    }
}
