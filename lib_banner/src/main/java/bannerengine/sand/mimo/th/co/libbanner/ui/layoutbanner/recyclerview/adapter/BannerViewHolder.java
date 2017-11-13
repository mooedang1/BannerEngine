package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import bannerengine.sand.mimo.th.co.libbanner.R;

/**
 * Created by orapong on 11/13/2017 AD.
 */

public  class BannerViewHolder extends RecyclerView.ViewHolder{
    FrameLayout LayoutBannerContent;
    ImageView imageView;
    public BannerViewHolder(View view){
        super(view);
        LayoutBannerContent = (FrameLayout) view.findViewById(R.id.LayoutBannerContent);
        imageView = (ImageView) view.findViewById(R.id.ImageView);
    }
}