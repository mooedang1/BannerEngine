package bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by orapong on 11/10/2017 AD.
 */
@Parcel
public class BannerMyImage {
    @SerializedName("targetBannerBBHigh")
    private String mTargetBannerBBHigh;

    public String getTargetBannerBBHigh() {
        return mTargetBannerBBHigh;
    }

    public void setTargetBannerBBHigh(String targetBannerBBHigh) {
        mTargetBannerBBHigh = targetBannerBBHigh;
    }
}
