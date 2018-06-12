package com.example.lk.kotlinmvp.uitls;
import android.databinding.BindingAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.lk.kotlinmvp.R;


/**
 * Created by lk on 2018/6/11.
 */

public class ImageLoadUtilsJava {

    @BindingAdapter("imageUrl")
    public static void display(ImageView imageView, String url) {
        if (imageView == null) {
            return;
        }
        Glide.with(imageView.getContext()).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_empty_picture)
                .crossFade().into(imageView);
    }
}
