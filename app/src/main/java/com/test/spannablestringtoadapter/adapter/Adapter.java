package com.test.spannablestringtoadapter.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.test.spannablestringtoadapter.R;
import com.test.spannablestringtoadapter.bean.WordBean;

import java.util.List;

/**
 * Created by lady_zhou on 2017/11/27.
 */

public class Adapter extends BaseQuickAdapter<WordBean, BaseViewHolder> {
    public Adapter(List<WordBean> data) {
        super(R.layout.item_word, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WordBean item) {
        helper.setText(R.id.tv_text, item.getWord());
    }
}
