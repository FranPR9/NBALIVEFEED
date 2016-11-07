package fpr9.com.nbalivefeed.entities;

/**
 * Created by FranciscoPR on 30/10/16.
 */
public class StatScore {

    private ScoreperTeam home;
    private ScoreperTeam visitor;

    private PeriodTime periodTime;

    public PeriodTime getPeriodTime() {
        return periodTime;
    }

    public void setPeriodTime(PeriodTime periodTime) {
        this.periodTime = periodTime;
    }

    public ScoreperTeam getHome() {
        return home;
    }

    public void setHome(ScoreperTeam home) {
        this.home = home;
    }

    public ScoreperTeam getVisitor() {
        return visitor;
    }

    public void setVisitor(ScoreperTeam visitor) {
        this.visitor = visitor;
    }
}
