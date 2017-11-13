package bannerengine.sand.mimo.th.co.libbanner.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import bannerengine.sand.mimo.th.co.libbanner.global.LogUtil;
import bannerengine.sand.mimo.th.co.libbanner.task.network.NetworkConnectionManager;
import bannerengine.sand.mimo.th.co.libbanner.task.network.callback.onNetworkCallbackListener;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.BannerData;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by orapong on 11/9/2017 AD.
 */

public class LayoutAllBanner  extends LinearLayout implements onNetworkCallbackListener {
    public LayoutAllBanner(Context context) {
        super(context);
    }

    public LayoutAllBanner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutAllBanner(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setUp(){

    }

    public void callService(){
        new NetworkConnectionManager().callServer(this,"https://ws-adv.ais.co.th/advertiser?chanelId=5648632e461fb17206f823c1d9e43bf3&categoryId=callingvideo&directUrl=true&limit=20");
    }

    @Override
    public void onResponse(Call<BannerData> call, BannerData bannerData) {
        LogUtil.d("onResponse");
    }

    @Override
    public void onBodyError(ResponseBody responseBodyError) {
        LogUtil.d("onBodyError");
    }

    @Override
    public void onBodyErrorIsNull() {
        LogUtil.d("onBodyErrorIsNull");
    }

    @Override
    public void onFailure(Throwable t) {
        LogUtil.d("onFailure");
    }
}
