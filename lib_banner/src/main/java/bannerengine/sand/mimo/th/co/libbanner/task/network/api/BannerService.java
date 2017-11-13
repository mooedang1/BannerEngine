package bannerengine.sand.mimo.th.co.libbanner.task.network.api;


import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by orapong on 11/10/2017 AD.
 */

public interface BannerService {
    @GET("/advertiser")
    Call<BannerData> getBanner(
            @Query("chanelId") String chanelId,
            @Query("categoryId") String categoryId,
            @Query("directUrl") Boolean directUrl,
            @Query("limit") String limit);
}
