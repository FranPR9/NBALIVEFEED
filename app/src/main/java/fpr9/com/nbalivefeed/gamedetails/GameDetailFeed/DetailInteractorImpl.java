package fpr9.com.nbalivefeed.gamedetails.GameDetailFeed;

/**
 * Created by FranciscoPR on 29/10/16.
 */
public class DetailInteractorImpl implements DetailInteractor {

    private DetailRepository repository;

    public DetailInteractorImpl(DetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeStats(String gameid) {
        repository.getStats(gameid);
    }

    @Override
    public void executePlaybyPlay(String gameid) {
        repository.getPlaybyPlay(gameid);
    }

    @Override
    public void executeHomeCommonStats(String teamid) {
        repository.getHomeCommonStats(teamid);
    }

    @Override
    public void executeAwayCommonStats(String teamid) {
        repository.getAwayCommonStats(teamid);
    }

    @Override
    public void executeHomeStats(String teamid) {
        repository.getHomeStats(teamid);
    }

    @Override
    public void executeAwayStats(String teamid) {
        repository.getAwayStats(teamid);
    }

    @Override
    public void executeLastGamesHome(String teamid) {
        repository.getLastGamesHome(teamid);
    }

    @Override
    public void executeLastGamesAway(String teamid) {
        repository.getLastGamesAway(teamid);
    }

    @Override
    public void executeInjuryReport(String cityHome, String cityAway) {
        repository.getInjuryReport(cityHome, cityAway);
    }

    @Override
    public void executeOdds() {
        repository.getOdds();
    }

    @Override
    public void executeRecord(String gameid) {
        repository.getRecord(gameid);
    }
}
