package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import bannerengine.sand.mimo.th.co.libbanner.R;
import bannerengine.sand.mimo.th.co.libbanner.global.Config;
import bannerengine.sand.mimo.th.co.libbanner.global.LogUtil;
import bannerengine.sand.mimo.th.co.libbanner.global.UrlParameter;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.help.MyJavaScriptYoutubeInterface;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.help.StateBannerFragment;

/**
 * Created by orapong on 11/10/2017 AD.
 */

public class BannerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BannerMyData> bannerDataList;
    private OnListener mListener;
    private Runnable runnable;

    public interface OnListener {
        void OnClickItemBanner(BannerMyData bannerMyData);
    }

    public void setListener(OnListener mListener) {
        this.mListener = mListener;
    }

    public BannerAdapter(List<BannerMyData> bannerDataList) {
        this.bannerDataList = bannerDataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_banner_content, parent, false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BannerViewHolder) {

            final BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
            final BannerMyData bannerMyData = bannerDataList.get(position);

            bannerViewHolder.LayoutBannerContent.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, Config.getInstance().getSize16to9()));

            Glide.with(bannerViewHolder.imageView.getContext())
                    .load(bannerMyData.getImage().getTargetBannerBBHigh())
                    .placeholder(Config.getInstance().getPlaceholder())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(bannerViewHolder.imageView);

            setShowImageOrYoutube(bannerViewHolder, bannerMyData);
            setShowIconVideo(bannerViewHolder, bannerMyData);
            bannerViewHolder.LayoutBannerContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickControlShowLayOut(bannerViewHolder, bannerMyData);
                    clickBannerItem(view, bannerMyData);
                }
            });

            if(bannerMyData.getStateYoutube() == StateBannerFragment.StateYoutube.YOUTUBE_BREAK){
                bannerViewHolder.webView.removeCallbacks(runnable);
                bannerViewHolder.webView.loadUrl("about:blank");
            }

        }
    }

    @Override
    public int getItemCount() {
        return bannerDataList == null ? 0 : bannerDataList.size();
    }

    public List<BannerMyData> getBannerDataList() {
        return bannerDataList;
    }

    public void removeAllBanner() {
        if (bannerDataList.isEmpty()) {
            return;
        }
        int totalSize = bannerDataList.size();
        Iterator<BannerMyData> iterator = bannerDataList.iterator();
        while (iterator.hasNext()) {
            BannerMyData bannerMyData = iterator.next();
            iterator.remove();
        }
        notifyItemRangeRemoved(0, totalSize);
    }

    public void updateAllShowBanner() {
        if (bannerDataList.isEmpty()) {
            return;
        }
        for (BannerMyData bannerMyData : bannerDataList) {
            bannerMyData.setStateBannerFragment(StateBannerFragment.StateFragment.SHOW_BANNER);
            bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_UNDEFINED);
        }
        notifyDataSetChanged();
    }

    public void stopVideo(){
        if (bannerDataList.isEmpty()) {
            return;
        }
        for (BannerMyData bannerMyData : bannerDataList) {
            bannerMyData.setStateBannerFragment(StateBannerFragment.StateFragment.SHOW_BANNER);
            bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_BREAK);
        }
        notifyDataSetChanged();
    }

    private void clickBannerItem(View view, BannerMyData bannerMyData) {
        mListener.OnClickItemBanner(bannerMyData);
    }

    private void setShowImageOrYoutube(BannerViewHolder bannerViewHolder, BannerMyData bannerMyData) {
        if (bannerMyData.getStateBannerFragment() == StateBannerFragment.StateFragment.SHOW_BANNER)
            setShowImageView(bannerViewHolder);
        else
            setShowVideo(bannerViewHolder);
    }

    private void setShowIconVideo(BannerViewHolder bannerViewHolder, BannerMyData bannerMyData) {
        if (bannerMyData.getStateBannerFragment() == StateBannerFragment.StateFragment.SHOW_BANNER) {
            bannerViewHolder.imageViewIconVideo.setVisibility(View.VISIBLE);
        } else {
            bannerViewHolder.imageViewIconVideo.setVisibility(View.GONE);
        }
    }

    private void setShowVideo(BannerViewHolder bannerViewHolder) {
        bannerViewHolder.bannerVideo.setVisibility(View.VISIBLE);
        bannerViewHolder.bannerImage.setVisibility(View.GONE);
    }

    private void setShowImageView(BannerViewHolder bannerViewHolder) {
        bannerViewHolder.bannerVideo.setVisibility(View.GONE);
        bannerViewHolder.bannerImage.setVisibility(View.VISIBLE);
    }

    private void setControlRunnable(final BannerViewHolder bannerViewHolder, BannerMyData bannerMyData) {
        if (runnable != null) {
            runnable = null;
        }
        runnable = new Runnable() {
            @Override
            public void run() {
                bannerViewHolder.webView.loadUrl("javascript:player.playVideo()");
            }
        };
    }

    private void setWebviewSource(final BannerViewHolder bannerViewHolder, final BannerMyData bannerMyData) throws IOException {
        Context context = bannerViewHolder.webView.getContext();
        setControlRunnable(bannerViewHolder, bannerMyData);

        HashMap<String, String> params = UrlParameter.getInstance().getQueryString(bannerMyData.getDirectUrl());

        InputStream is = context.getAssets().open("youtube.html");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        String str = new String(buffer);
        str = str.replace("www.youtube.com/embed/oQ5QWjGOfl8", "www.youtube.com/embed/" + params.get("v"));
        String mime = "text/html";
        String encoding = "utf-8";
        WebSettings mWebSettings = bannerViewHolder.webView.getSettings();
        mWebSettings.setJavaScriptEnabled(true);
        bannerViewHolder.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                bannerViewHolder.webView.removeCallbacks(runnable);
                bannerViewHolder.webView.postDelayed(runnable, 1500);
            }
        });

        MyJavaScriptYoutubeInterface myJavaScriptYoutubeInterface = new MyJavaScriptYoutubeInterface();
        myJavaScriptYoutubeInterface.setOnTask(new MyJavaScriptYoutubeInterface.OnTask() {
            @Override
            public void onUnstarted() {
                LogUtil.i("MyJavaScriptYoutubeInterface onUnstarted");
                bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_UNSTARTED);
            }

            @Override
            public void onEnded() {
                LogUtil.i("MyJavaScriptYoutubeInterface onEnded");
                bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_ENDED);
            }

            @Override
            public void onPlaying() {
                LogUtil.i("MyJavaScriptYoutubeInterface onPlaying");
                bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_PLAY);
            }

            @Override
            public void onPaused() {
                LogUtil.i("MyJavaScriptYoutubeInterface onPaused");
                bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_PAUSED);
            }

            @Override
            public void onBuffering() {
                LogUtil.i("MyJavaScriptYoutubeInterface onBuffering");
                bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_BUFFERING);
            }

            @Override
            public void onCued() {
                LogUtil.i("MyJavaScriptYoutubeInterface onCued");
                bannerMyData.setStateYoutube(StateBannerFragment.StateYoutube.YOUTUBE_CUED);
            }
        });
        bannerViewHolder.webView.setWebChromeClient(new WebChromeClient() {
        });
        bannerViewHolder.webView.addJavascriptInterface(myJavaScriptYoutubeInterface, "Android");
        mWebSettings.setAppCacheEnabled(true);
        bannerViewHolder.webView.getSettings().setUserAgentString("Android");
        bannerViewHolder.webView.loadDataWithBaseURL("https://www.youtube.com", str, mime, encoding, null);
    }


    private void clickControlShowLayOut(BannerViewHolder bannerViewHolder, BannerMyData bannerMyData) {
        if (bannerMyData.getStateBannerFragment() == StateBannerFragment.StateFragment.SHOW_BANNER) {
            if (bannerMyData.getStatusDirect() == StateBannerFragment.StatusDirect.YOUTUBE) {
                bannerMyData.setStateBannerFragment(StateBannerFragment.StateFragment.SHOW_VIDEO);
                setShowVideo(bannerViewHolder);
                try {
                    setWebviewSource(bannerViewHolder, bannerMyData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
