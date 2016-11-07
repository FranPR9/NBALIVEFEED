package fpr9.com.nbalivefeed.gamesindex;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public class IndexInteractorImpl implements IndexInteractor {

    private IndexRepository repository;

    public IndexInteractorImpl(IndexRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeScores() {
        repository.getScores();
    }
}
