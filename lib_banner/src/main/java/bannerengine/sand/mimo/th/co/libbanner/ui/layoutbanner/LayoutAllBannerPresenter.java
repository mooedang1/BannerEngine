package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner;

import bannerengine.sand.mimo.th.co.libbanner.global.LogUtil;
import bannerengine.sand.mimo.th.co.libbanner.task.network.NetworkConnectionManager;
import bannerengine.sand.mimo.th.co.libbanner.task.network.callback.onNetworkCallbackListener;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by orapong on 11/10/2017 AD.
 */

public class LayoutAllBannerPresenter implements LayoutAllBannerContractor.Action, onNetworkCallbackListener {
    private LayoutAllBannerContractor.View view;
    private BannerData bannerData = new BannerData();

    public LayoutAllBannerPresenter(LayoutAllBannerContractor.View view) {
        this.view = view;
    }

    @Override
    public void callService(String chanelId,String categoryId,String limit,boolean directUrl){
        new NetworkConnectionManager().callServer(this,chanelId,categoryId,limit,directUrl);
    }

    //callServer start
    @Override
    public void onResponse(Call<BannerData> call, BannerData bannerData) {
        LogUtil.d("onResponse");
        if(bannerData!=null){
            this.bannerData = bannerData;
            view.setUpDataItem(this.bannerData);
        }
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
    //callServer end
}