package bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.RelativeLayout;

import bannerengine.sand.mimo.th.co.libbanner.R;
import bannerengine.sand.mimo.th.co.libbanner.global.Config;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerData;
import bannerengine.sand.mimo.th.co.libbanner.task.network.model.banner.BannerMyData;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.CirclePagerIndicatorDecoration;
import bannerengine.sand.mimo.th.co.libbanner.ui.layoutbanner.recyclerview.adapter.BannerAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import jp.wasabeef.recyclerview.animators.ScaleInBottomAnimator;

/**
 * Created by orapong on 11/9/2017 AD.
 */

public class LayoutAllBanner extends RelativeLayout implements LayoutAllBannerContractor.View, BannerAdapter.OnListener {
    private LayoutAllBannerContractor.Action mPresenter;
    private RecyclerView recyclerview;
    private BannerAdapter bannerAdapter;
    private OnListener mListener;
    private  int runPosition = 0;

    public interface OnListener {
        void OnClickItemBanner(BannerMyData bannerMyData);
    }

    public void setListener(OnListener mListener) {
        this.mListener = mListener;
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
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    private void init() {
        LayoutInflater mInflater = LayoutInflater.from(getContext());
        View v = mInflater.inflate(R.layout.layout_banner_pagerview, this, true);
        recyclerview = (RecyclerView) v.findViewById(R.id.recyclerview);
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
        autoRun();
    }


    public void setUp(final String chanelId, final String categoryId, final String limit, final boolean directUrl) {
        recyclerview.post(new Runnable() {
            @Override
            public void run() {
                Config.getInstance().setSize16to9(getContext());
                mPresenter = new LayoutAllBannerPresenter(LayoutAllBanner.this);
                mPresenter.callService(chanelId, categoryId, limit, directUrl);
            }
        });
    }

    public void removeBannerAll() {
        if (bannerAdapter != null) {
            bannerAdapter.removeAllBanner();
        }
    }

    @Override
    public void setUpDataItem(BannerData bannerData) {
        bannerAdapter = new BannerAdapter(bannerData.getData());
        recyclerview.setAdapter(createAnimationInAdapter(bannerAdapter));
        recyclerview.setItemAnimator(createScaleOutAnimationOutAdapter());
        bannerAdapter.setListener(this);
    }

    private ScaleInAnimationAdapter createAnimationInAdapter(RecyclerView.Adapter adapter) {
        ScaleInAnimationAdapter scaleInAnimationAdapter = new ScaleInAnimationAdapter(adapter);
        scaleInAnimationAdapter.setFirstOnly(false);
        scaleInAnimationAdapter.setInterpolator(new OvershootInterpolator(2f));
        scaleInAnimationAdapter.setDuration(200);
        return scaleInAnimationAdapter;
    }

    private ScaleInBottomAnimator createScaleOutAnimationOutAdapter() {
        ScaleInBottomAnimator animator = new ScaleInBottomAnimator(new DecelerateInterpolator());
        animator.setRemoveDuration(0);
        return animator;
    }

    @Override
    public void OnClickItemBanner(BannerMyData bannerMyData) {

    }

    private void autoRun() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            boolean flag = true;
            @Override
            public void run() {
                if (Config.getInstance().autoRun) {
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
                        handler.postDelayed(this, Config.getInstance().speedScroll);
                    }
                }
            }
        };
        handler.postDelayed(runnable, Config.getInstance().speedScroll);
    }
}



