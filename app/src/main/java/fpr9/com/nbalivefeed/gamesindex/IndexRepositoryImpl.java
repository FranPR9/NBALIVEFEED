package fpr9.com.nbalivefeed.gamesindex;

import fpr9.com.nbalivefeed.api.FeedService;
import fpr9.com.nbalivefeed.entities.GameScore;
import fpr9.com.nbalivefeed.entities.GameScoreRespond;
import fpr9.com.nbalivefeed.gamesindex.event.IndexEvent;
import fpr9.com.nbalivefeed.lib.EventBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by FranciscoPR on 28/10/16.
 */
public class IndexRepositoryImpl implements IndexRepository{

    EventBus eventBus;
    FeedService feedService;

    public IndexRepositoryImpl(EventBus eventBus, FeedService feedService) {
        this.eventBus = eventBus;
        this.feedService = feedService;
    }

    @Override
    public void getScores() {
        Call<GameScoreRespond> call = feedService.getScores();
        call.enqueue(new Callback<GameScoreRespond>() {
            @Override
            public void onResponse(Call<GameScoreRespond> call, Response<GameScoreRespond> response) {
                if(response.isSuccessful()){
                    GameScoreRespond aux = response.body();
                    if(aux!=null){
                        if(aux.getGs().getGames().size()>0){
                            post(null, IndexEvent.SCORE_TYPE,aux.getGs(),true);
                        }else{
                            post("no games! );", IndexEvent.SCORE_TYPE,aux.getGs(),false);
                        }
                    }else{
                        post("Error! );", IndexEvent.SCORE_TYPE,null,false);
                    }
                }
                else{
                    post("error!", IndexEvent.SCORE_TYPE,null,false);
                }
            }

            @Override
            public void onFailure(Call<GameScoreRespond> call, Throwable t) {
                post(t.getMessage(), IndexEvent.SCORE_TYPE,null,false);
            }
        });
    }

    private void post(String error, int scoreType, GameScore aux,boolean success) {
        IndexEvent indexEvent = new IndexEvent();
        indexEvent.setError(error);
        indexEvent.setGameScore(aux);
        indexEvent.setType(scoreType);
        indexEvent.setSuccess(success);

        eventBus.post(indexEvent);
    }
}
