package bannerengine.sand.mimo.th.co.libbanner.task.network;


import java.util.concurrent.TimeUnit;

import bannerengine.sand.mimo.th.co.libbanner.global.Config;
import bannerengine.sand.mimo.th.co.libbanner.task.network.api.BannerService;
import bannerengine.sand.mimo.th.co.libbanner.task.network.callback.onNetworkCallbackListener;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by vanirut on 14-Jan-16.
 */
public class NetworkConnectionManager {

    public NetworkConnectionManager() {
    }

    public void callServer(final onNetworkCallbackListener listener,Config config,String chanelId,String categoryId,String limit,boolean directUrl){

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60 / 2, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                //.addInterceptor(logging)
                .cache(null)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(config.getBase_url())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BannerService bannerService = retrofit.create(BannerService.class);
        Call call = bannerService.getBanner(chanelId,categoryId,directUrl,limit);
        call.enqueue(new Callback<BannerData>() {
            @Override
            public void onResponse(Call<BannerData> call, Response<BannerData> response) {
                BannerData bannerData = response.body();
                if (bannerData == null) {
                    //404 or the response cannot be converted to User.
                    ResponseBody responseBody = response.errorBody();
                    if (responseBody != null) {
                        listener.onBodyError(responseBody);
                    } else {
                        listener.onBodyErrorIsNull();
                    }
                } else {
                    //200
                    listener.onResponse(call, bannerData);
                }
            }
            @Override
            public void onFailure(Call<BannerData> call, Throwable t) {
                listener.onFailure(t);
            }
        });
    }
}