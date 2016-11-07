package fpr9.com.nbalivefeed.lib;

/**
 * Created by ykro.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
    boolean isRegister(Object event);

}
