package com.example.taehyung.ddac.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taehyung.ddac.DataBase.DbOpenHelper;
import com.example.taehyung.ddac.Item.BoughtProduct;
import com.example.taehyung.ddac.Item.ParentItem;
import com.example.taehyung.ddac.Item.TypeItem;
import com.example.taehyung.ddac.R;

import java.util.List;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public static Context context;
    private List<ParentItem> contents;
    public com.example.taehyung.ddac.DataBase.DbOpenHelper DbOpenHelper;
    public List<BoughtProduct> boughtProducts;

    public ExpandableListAdapter(Context context, List<com.example.taehyung.ddac.Item.ParentItem> contents) {
        this.context = context;
        this.contents = contents;
        DbOpenHelper = new DbOpenHelper(context);
        DbOpenHelper.open(context);
        boughtProducts = DbOpenHelper.getBoughtProductList();
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.contents.get(listPosition).getResId();
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final int expandedListText = (Integer)getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.child_item, null);
        }
        ImageView img1 = (ImageView) convertView.findViewById(R.id.item1);
        img1.setImageResource(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.contents.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.contents.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String listTitle = this.contents.get(listPosition).getTitle();
        int type = this.contents.get(listPosition).getType();
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_item, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        ImageView typeImageView = (ImageView)convertView.findViewById(R.id.listType);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        if(boughtProducts.size() != 0 && (listPosition+1 < boughtProducts.get(0).getLevel())) {
            listTitleTextView.setPaintFlags((listTitleTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG));
            listTitleTextView.setTextColor(0xffa0a0a0);
        }else {
            listTitleTextView.setTextColor(0xffff0000);
            if((listTitleTextView.getPaintFlags() & Paint.STRIKE_THRU_TEXT_FLAG) == Paint.STRIKE_THRU_TEXT_FLAG)
                listTitleTextView.setPaintFlags((listTitleTextView.getPaintFlags() ^ Paint.STRIKE_THRU_TEXT_FLAG));
        }
        listTitleTextView.setText(listTitle);
        ImageView upDownImageView = (ImageView)convertView.findViewById(R.id.expandableImage);
        if(type == TypeItem.MAIN_CONTENTS)
            typeImageView.setImageResource(R.drawable.quest_main_icon);
        else
            typeImageView.setImageResource(R.drawable.quest_service_icon);

        if(isExpanded) {
            upDownImageView.setImageResource(R.drawable.down_image);
            convertView.setPadding(0,0,0,0);
        }
        else {
            upDownImageView.setImageResource(R.drawable.up_image);
            convertView.setPadding(0,0,0,50);
        }
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}

