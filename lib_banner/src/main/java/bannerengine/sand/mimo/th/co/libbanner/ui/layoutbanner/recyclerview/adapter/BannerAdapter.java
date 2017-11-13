package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.Iterator;
import java.util.List;

import bannerengine.sand.mimo.th.co.libbanner.R;
import bannerengine.sand.mimo.th.co.libbanner.global.Config;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;

/**
 * Created by orapong on 11/10/2017 AD.
 */

public class BannerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<BannerMyData> bannerDataList;
    private OnListener mListener;

    public interface OnListener{
        void OnClickItemBanner(BannerMyData bannerMyData);
    }

    public void setListener(OnListener mListener) {
        this.mListener = mListener;
    }

    public BannerAdapter(List<BannerMyData> bannerDataList){
        this.bannerDataList = bannerDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner_content, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            bannerViewHolder.LayoutBannerContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickBannerItem(view);
                }
            });
            Glide.with(bannerViewHolder.imageView.getContext())
                    .load(bannerDataList.get(position).getImage().getTargetBannerBBHigh())
                    .placeholder(Config.getInstance().getPlaceholder())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(bannerViewHolder.imageView);
        }
    }

    @Override
    public int getItemCount() {
        return bannerDataList.size();
    }

    public List<BannerMyData> getBannerDataList() {
        return bannerDataList;
    }

    public void removeAllBanner(){
        if(bannerDataList.isEmpty()){
            return;
        }
        int totalSize = bannerDataList.size();
        Iterator<BannerMyData> iterator = bannerDataList.iterator();
        while (iterator.hasNext()) {
            BannerMyData bannerMyData = iterator.next();
            iterator.remove();
        }
        notifyItemRangeRemoved(0, totalSize);
    }

    private void clickBannerItem(View view){

    }
}
