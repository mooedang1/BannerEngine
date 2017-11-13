package bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by orapong on 11/10/2017 AD.
 */
@Parcel
public class BannerMyPagination {
    @SerializedName("count")
    private Long mCount;
    @SerializedName("limit")
    private Long mLimit;
    @SerializedName("total")
    private Long mTotal;

    public Long getCount() {
        return mCount;
    }

    public void setCount(Long count) {
        mCount = count;
    }

    public Long getLimit() {
        return mLimit;
    }

    public void setLimit(Long limit) {
        mLimit = limit;
    }

    public Long getTotal() {
        return mTotal;
    }

    public void setTotal(Long total) {
        mTotal = total;
    }
}
