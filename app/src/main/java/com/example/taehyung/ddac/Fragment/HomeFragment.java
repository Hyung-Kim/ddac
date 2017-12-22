package com.example.taehyung.ddac.Fragment;

/**
 * Created by TaeHyungKim on 2017-12-16.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taehyung.ddac.Adapter.LoopPagerAdapter;
import com.example.taehyung.ddac.Adapter.RecyclerViewAdapter;
import com.example.taehyung.ddac.Item.ProductItem;
import com.example.taehyung.ddac.R;
import com.example.taehyung.ddac.RollView.RollPagerView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RollPagerView rollPagerView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ProductItem> products;
    private View v;
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_home, container, false);
        resourceInit();
        recyclerViewInit();
        registerAdapter();
        return v;
    }
    void recyclerViewInit(){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        products = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(products, getActivity());

        products.add(new ProductItem(R.drawable.product_list_2, "잃어버린 것을 찾아서", "약 4시간 소요", "역사, 추리, 스릴러", "총 이동거리 8km", "누적 100,812명","어느날 친구의 급박한 전화를 받고 해당장소로 가지만 총격소리와 함께 친구를 잃고 친구의 마지막 유언을 따라가는데..."));
        products.add(new ProductItem(R.drawable.product_list_3, "통곡의 미루나무", "약 9시간 소요", "스릴러, 서스펜스, 반전", "총 이동거리 8.8km", "누적 1,000,812명","미구현"));
        for(int i=0;i<50;i++) {
            products.add(new ProductItem(R.drawable.product_list_1, "누명", "약 8시간 소요", "스릴러, 서스펜스, 반전", "총 이동거리 12.8km", "누적 4,812명","미구현"));
        }
    }
    void registerAdapter(){
        rollPagerView.setAdapter(new ImageLoopAdapter(rollPagerView));
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    void resourceInit(){
        rollPagerView = (RollPagerView)v.findViewById(R.id.view_pager);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
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

