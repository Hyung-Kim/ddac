package com.example.taehyung.ddac.Item;

/**
 * Created by TaeHyungKim on 2017-12-17.
 */

public class BoughtProduct {
    int id;
    int product_id;
    String buy_time;
    int level;

    public BoughtProduct(int id, int product_id, String buy_time, int level){
        this.id = id;
        this.product_id = product_id;
        this.buy_time = buy_time;
        this.level = level;
    }
    public int getId() {
        return id;
    }
    public int getProduct_id() {
        return product_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public void setBuy_time(String buy_time) {
        this.buy_time = buy_time;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getBuy_time() {
        return buy_time;
    }

    public int getLevel() {
        return level;
    }
}
