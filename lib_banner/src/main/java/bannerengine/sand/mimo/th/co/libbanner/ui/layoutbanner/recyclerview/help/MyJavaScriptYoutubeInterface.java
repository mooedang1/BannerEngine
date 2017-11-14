package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.help;

import android.webkit.JavascriptInterface;

/**
 * Created by orapong on 11/14/2017 AD.
 */

public class MyJavaScriptYoutubeInterface {
    OnTask onTask = null;
    public interface OnTask {
        void onUnstarted();
        void onEnded();
        void onPlaying();
        void onPaused();
        void onBuffering();
        void onCued();
    }
    public void setOnTask(OnTask onTask) {
        this.onTask = onTask;
    }


    public MyJavaScriptYoutubeInterface  () {

    }


    @JavascriptInterface
    public void onUnstarted(){
//        Toast toast = Toast.makeText(context, "onUnstarted", Toast.LENGTH_SHORT);
//        toast.show();
        onTask.onUnstarted();
    }

    @JavascriptInterface
    public void onEnded(){
//        Toast toast = Toast.makeText(context, "onEnded", Toast.LENGTH_SHORT);
//        toast.show();
        onTask.onEnded();
    }

    @JavascriptInterface
    public void onPlaying(){
//        Toast toast = Toast.makeText(context, "onPlaying", Toast.LENGTH_SHORT);
//        toast.show();
        onTask.onPlaying();
    }

    @JavascriptInterface
    public void onPaused(){
//        Toast toast = Toast.makeText(context, "onPaused", Toast.LENGTH_SHORT);
//        toast.show();
        onTask.onPaused();
    }

    @JavascriptInterface
    public void onBuffering(){
//        Toast toast = Toast.makeText(context, "onBuffering", Toast.LENGTH_SHORT);
//        toast.show();
        onTask.onBuffering();
    }

    @JavascriptInterface
    public void onCued(){
//        Toast toast = Toast.makeText(context, "onCued", Toast.LENGTH_SHORT);
//        toast.show();
        onTask.onCued();
    }






}
