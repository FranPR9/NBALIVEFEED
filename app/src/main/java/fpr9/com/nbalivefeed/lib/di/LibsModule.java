package fpr9.com.nbalivefeed.lib.di;

import android.app.Activity;

import com.bumptech.glide.request.RequestListener;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import fpr9.com.nbalivefeed.lib.EventBus;
import fpr9.com.nbalivefeed.lib.GlideImageLoader;
import fpr9.com.nbalivefeed.lib.GreenRobotEventBus;
import fpr9.com.nbalivefeed.lib.ImageLoader;


/**
 * Created by ykro.
 */
@Module
public class LibsModule {
    Activity activity;

    public LibsModule() {
    }
    public LibsModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @Singleton
    EventBus provideEventBus() {
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader provideImageLoader(Activity activity) {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (activity != null) {
            imageLoader.setLoaderContext(activity);
        }
        return imageLoader;
    }


    @Provides
    @Singleton
    Activity provideActivity(){
        return this.activity;
    }

}
