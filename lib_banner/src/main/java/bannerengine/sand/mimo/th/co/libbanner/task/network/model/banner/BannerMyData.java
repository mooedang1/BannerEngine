package bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by orapong on 11/10/2017 AD.
 */
@Parcel
public class BannerMyData {
    @SerializedName("description")
    private String mDescription;
    @SerializedName("directUrl")
    private String mDirectUrl;
    @SerializedName("image")
    private BannerMyImage mImage;
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

    public BannerMyImage getImage() {
        return mImage;
    }

    public void setImage(BannerMyImage image) {
        mImage = image;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

}
