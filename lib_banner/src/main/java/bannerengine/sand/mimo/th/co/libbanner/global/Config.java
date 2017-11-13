package bannerengine.sand.mimo.th.co.libbanner.global;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

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
    public boolean autoRun = true;
    public int speedScroll = 1200;
    public int placeholder = R.drawable.placeholder;
    public int bannerWidth;
    public int bannerHeight;

    public int screenWidth = 0;
    public int screenHeight = 0;
    public int size16to9 = 0;


    public void setScreenSizeDevice(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.heightPixels;
        screenHeight = metrics.widthPixels;
    }

    public void setSize16to9(Context context){
        setScreenSizeDevice(context);
        size16to9 = (screenHeight/16)*9;
        LogUtil.d(Integer.toString(size16to9));
    }

    public float getSize16to9(){
        return size16to9;
    }

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
