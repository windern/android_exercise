package com.windern.test.androidexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.scrollView)
    ObservableScrollView mScrollView;

    @BindView(R.id.container_flow)
    View mLlFlow;

    @BindView(R.id.tv_whole_title)
    View tvWholeTitle;

    @BindView(R.id.view_scroll_title1)
    View mViewScrollTitle1;

    @BindView(R.id.view_flow_title1)
    View mViewFlowTitle1;

    @BindView(R.id.view_scroll_title2)
    View mViewScrollTitle2;

    @BindView(R.id.view_flow_title2)
    View mViewFlowTitle2;

    @BindView(R.id.hscroll_flow)
    ObservableHorizontalScrollView mHscrollFlow;

    @BindView(R.id.hscroll_content)
    ObservableHorizontalScrollView mHscrollContent;

    @BindView(R.id.view_flow_fixed_title1)
    View mViewFlowFixedTitle1;

    @BindView(R.id.view_flow_fixed_title2)
    View mViewFlowFixedTitle2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initScorll();
        initHscroll();
    }

    public void initScorll() {
        mScrollView.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                int outWholeTitleScrollY = y - tvWholeTitle.getHeight();

                if (outWholeTitleScrollY > mViewScrollTitle2.getTop()) {
                    mLlFlow.setTop(0);
                    mViewFlowTitle2.setVisibility(View.VISIBLE);
                    mViewFlowFixedTitle2.setVisibility(View.VISIBLE);
                    mViewFlowTitle1.setVisibility(View.GONE);
                    mViewFlowFixedTitle1.setVisibility(View.GONE);
                } else if (outWholeTitleScrollY > mViewScrollTitle2.getTop() - mViewScrollTitle1.getHeight()) {
                    mLlFlow.setTop(mViewScrollTitle2.getTop() - mViewScrollTitle1.getHeight() - outWholeTitleScrollY);
                    mViewFlowTitle2.setVisibility(View.GONE);
                    mViewFlowFixedTitle2.setVisibility(View.GONE);
                    mViewFlowTitle1.setVisibility(View.VISIBLE);
                    mViewFlowFixedTitle1.setVisibility(View.VISIBLE);
                } else if (outWholeTitleScrollY - tvWholeTitle.getTop() > mViewScrollTitle1.getTop()) {
                    mLlFlow.setTop(0);
                    mViewFlowTitle2.setVisibility(View.GONE);
                    mViewFlowFixedTitle2.setVisibility(View.GONE);
                    mViewFlowTitle1.setVisibility(View.VISIBLE);
                    mViewFlowFixedTitle1.setVisibility(View.VISIBLE);
                } else {
                    mLlFlow.setTop(0);
                    mViewFlowTitle2.setVisibility(View.GONE);
                    mViewFlowFixedTitle2.setVisibility(View.GONE);
                    mViewFlowTitle1.setVisibility(View.GONE);
                    mViewFlowFixedTitle1.setVisibility(View.GONE);
                }
            }
        });
    }

    public void initHscroll() {
        mHscrollFlow.setScrollViewListener(new HorizontalScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                mHscrollContent.setScrollX(x);
            }
        });

        mHscrollContent.setScrollViewListener(new HorizontalScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableHorizontalScrollView scrollView, int x, int y, int oldx, int oldy) {
                mHscrollFlow.setScrollX(x);
            }
        });
    }
}
