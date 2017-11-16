package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import bannerengine.sand.mimo.th.co.libbanner.R;
import bannerengine.sand.mimo.th.co.libbanner.global.Config;
import bannerengine.sand.mimo.th.co.libbanner.global.LogUtil;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.adapter.BannerAdapter;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.help.CirclePagerIndicatorDecoration;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

/**
 * Created by orapong on 11/9/2017 AD.
 */

public class LayoutAllBanner extends LinearLayout implements LayoutAllBannerContractor.View, BannerAdapter.OnListener {
    private LayoutAllBannerContractor.Action mPresenter;
    private ItemTouchHelper itemTouchHelper;
    private RecyclerView recyclerview;
    private BannerAdapter bannerAdapter;
    private OnListener mListener;
    private int runPosition = 0;
    private boolean breakAutoRun = false;
    private TextView breakview;

    private Runnable runnable = new Runnable() {
        boolean flag = true;
        @Override
        public void run() {
            if (Config.getInstance().getAutoRun() && getBreakAutoRun() == false) {
                if (runPosition < bannerAdapter.getItemCount()) {
                    if (runPosition == bannerAdapter.getItemCount() - 1) {
                        flag = false;
                    } else if (runPosition == 0) {
                        flag = true;
                    }
                    if (flag) {
                        runPosition++;
                    } else {
                        runPosition = 0;
                    }
                    recyclerview.smoothScrollToPosition(runPosition);
                    autoRun();
                }
            }else{
                autoRun();
            }
        }
    };

    public interface OnListener {
        void OnClickItemBanner(BannerMyData bannerMyData);
    }

    public LayoutAllBanner setListener(OnListener mListener) {
        this.mListener = mListener;
        return this;
    }

    public LayoutAllBanner(Context context) {
        super(context);
        init();
    }

    public LayoutAllBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LayoutAllBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == View.VISIBLE){
            //onResume called
        } else {
            // onPause() called
            if(bannerAdapter!=null) { bannerAdapter.stopVideo();}
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (hasWindowFocus){
            //onresume() called
        } else {
            // onPause() called
            if(bannerAdapter!=null) { bannerAdapter.stopVideo();}
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        // onDestroy() called
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private void init() {
        mPresenter = new LayoutAllBannerPresenter(LayoutAllBanner.this);
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View v = mInflater.inflate(R.layout.layout_banner_pagerview, this, true);
        recyclerview = (RecyclerView) v.findViewById(R.id.recyclerview);
        breakview = (TextView)v.findViewById(R.id.breakview);
        breakview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                bannerAdapter.stopVideo();
            }
        });

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerview.setHasFixedSize(true);
        recyclerview.addItemDecoration(
                new CirclePagerIndicatorDecoration(
                        ContextCompat.getColor(getContext(), R.color.my_gray1),
                        ContextCompat.getColor(getContext(), R.color.my_gray2)
                )
        );
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LogUtil.i("The RecyclerView is not currently scrolling"+Integer.toString(newState));
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    breakAutoRun = false;
                    bannerAdapter.updateAllShowBanner();
                } else if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    breakAutoRun = true;
                }
            }
        });

    }


    @Override
    public void setUpDataItem(BannerData bannerData) {
        bannerAdapter = new BannerAdapter(bannerData.getData());
        recyclerview.setAdapter(createAnimationInAdapter(bannerAdapter));
        recyclerview.setItemAnimator(createScaleOutAnimationOutAdapter());
        bannerAdapter.setListener(this);
        autoRun();
    }

    private ScaleInAnimationAdapter createAnimationInAdapter(RecyclerView.Adapter adapter) {
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        scaleInAnimationAdapter.setInterpolator(new OvershootInterpolator(2f));
        scaleInAnimationAdapter.setDuration(0);
        return scaleInAnimationAdapter;
    }

    private ScaleInBottomAnimator createScaleOutAnimationOutAdapter() {
        ScaleInBottomAnimator animator = new ScaleInBottomAnimator(new DecelerateInterpolator());
        animator.setRemoveDuration(0);
        return animator;
    }

    @Override
    public void OnClickItemBanner(BannerMyData bannerMyData) {
        mPresenter.OnClickItemBanner(bannerMyData);
        mListener.OnClickItemBanner(bannerMyData);

    }

    private boolean getBreakAutoRun() {
        return breakAutoRun;
    }

    @Override
    public void setBreakAutoRun(boolean breakAutoRun) {
        this.breakAutoRun = breakAutoRun;
    }

    private void autoRun() {
        recyclerview.removeCallbacks(runnable);
        recyclerview.postDelayed(runnable, Config.getInstance().getSpeedScroll());
    }


    public LayoutAllBanner setUp(final String chanelId, final String categoryId, final String limit, final boolean directUrl) {
        recyclerview.post(new Runnable() {
            @Override
            public void run() {
                Config.getInstance().setSize16to9(getContext());
                mPresenter.callService(chanelId, categoryId, limit, directUrl);
            }
        });
        return this;
    }

    @Override
    public LayoutAllBanner removeBannerAll() {
        if (bannerAdapter != null) {
            bannerAdapter.removeAllBanner();
        }
        return this;
    }

    public LayoutAllBanner setBase_url(String base_url){
        Config.getInstance().setBase_url(base_url);
        return this;
    }

    public LayoutAllBanner setAutoRun(Boolean autoRun){
        Config.getInstance().setAutoRun(autoRun);
        return this;
    }

    public LayoutAllBanner setSpeedScroll(int speedScroll){
        Config.getInstance().setSpeedScroll(speedScroll);
        return this;
    }

    public LinearLayout setPlaceholder(int placeholder) {
        Config.getInstance().setPlaceholder(placeholder);
        return this;
    }

}



