package bannerengine.sand.mimo.th.co.libbanner.global;

import bannerengine.sand.mimo.th.co.libbanner.R;

/**
 * Created by orapong on 11/9/2017 AD.
 */

public class Config {

    private static Config ourInstance = new Config();
    public static Config getInstance() {
        return ourInstance;
    }
    private final String url = "https://ws-adv.ais.co.th/";
    public int placeholder = R.drawable.placeholder;
    public int bannerWidth;
    public int bannerHeight;

    public String getUrl() {
        return url;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
    }

}
