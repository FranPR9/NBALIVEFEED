package fpr9.com.nbalivefeed.entities;

import java.util.List;

/**
 * Created by FranciscoPR on 03/11/16.
 */
public class InjuryContainer {

    boolean success;

    Injury response;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Injury getResponse() {
        return response;
    }

    public void setResponse(Injury response) {
        this.response = response;
    }
}
