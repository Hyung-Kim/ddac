package com.example.taehyung.ddac.Adapter;

import android.content.Context;
import android.content.Intent;

import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taehyung.ddac.BuyActivity;
import com.example.taehyung.ddac.DDACMainActivity;
import com.example.taehyung.ddac.DataBase.DbOpenHelper;
import com.example.taehyung.ddac.Fragment.DdacFragment;
import com.example.taehyung.ddac.Item.ProductItem;
import com.example.taehyung.ddac.ProductInformationActivity;
import com.example.taehyung.ddac.R;

import java.util.ArrayList;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<ProductItem> products;
    Context context;

    public RecyclerViewAdapter(ArrayList<ProductItem> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.btnBuy.setOnClickListener(buyClickListener);
        holder.imageView.setOnClickListener(productClickListener);
        holder.imageView.setImageResource(products.get(position).img);
        holder.tvTitle.setText(products.get(position).title);
        holder.tvType.setText(products.get(position).type);
        holder.tvTime.setText(products.get(position).time);
        holder.tvDistance.setText(products.get(position).distance);
        holder.tvAccmulate.setText(products.get(position).accmulate);
    }

    // Return the size of your dataset (invoked by the layout manager)
    View.OnClickListener buyClickListener = (View v) -> {
        BottomNavigationView bottomNavigationView = (BottomNavigationView)((DDACMainActivity)v.getContext()).findViewById(R.id.navigation);
        bottomNavigationView.setSelectedItemId(R.id.action_item3);
        DbOpenHelper DbOpenHelper = new DbOpenHelper(v.getContext());
        DbOpenHelper.open();
    };
    View.OnClickListener productClickListener = (v) -> {
        Intent intent = new Intent(context, ProductInformationActivity.class);
        context.startActivity(intent);
    };
    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tvTitle;
        public TextView tvType;
        public TextView tvTime;
        public TextView tvDistance;
        public TextView tvAccmulate;
        public Button btnBuy;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvType = (TextView) view.findViewById(R.id.tvType);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            tvDistance = (TextView) view.findViewById(R.id.tvDistance);
            tvAccmulate = (TextView) view.findViewById(R.id.tvAccmulate);
            btnBuy = (Button) view.findViewById(R.id.btnBuy);
        }
    }
}
