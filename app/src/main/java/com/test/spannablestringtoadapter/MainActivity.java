package com.test.spannablestringtoadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.test.spannablestringtoadapter.adapter.Adapter;
import com.test.spannablestringtoadapter.bean.WordBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<WordBean> mModels;
    private Adapter mAdapter;
    private RecyclerView mRecyclerView;

    private EditText mEtKey;
    private TextView mTvClear;
    private View mLlSearch;

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

        mAdapter = new Adapter(new ArrayList<WordBean>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
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
    }

    private void initListener() {
        mTvClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtKey.getText().clear();
            }
        });
    }


}
