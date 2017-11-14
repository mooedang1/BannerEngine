package bannerengine.sand.mimo.th.co.bannerengine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.LayoutAllBanner;

public class MainActivity extends AppCompatActivity implements LayoutAllBanner.OnListener {
    LayoutAllBanner layoutAllBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutAllBanner = (LayoutAllBanner) findViewById(R.id.layoutAllBanner);
       // callservice();
    }

    @Override
    protected void onResume() {
        super.onResume();
        callservice();
    }

    public void reset(View view) {
        callservice();
    }

    public void callservice(){
        layoutAllBanner.removeBannerAll();
        //layoutAllBanner.setUp("34256e7f631d9b279c40274b066edeed","categorypoints","10",true);
        //layoutAllBanner.setUp("5648632e461fb17206f823c1d9e43bf3","callingvideo","10",true);
        layoutAllBanner.setUp("6651bc909ffd809a562227f9b083f0aa76aee5cc","freesongs","10",true);
        layoutAllBanner.setListener(this);
    }

    public void nextpage(View view) {
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
    }

    @Override
    public void OnClickItemBanner(BannerMyData bannerMyData) {
        Toast.makeText(this,bannerMyData.getImage().getTargetBannerBBHigh(),Toast.LENGTH_SHORT).show();
    }

}
