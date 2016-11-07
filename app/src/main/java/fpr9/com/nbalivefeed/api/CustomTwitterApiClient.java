package fpr9.com.nbalivefeed.api;

import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

/**
 * Created by ykro.
 */
public class CustomTwitterApiClient extends TwitterApiClient {
    public CustomTwitterApiClient() {
    }

    /*public CustomTwitterApiClient(Session session) {
        super((TwitterSession) session);
    }*/

    public TimelineService getTimelineService() {
        return getService(TimelineService.class);
    }
}
