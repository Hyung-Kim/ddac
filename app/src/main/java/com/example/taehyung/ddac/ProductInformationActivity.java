package com.example.taehyung.ddac;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taehyung.ddac.DataBase.DbOpenHelper;
import com.example.taehyung.ddac.Item.BoughtProduct;

import java.util.List;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class ProductInformationActivity extends AppCompatActivity {
    Button btnBack;
    Button btnVideoMoreDetails;
    Button btnBuy;
    private Handler handler = new Handler();
    ImageView prepareImage1;
    ImageView prepareImage3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
        resourceInit();
        btnBack.setOnClickListener(backClickListener);
        btnVideoMoreDetails.setOnClickListener(moreVideoClickListener);
        btnBuy.setOnClickListener(buyClickListener);
        prepareImage1.setOnClickListener(prepareImageClickListener);
        prepareImage3.setOnClickListener(prepareImageClickListener);
    }
    View.OnClickListener backClickListener = (v) -> this.finish();
    View.OnClickListener moreVideoClickListener = (v) -> Toast.makeText(this,"등록되어 있는 동영상이 없습니다.",Toast.LENGTH_SHORT).show();
    View.OnClickListener prepareImageClickListener = (v) -> Toast.makeText(this,"동영상을 불러올 수 없습니다.",Toast.LENGTH_SHORT).show();
    View.OnClickListener buyClickListener = (v) -> {
        DbOpenHelper DbOpenHelper = new DbOpenHelper(v.getContext());
        DbOpenHelper.open(v.getContext());
        List<BoughtProduct> boughtProducts = DbOpenHelper.getBoughtProductList();
        if (boughtProducts.size() == 1)
            Toast.makeText(v.getContext(), "이미 1개의 패키지를 구매하셨습니다.", Toast.LENGTH_SHORT).show();
        else {
            this.finish();
            new Thread(() -> {
                try{
                Thread.sleep(600);}catch (Exception e){}
                handler.post(()->{DDACMainActivity.bottomNavigationView.setSelectedItemId(R.id.action_item3);
                    DbOpenHelper.addProducts(1, 1);});
            }).start();
        }
    };

    public void resourceInit(){
        btnBack = (Button)findViewById(R.id.backButton);
        btnVideoMoreDetails = (Button)findViewById(R.id.moreDetailsOfVideo);
        btnBuy = (Button)findViewById(R.id.btnBuy);
        prepareImage1 = (ImageView)findViewById(R.id.video_prepare_1);
        prepareImage3 = (ImageView)findViewById(R.id.video_prepare_3);
    }
}
