package com.example.taehyung.ddac.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.taehyung.ddac.R;
import com.example.taehyung.ddac.RollView.RollPagerView;

/**
 * Created by TaeHyungKim on 2017-12-16.
 */

public class ImageLoopAdapter extends LoopPagerAdapter {
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