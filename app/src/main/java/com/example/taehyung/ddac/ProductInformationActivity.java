package com.example.taehyung.ddac;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class ProductInformationActivity extends AppCompatActivity {
    Button btnBack;
    Button btnVideoMoreDetails;
    Button btnActorMoreDetilas;
    Button btnBuy;
    ImageView prepareImage1;
    ImageView prepareImage2;
    ImageView prepareImage3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_information);
        resourceInit();
        btnBack.setOnClickListener(backClickListener);
        btnVideoMoreDetails.setOnClickListener(moreVideoClickListener);
        btnActorMoreDetilas.setOnClickListener(moreActorClickListener);
        btnBuy.setOnClickListener(buyClickListener);
        prepareImage1.setOnClickListener(prepareImageClickListener);
        prepareImage2.setOnClickListener(prepareImageClickListener);
        prepareImage3.setOnClickListener(prepareImageClickListener);
    }
    View.OnClickListener backClickListener = (v) -> this.finish();
    View.OnClickListener moreVideoClickListener = (v) -> Toast.makeText(this,"등록되어 있는 동영상이 없습니다.",Toast.LENGTH_SHORT).show();
    View.OnClickListener moreActorClickListener = (v) -> Toast.makeText(this,"등록되어 있는 배우가 없습니다.",Toast.LENGTH_SHORT).show();
    View.OnClickListener prepareImageClickListener = (v) -> Toast.makeText(this,"동영상을 불러올 수 없습니다.",Toast.LENGTH_SHORT).show();
    View.OnClickListener buyClickListener = (v) -> {
        Intent intent = new Intent(this, BuyActivity.class);
        startActivity(intent);
    };
    public void resourceInit(){
        btnBack = (Button)findViewById(R.id.backButton);
        btnVideoMoreDetails = (Button)findViewById(R.id.moreDetailsOfVideo);
        btnActorMoreDetilas = (Button)findViewById(R.id.moreDetailsOfActor);
        btnBuy = (Button)findViewById(R.id.btnBuy);
        prepareImage1 = (ImageView)findViewById(R.id.video_prepare_1);
        prepareImage2 = (ImageView)findViewById(R.id.video_prepare_2);
        prepareImage3 = (ImageView)findViewById(R.id.video_prepare_3);
    }
}
