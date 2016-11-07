package fpr9.com.nbalivefeed.entities;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class StatsHomeAway {

    private StatsContainer home;
    private StatsContainer visitor;

    public StatsContainer getHome() {
        return home;
    }

    public void setHome(StatsContainer home) {
        this.home = home;
    }

    public StatsContainer getVisitor() {
        return visitor;
    }

    public void setVisitor(StatsContainer visitor) {
        this.visitor = visitor;
    }
}
