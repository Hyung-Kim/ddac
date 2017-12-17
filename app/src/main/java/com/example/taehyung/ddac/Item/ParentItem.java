package com.example.taehyung.ddac.Item;

/**
 * Created by TaeHyungKim on 2017-12-18.
 */

public class ParentItem{
    public String title;
    public int resId;
    public int type;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {

        return type;
    }

    public ParentItem(String title, int resId, int type){
        this.title = title;
        this.resId = resId;
        this.type = type;

    }
    public String getTitle() {
        return title;
    }

    public int getResId() {
        return resId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
