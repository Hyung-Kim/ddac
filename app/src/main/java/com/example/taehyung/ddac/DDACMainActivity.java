package com.example.taehyung.ddac;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taehyung.ddac.Adapter.LoopPagerAdapter;
import com.example.taehyung.ddac.Adapter.RecyclerViewAdapter;
import com.example.taehyung.ddac.Item.ProductItem;
import com.example.taehyung.ddac.RollView.RollPagerView;

import java.util.ArrayList;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class DDACMainActivity extends AppCompatActivity {
    private RollPagerView rollPagerView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProductItem> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddac_main);
        resourceInit();
        recyclerViewInit();
        registerAdapter();
    }
    void recyclerViewInit(){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        products = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(products, this);
        for(int i=0;i<100;i++)
            products.add(new ProductItem(R.drawable.product_list_1, "누명", "약 8시간 소요", "스릴러, 서스펜스, 반전", "총 이동거리 12.8km", "누적 4,812명"));
    }
    void registerAdapter(){
        rollPagerView.setAdapter(new ImageLoopAdapter(rollPagerView));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    void resourceInit(){
        rollPagerView = (RollPagerView)findViewById(R.id.view_pager);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
    }
    private class ImageLoopAdapter extends LoopPagerAdapter {
        int[] imgs = new int[]{
                R.drawable.advertisement_list_1,
                R.drawable.advertisement_list_1,
                R.drawable.advertisement_list_1,
                R.drawable.advertisement_list_1,
                R.drawable.advertisement_list_1,
        };
        public ImageLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }
        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setImageResource(imgs[position]);
            return view;
        }
        @Override
        public int getRealCount() {
            return imgs.length;
        }
    }
}
