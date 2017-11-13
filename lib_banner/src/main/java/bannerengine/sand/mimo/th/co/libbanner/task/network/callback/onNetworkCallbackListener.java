package bannerengine.sand.mimo.th.co.libbanner.task.network.callback;


import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by vanirut on 14-Jan-16.
 */
public interface onNetworkCallbackListener {
    public void onResponse(Call<BannerData> call, BannerData bannerData);
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
}
