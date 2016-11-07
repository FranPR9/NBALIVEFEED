package fpr9.com.nbalivefeed.lib;

import android.widget.ImageView;

/**
 * Created by ykro.
 */
public interface ImageLoader {
    void load(ImageView imageView, String URL);

    void setOnFinishedImageLoadingListener(Object object);

}
