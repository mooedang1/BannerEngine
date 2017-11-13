package bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by orapong on 11/9/2017 AD.
 */
@Parcel
public class BannerData {
    @SerializedName("data")
    private List<BannerMyData> mData;
    @SerializedName("pagination")
    private BannerMyPagination mPagination;

    public List<BannerMyData> getData() {
        return mData;
    }

    public void setData(List<BannerMyData> data) {
        mData = data;
    }

    public BannerMyPagination getPagination() {
        return mPagination;
    }

    public void setPagination(BannerMyPagination pagination) {
        mPagination = pagination;
    }

}
