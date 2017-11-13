package bannerengine.sand.mimo.th.co.libbanner.task.network.api;


import bannerengine.sand.mimo.th.co.libbanner.task.network.model.BannerData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by orapong on 11/10/2017 AD.
 */

public interface BannerService {
    @GET()
    Call<BannerData> getBanner();
}
