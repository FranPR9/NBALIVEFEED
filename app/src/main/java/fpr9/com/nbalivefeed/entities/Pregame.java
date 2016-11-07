package fpr9.com.nbalivefeed.entities;

/**
 * Created by FranciscoPR on 07/11/16.
 */
public class Pregame {

    private String gameDate;
    private RecordContainer home;
    private RecordContainer visitor;

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public RecordContainer getHome() {
        return home;
    }

    public void setHome(RecordContainer home) {
        this.home = home;
    }

    public RecordContainer getVisitor() {
        return visitor;
    }

    public void setVisitor(RecordContainer visitor) {
        this.visitor = visitor;
    }
}
