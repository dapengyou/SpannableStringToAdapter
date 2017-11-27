package com.test.spannablestringtoadapter.bean;

import java.io.Serializable;

/**
 * Created by lady_zhou on 2017/11/27.
 */

public class WordBean implements Serializable {
    private long mId;
    private int mRank;
    private String mWord;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public int getRank() {
        return mRank;
    }

    public void setRank(int rank) {
        mRank = rank;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String word) {
        mWord = word;
    }
}
