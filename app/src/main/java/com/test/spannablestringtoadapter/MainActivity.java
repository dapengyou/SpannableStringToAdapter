package com.test.spannablestringtoadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.test.spannablestringtoadapter.adapter.Adapter;
import com.test.spannablestringtoadapter.bean.WordBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<WordBean> mModels;
    private Adapter mAdapter;
    private RecyclerView mRecyclerView;
    private SmartRefreshLayout mSmartRefreshLayout;

    private EditText mEtKey;
    private TextView mTvClear;
    private View mLlSearch;

    private static final int HIDE_THRESHOLD = 20;
    private int scrolledDistance = 0;
    private boolean controlsVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mEtKey = findViewById(R.id.et_search_key);
        mTvClear = findViewById(R.id.tv_clear);
        mLlSearch = findViewById(R.id.ll_search);
        mRecyclerView = findViewById(R.id.rv_list);
        mSmartRefreshLayout = findViewById(R.id.srl_list);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initData() {
        mModels = new ArrayList<>();
        String[] words = getResources().getStringArray(R.array.words);
        for (int i = 0; i < words.length; i++) {
            WordBean wordBean = new WordBean();
            wordBean.setId(i);
            wordBean.setRank(i + 1);
            wordBean.setWord(words[i]);
            mModels.add(wordBean);
        }

        mAdapter = new Adapter(R.layout.item_word, mModels);
        mRecyclerView.setAdapter(mAdapter);
        View headview = LayoutInflater.from(this).inflate(R.layout.item_search_head, null);
        mAdapter.addHeaderView(headview);
    }

    private void initListener() {
        mTvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtKey.getText().clear();
            }
        });
        //屏蔽下拉刷新
        mSmartRefreshLayout.setEnableRefresh(false);
        //list滚动，加载消失显示动画
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (scrolledDistance > HIDE_THRESHOLD && controlsVisible) {
                    hideViews();
                    controlsVisible = false;
                    scrolledDistance = 0;
                } else if (scrolledDistance < -HIDE_THRESHOLD && !controlsVisible) {
                    showViews();
                    controlsVisible = true;
                    scrolledDistance = 0;
                }
                if ((controlsVisible && dy > 0) || (!controlsVisible && dy < 0)) {
                    scrolledDistance += dy;
                }
            }
        });
    }

    private void hideViews() {
        mLlSearch.animate().translationY(-mLlSearch.getHeight()).setInterpolator(new AccelerateInterpolator(2));
    }

    private void showViews() {
        mLlSearch.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    }


}
