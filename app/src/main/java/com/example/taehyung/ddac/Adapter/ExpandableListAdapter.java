package com.example.taehyung.ddac.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taehyung.ddac.DataBase.DbOpenHelper;

import com.example.taehyung.ddac.Item.BoughtProduct;
import com.example.taehyung.ddac.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by TaeHyungKim on 2017-12-10.
 */

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    public static Context context;
    private List<String> ParentItem;
    private HashMap<String, Integer> ChildItem;
    public static DbOpenHelper DbOpenHelper;
    public static List<BoughtProduct> boughtProducts;

    public ExpandableListAdapter(Context context, List<String> ParentItem,
                                 HashMap<String, Integer> ChildItem) {
        this.context = context;
        this.ParentItem = ParentItem;
        this.ChildItem = ChildItem;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.ChildItem.get(this.ParentItem.get(listPosition));
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
        return this.ParentItem.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.ParentItem.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.parent_item, null);
        }
        TextView listTitleTextView = (TextView) convertView.findViewById(R.id.listTitle);
        ImageView typeImageView = (ImageView)convertView.findViewById(R.id.listType);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        ImageView upDownImageView = (ImageView)convertView.findViewById(R.id.expandableImage);
        typeImageView.setImageResource(R.drawable.quest_main_icon);
        if(isExpanded)
            upDownImageView.setImageResource(R.drawable.down_image);
        else
            upDownImageView.setImageResource(R.drawable.up_image);
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


    public static HashMap<String, Integer> getData(Context ctx) {
        HashMap<String, Integer> ParentItem = new HashMap<String, Integer>();
        Integer child1 = new Integer(R.drawable.first_question);
        Integer child2 = new Integer(R.drawable.second_question);
        Integer child3 = new Integer(R.drawable.three_question);
        Integer child4 = new Integer(R.drawable.four_question);
        Integer child5 = new Integer(R.drawable.five_question);
        Integer child6 = new Integer(R.drawable.six_question);

        DbOpenHelper = new DbOpenHelper(context);
        DbOpenHelper.open(ctx);
        boughtProducts = DbOpenHelper.getBoughtProductList();
        if(boughtProducts.size() != 0) {
            BoughtProduct boughtProduct = boughtProducts.get(0);
            if (boughtProduct.getLevel() <= 1) {
                ParentItem.put("오프닝 영상을 감상하세요.", child1);
            }
            if (boughtProduct.getLevel() <= 2) {
                ParentItem.put("Detail. \\n 다음 단서가 가리키는 장소로 이동하세요.", child2);
            }
            if (boughtProduct.getLevel() <= 3) {
                ParentItem.put("추천식당에서 식사를 하세요(10% 할인)", -1);
            }
            if (boughtProduct.getLevel() <= 4) {
                ParentItem.put("퀘스트 '성서로운 돌'이(가) 생성되었습니다.", child3);
            }
            if (boughtProduct.getLevel() <= 5) {
                ParentItem.put("퀘스트 '조력자 컨택'이 생성되었습니다.", child4);
            }
            if (boughtProduct.getLevel() <= 6) {
                ParentItem.put("퀘스트 '어둠 속 빛 한줄기'이(가) 생성되었습니다.", child5);
            }
            if (boughtProduct.getLevel() <= 7) {
                ParentItem.put("퀘스트 '나라의 운명'이(가) 생성되었습니다.", child6);
            }
            return ParentItem;
        }else
            ParentItem.put("오프닝 영상을 감상하세요.", child1);
            return ParentItem;
    }
}

