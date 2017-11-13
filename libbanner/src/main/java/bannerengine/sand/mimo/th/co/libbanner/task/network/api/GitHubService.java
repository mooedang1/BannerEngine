package bannerengine.sand.mimo.th.co.libbanner.task.network.api;


import bannerengine.sand.mimo.th.co.libbanner.task.network.model.BannerData;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by vanirut on 14-Jan-16.
 */
public interface GitHubService {
    @GET("/users/{username}")
    Call<User> getUser(@Path("username") String username);


    @GET()
    Call<BannerData>getBanner();
}
