package bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.help.StateBannerFragment;

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

    private StateBannerFragment.StateFragment stateBannerFragment = StateBannerFragment.StateFragment.SHOW_BANNER;
    private StateBannerFragment.StateYoutube stateYoutube = StateBannerFragment.StateYoutube.YOUTUBE_UNDEFINED;
    private StateBannerFragment.StatusDirect statusDirect = StateBannerFragment.StatusDirect.NOTING;

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

    public StateBannerFragment.StateFragment getStateBannerFragment() {
        return stateBannerFragment;
    }

    public void setStateBannerFragment(StateBannerFragment.StateFragment stateBannerFragment) {
        this.stateBannerFragment = stateBannerFragment;
    }

    public StateBannerFragment.StateYoutube getStateYoutube() {
        return stateYoutube;
    }

    public void setStateYoutube(StateBannerFragment.StateYoutube stateYoutube) {
        this.stateYoutube = stateYoutube;
    }

    public StateBannerFragment.StatusDirect getStatusDirect() {
        return statusDirect;
    }

    public void setStatusDirect(StateBannerFragment.StatusDirect statusDirect) {
        this.statusDirect = statusDirect;
    }

    public void setStatusItem(){

        if(getDirectUrl()!=null && getDirectUrl().equals("")==false){
            //1. "#" ไม่ทำ
            //2. "youtube"
            //3. "http://live360?"
            //4. "http://www.xxxx" ออกนอก
            //Toast.makeText(ctx, oDirectUrl, Toast.LENGTH_SHORT).show();
            if (getDirectUrl().contains("#")) {
                //ไม่ทำไร
            } else if (getDirectUrl().contains("youtube")) {
                setStatusDirect(StateBannerFragment.StatusDirect.YOUTUBE);
            } else {
                setStatusDirect(StateBannerFragment.StatusDirect.WEB);
            }
        }
    }

}
