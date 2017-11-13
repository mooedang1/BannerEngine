package bannerengine.sand.mimo.th.co.bannerengine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.LayoutAllBanner;

public class MainActivity extends AppCompatActivity {
    LayoutAllBanner layoutAllBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutAllBanner = (LayoutAllBanner) findViewById(R.id.layoutAllBanner);
        layoutAllBanner.setUp("6651bc909ffd809a562227f9b083f0aa76aee5cc","cmmahome","10",true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    public void reset(View view) {
        layoutAllBanner.removeBannerAll();
        layoutAllBanner.setUp("6651bc909ffd809a562227f9b083f0aa76aee5cc","cmmahome","10",true);
        layoutAllBanner.setListener(new LayoutAllBanner.OnListener() {
            @Override
            public void OnClickItemBanner(BannerMyData bannerMyData) {

            }
        });
    }
}
