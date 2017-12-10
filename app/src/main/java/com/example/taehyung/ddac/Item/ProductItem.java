package com.example.taehyung.ddac.Item;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class ProductItem {
    public int img;
    public String title;
    public String time;
    public String type;
    public String distance;
    public String accmulate;

    public ProductItem(int img, String title, String time, String type, String distance, String accmulate) {
        this.img = img;
        this.title = title;
        this.time = time;
        this.type = type;
        this.accmulate = accmulate;
        this.distance = distance;
    }
}
