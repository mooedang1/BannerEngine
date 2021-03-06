package bannerengine.sand.mimo.th.co.bannerengine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.LayoutAllBanner;

public class MainActivity extends AppCompatActivity implements LayoutAllBanner.OnListener {
    LayoutAllBanner layoutAllBanner1,layoutAllBanner2,layoutAllBanner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutAllBanner1 = (LayoutAllBanner) findViewById(R.id.layoutAllBanner1);
        layoutAllBanner2 = (LayoutAllBanner) findViewById(R.id.layoutAllBanner2);
        layoutAllBanner3 = (LayoutAllBanner) findViewById(R.id.layoutAllBanner3);
        // callservice();
    }

    @Override
    protected void onResume() {
        super.onResume();
        callservice();
    }

    public void reset(View view) {
        // layoutAllBanner.removeBannerAll();
        callservice();
    }

    public void callservice() {
//        layoutAllBanner.removeBannerAll();
        //layoutAllBanner.setUp("34256e7f631d9b279c40274b066edeed","categorypoints","10",true);
        //layoutAllBanner.setUp("5648632e461fb17206f823c1d9e43bf3","callingvideo","10",true);
//        layoutAllBanner.setUp("6651bc909ffd809a562227f9b083f0aa76aee5cc", "freesongs", "10", true);

        layoutAllBanner1
                .setAutoRun(false)
                .setSpeedScroll(5000)
                .setUp("6651bc909ffd809a562227f9b083f0aa76aee5cc", "freesongs", "10", true);
        layoutAllBanner1.setListener(this);
        layoutAllBanner2
                .setAutoRun(true)
                .setSpeedScroll(2000)
                .setUp("34256e7f631d9b279c40274b066edeed", "categorypoints", "10", true);
        layoutAllBanner2.setListener(this);
//        layoutAllBanner3
//                .setAutoRun(true)
//                .setSpeedScroll(1000)
//                .setUp("5648632e461fb17206f823c1d9e43bf3", "callingvideo", "10", true);
//        layoutAllBanner3.setListener(this);
    }

    public void nextpage(View view) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void OnClickItemBanner(BannerMyData bannerMyData) {
        Toast.makeText(this, bannerMyData.getImage().getTargetBannerBBHigh(), Toast.LENGTH_SHORT).show();
    }

//    public void test() {
//        layoutAllBanner
//                .setBase_url("https://ws-adv.ais.co.th/")
//                .setAutoRun(true)
//                .setSpeedScroll(5000)
//                .setPlaceholder(R.drawable.placeholder1);
//        layoutAllBanner.setUp("6651bc909ffd809a562227f9b083f0aa76aee5cc", "freesongs", "10", true);
//    }
}
