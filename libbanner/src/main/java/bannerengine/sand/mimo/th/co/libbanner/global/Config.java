package bannerengine.sand.mimo.th.co.libbanner.global;

/**
 * Created by orapong on 11/9/2017 AD.
 */

public class Config {

    private static Config ourInstance = new Config();
    public static Config getInstance() {
        return ourInstance;
    }
    private String url = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



}
