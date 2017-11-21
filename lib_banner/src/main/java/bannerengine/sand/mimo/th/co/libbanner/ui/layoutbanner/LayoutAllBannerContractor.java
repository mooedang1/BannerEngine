package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner;

import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;

/**
 * Created by orapong on 11/10/2017 AD.
 */

public class LayoutAllBannerContractor {
    interface View{
        void setUpDataItem(BannerData bannerData);

<<<<<<< HEAD
        LayoutAllBanner removeBannerAll();
=======
        void setBreakAutoRun(boolean breakAutoRun);
>>>>>>> parent of 2fff875... no message
    }

    interface Action{
        void callService(String chanelId,String categoryId,String limit,boolean directUrl);

        void OnClickItemBanner(BannerMyData bannerMyData);
    }
}
