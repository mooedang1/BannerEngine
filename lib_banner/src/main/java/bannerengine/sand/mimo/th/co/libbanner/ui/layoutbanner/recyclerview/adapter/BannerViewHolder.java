package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import bannerengine.sand.mimo.th.co.libbanner.R;

/**
 * Created by orapong on 11/13/2017 AD.
 */

public class BannerViewHolder extends RecyclerView.ViewHolder {
    FrameLayout LayoutBannerContent;
    ImageView imageView,imageViewIconVideo;
    WebView webView;
    FrameLayout bannerImage, bannerVideo;

    public BannerViewHolder(View view) {
        super(view);
        LayoutBannerContent = (FrameLayout) view.findViewById(R.id.LayoutBannerContent);
        bannerImage = (FrameLayout) view.findViewById(R.id.bannerImage);
        bannerVideo = (FrameLayout) view.findViewById(R.id.bannerVideo);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        imageViewIconVideo = (ImageView) view.findViewById(R.id.imageViewIconVideo);
        webView = (WebView) view.findViewById(R.id.webView);
    }

}