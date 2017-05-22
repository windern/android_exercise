package com.windern.test.androidexercise;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.scrollView)
    ObservableScrollView mScrollView;

    @BindView(R.id.ll_flow)
    LinearLayout mLlFlow;

    @BindView(R.id.tv_whole_title)
    View tvWholeTitle;

    @BindView(R.id.view_scroll_title1)
    View mTvScrollTitle1;

    @BindView(R.id.view_flow_title1)
    View mTvFlowTitle1;

    @BindView(R.id.view_scroll_title2)
    View mTvScrollTitle2;

    @BindView(R.id.view_flow_title2)
    View mTvFlowTitle2;

    @BindView(R.id.hscroll_flow)
    HorizontalScrollView hscrollFlow;

    @BindView(R.id.hscroll_content)
    HorizontalScrollView hscrollContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initScorll();
    }

    public void initScorll() {
        mScrollView.setScrollViewListener(new ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                int outWholeTitleScrollY = y - tvWholeTitle.getHeight();

                if (outWholeTitleScrollY > mTvScrollTitle2.getTop()) {
                    mLlFlow.setTop(0);
                    mTvFlowTitle2.setVisibility(View.VISIBLE);
                    mTvFlowTitle1.setVisibility(View.GONE);
                } else if (outWholeTitleScrollY > mTvScrollTitle2.getTop() - mTvScrollTitle1.getHeight()) {
                    mLlFlow.setTop(mTvScrollTitle2.getTop() - mTvScrollTitle1.getHeight() - outWholeTitleScrollY);
                    mTvFlowTitle2.setVisibility(View.GONE);
                    mTvFlowTitle1.setVisibility(View.VISIBLE);
                } else if (outWholeTitleScrollY - tvWholeTitle.getTop() > mTvScrollTitle1.getTop()) {
                    mLlFlow.setTop(0);
                    mTvFlowTitle2.setVisibility(View.GONE);
                    mTvFlowTitle1.setVisibility(View.VISIBLE);
                } else {
                    mLlFlow.setTop(0);
                    mTvFlowTitle2.setVisibility(View.GONE);
                    mTvFlowTitle1.setVisibility(View.GONE);
                }
            }
        });
    }
}
