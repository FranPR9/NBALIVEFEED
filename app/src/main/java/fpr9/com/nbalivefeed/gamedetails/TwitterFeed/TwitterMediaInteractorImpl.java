package fpr9.com.nbalivefeed.gamedetails.TwitterFeed;

/**
 * Created by FranciscoPR on 31/10/16.
 */
public class TwitterMediaInteractorImpl implements TwitterMediaInteractor {

    private TwitterMediaRepository repository;

    public TwitterMediaInteractorImpl(TwitterMediaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void executeTwitterMedia(String query) {
        repository.getNBATweets(query);
    }
}
