package bannerengine.sand.mimo.th.co.libbanner.global;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import bannerengine.sand.mimo.th.co.libbanner.R;

/**
 * Created by orapong on 11/9/2017 AD.
 */

public class Config {



    private String base_url = "https://ws-adv.ais.co.th/";
    private boolean autoRun = true;
    private boolean breakAutoRun = false;
    private int speedScroll = 1200;
    private int placeholder = R.drawable.placeholder;
    private int bannerWidth;
    private int bannerHeight;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private int size16to9 = 0;


    private void setScreenSizeDevice(Context context) {
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        screenWidth = metrics.heightPixels;
        screenHeight = metrics.widthPixels;
    }

    public void setSize16to9(Context context) {
        setScreenSizeDevice(context);
        size16to9 = (screenHeight / 16) * 9;
        LogUtil.d(Integer.toString(size16to9));
    }

    public void setBase_url(String base_url) {
        this.base_url = base_url;
    }

    public String getBase_url() {
        return base_url;
    }

    public int getSize16to9() {
        return size16to9;
    }

    public int getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(int placeholder) {
        this.placeholder = placeholder;
    }

    public int getSpeedScroll() {
        return speedScroll;
    }

    public void setSpeedScroll(int speedScroll) {
        this.speedScroll = speedScroll;
    }

    public boolean getAutoRun() {
        return autoRun;
    }

    public void setAutoRun(boolean autoRun) {
        this.autoRun = autoRun;
    }

    public boolean getBreakAutoRun() {
        return breakAutoRun;
    }

    public void setBreakAutoRun(boolean breakAutoRun) {
        this.breakAutoRun = breakAutoRun;
    }

}
