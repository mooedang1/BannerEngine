package bannerengine.sand.mimo.th.co.libbanner.global;

import android.net.Uri;

import java.util.HashMap;

/**
 * Created by orapong on 11/14/2017 AD.
 */

public class UrlParameter {
    private static UrlParameter ourInstance = new UrlParameter();
    public static UrlParameter getInstance() {
        return ourInstance;
    }
    public HashMap<String, String> getQueryString(String url) {
        Uri uri = Uri.parse(url);
        HashMap<String, String> map = new HashMap<>();
        for (String paramName : uri.getQueryParameterNames()) {
            if (paramName != null) {
                String paramValue = uri.getQueryParameter(paramName);
                if (paramValue != null) {
                    map.put(paramName, paramValue);
                }
            }
        }
        return map;
    }
}
