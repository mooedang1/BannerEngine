package bannerengine.sand.mimo.th.co.libbanner.task.network.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by orapong on 11/9/2017 AD.
 */
@Parcel
public class BannerData {
    @SerializedName("data")
    private List<Datum> mData;
    @SerializedName("pagination")
    private Pagination mPagination;

    public List<Datum> getData() {
        return mData;
    }

    public void setData(List<Datum> data) {
        mData = data;
    }

    public Pagination getPagination() {
        return mPagination;
    }

    public void setPagination(Pagination pagination) {
        mPagination = pagination;
    }

    @SuppressWarnings("unused")
    public class Datum {
        @SerializedName("description")
        private String mDescription;
        @SerializedName("directUrl")
        private String mDirectUrl;
        @SerializedName("image")
        private Image mImage;
        @SerializedName("url")
        private String mUrl;

        public String getDescription() {
            return mDescription;
        }

        public void setDescription(String description) {
            mDescription = description;
        }

        public String getDirectUrl() {
            return mDirectUrl;
        }

        public void setDirectUrl(String directUrl) {
            mDirectUrl = directUrl;
        }

        public Image getImage() {
            return mImage;
        }

        public void setImage(Image image) {
            mImage = image;
        }

        public String getUrl() {
            return mUrl;
        }

        public void setUrl(String url) {
            mUrl = url;
        }

    }

    @SuppressWarnings("unused")
    public class Image {

        @SerializedName("targetBannerBBHigh")
        private String mTargetBannerBBHigh;

        public String getTargetBannerBBHigh() {
            return mTargetBannerBBHigh;
        }

        public void setTargetBannerBBHigh(String targetBannerBBHigh) {
            mTargetBannerBBHigh = targetBannerBBHigh;
        }

    }

    @SuppressWarnings("unused")
    public class Pagination {

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
}
