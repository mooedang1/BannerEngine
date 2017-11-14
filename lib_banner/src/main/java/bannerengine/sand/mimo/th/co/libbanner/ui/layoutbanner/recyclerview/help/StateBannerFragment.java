package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.help;

/**
 * Created by orapong on 11/14/2017 AD.
 */

public class StateBannerFragment {
    private static StateBannerFragment ourInstance = new StateBannerFragment();
    public static StateBannerFragment getInstance() {
        return ourInstance;
    }
    public enum StateFragment {
        SHOW_BANNER,
        SHOW_WEB
    }
    public enum StateYoutube {
        YOUTUBE_UNDEFINED,
        YOUTUBE_UNSTARTED,
        YOUTUBE_ENDED,
        YOUTUBE_PAUSED,
        YOUTUBE_PLAY,
        YOUTUBE_BUFFERING,
        YOUTUBE_CUED
    }
}
