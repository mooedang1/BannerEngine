package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner;

import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;

/**
 * Created by orapong on 11/10/2017 AD.
 */

public class LayoutAllBannerContractor {
    interface View{
        void setUpDataItem(BannerData bannerData);

        void setBreakAutoRun(boolean breakAutoRun);
    }

    interface Action{
        void callService(String chanelId,String categoryId,String limit,boolean directUrl);

        void OnClickItemBanner(BannerMyData bannerMyData);
    }
}
