package com.example.taehyung.ddac.Fragment;

/**
 * Created by TaeHyungKim on 2017-12-16.
 */


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taehyung.ddac.Adapter.ExpandableListAdapter;
import com.example.taehyung.ddac.DDACMainActivity;
import com.example.taehyung.ddac.DataBase.DbOpenHelper;
import com.example.taehyung.ddac.GoogleMapActivity;
import com.example.taehyung.ddac.Item.BoughtProduct;
import com.example.taehyung.ddac.Item.ParentItem;
import com.example.taehyung.ddac.Item.TypeItem;
import com.example.taehyung.ddac.MainActivity;
import com.example.taehyung.ddac.R;
import com.google.android.gms.maps.GoogleMap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DdacFragment extends Fragment {
    private TextView remainTime;
    private View v;
    public DbOpenHelper DbOpenHelper;
    public List<BoughtProduct> boughtProducts;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<ParentItem> expandableList;
    private String startTime = null;

    public static DdacFragment newInstance() {
        DdacFragment fragment = new DdacFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //No call for super(). Bug on API Level > 11.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ddac, container, false);
        mapButtonInit();
        remainTime = v.findViewById(R.id.remainTime);
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        expandableList = getData(getContext());
        if(expandableList==null)
            return v;
        expandableListAdapter = new ExpandableListAdapter(v.getContext(), expandableList);
        expandableListView.setAdapter(expandableListAdapter);
        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        expandableListView.setIndicatorBounds(width-100, width);
        if(boughtProducts.size() != 0) {
            Log.d("KTH", "time : " +  boughtProducts.get(0).getBuy_time());
            startTime = boughtProducts.get(0).getBuy_time().substring(11, 19);
        }
        else
            startTime =null;
        if(startTime != null)
            updateTimeEverySecond();
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition == 0 && boughtProducts.get(0).getLevel() == 1){
                    DbOpenHelper.levelUp(2);
                    Toast.makeText(getActivity(),"오프닝 영상 보기를 완료하였습니다. ",Toast.LENGTH_LONG).show();
                    BottomNavigationView bottomNavigationView = (BottomNavigationView) ((DDACMainActivity) v.getContext()).findViewById(R.id.navigation);
                    bottomNavigationView.setSelectedItemId(R.id.action_item3);
                }
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                //닫기
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                switch(groupPosition){
                    case 1:
                        Intent intent = new Intent(getActivity(), GoogleMapActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Intent intent1 = new Intent(getActivity(), GoogleMapActivity.class);
                        startActivity(intent1);
                        break;
                    case 5:
                        Intent intent2 = new Intent(getActivity(), GoogleMapActivity.class);
                        startActivity(intent2);
                        break;
                    case 6:
                        Intent intent3 = new Intent(getActivity(), GoogleMapActivity.class);
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });
        return v;
    }
    void mapButtonInit(){
        Button btnGps = v.findViewById(R.id.btnGps);
        btnGps.setOnClickListener(mapBtnClickListener);
    }

    View.OnClickListener mapBtnClickListener = ((v)->{
        Intent intent = new Intent(v.getContext(), GoogleMapActivity.class);
        startActivity(intent);
    });
    public List<ParentItem> getData(Context ctx) {
        List<ParentItem> parentItem = new ArrayList<>();
        Integer child1 = new Integer(R.drawable.first_question);
        Integer child2 = new Integer(R.drawable.second_question);
        Integer child3 = new Integer(R.drawable.three_question);
        Integer child4 = new Integer(R.drawable.four_question);
        Integer child5 = new Integer(R.drawable.five_question);
        Integer nothing = new Integer(R.drawable.nothing);

        DbOpenHelper = new DbOpenHelper(getActivity());
        DbOpenHelper.open(ctx);
        boughtProducts = DbOpenHelper.getBoughtProductList();
        if(boughtProducts.size() != 0) {
            BoughtProduct boughtProduct = boughtProducts.get(0);
            if (boughtProduct.getLevel() >= 1) {
                parentItem.add(new ParentItem("오프닝 영상을 감상하세요.", nothing, TypeItem.MAIN_CONTENTS));
            }
            if (boughtProduct.getLevel() >= 2) {
                parentItem.add(new ParentItem("퀘스트 '첫 지령'이(가) 생성되었습니다.", child1,TypeItem.MAIN_CONTENTS));
            }
            if (boughtProduct.getLevel() >= 3) {
                parentItem.add(new ParentItem("추천식당에서 식사를 하세요(10% 할인)", nothing,TypeItem.SERVICE_CONTENTS));
                parentItem.add(new ParentItem("퀘스트 '성서로운 돌'이(가) 생성되었습니다.", child2, TypeItem.MAIN_CONTENTS));
            }
            if (boughtProduct.getLevel() >= 4) {
                parentItem.add(new ParentItem("퀘스트 '조력자 컨택'이 생성되었습니다.", child3, TypeItem.MAIN_CONTENTS));
            }
            if (boughtProduct.getLevel() >= 5) {
                parentItem.add(new ParentItem("퀘스트 '어둠 속 빛 한줄기'이(가) 생성되었습니다.", child4, TypeItem.MAIN_CONTENTS));
            }
            if (boughtProduct.getLevel() >= 6) {
                parentItem.add(new ParentItem("퀘스트 '나라의 운명'이(가) 생성되었습니다.", child5, TypeItem.MAIN_CONTENTS));
            }
            return parentItem;
        }else
            return null;
    }

    Thread t = new Thread() {
        @Override
        public void run() {
            while (!isInterrupted()) {
                try {
                    Thread.sleep(1000);
                }catch(Exception e){}
                MainActivity.mHandler.post(() -> updateTextView());
            }
        }
    };

    public void updateTimeEverySecond(){
        t.start();
    }
    private void updateTextView() {
        Date noteTS = Calendar.getInstance().getTime();
        String time = "HH:mm:ss";
        String endTime = DateFormat.format(time, noteTS).toString();
        if(startTime!=null){
            try {
                Log.d("KTH","start : " +startTime);
                Log.d("KTH","end : " +endTime);
                Date date1 = new SimpleDateFormat("HH:mm:ss").parse(startTime);
                Date date2 = new SimpleDateFormat("HH:mm:ss").parse(endTime);
                long timeDifInMillis = date2.getTime() - date1.getTime();
                long diffSeconds = timeDifInMillis / 1000 % 60;
                long diffMinutes = timeDifInMillis / (60 * 1000) % 60;
                long diffHours = timeDifInMillis / (60 * 60 * 1000) % 24;
                endTime = String.format("%02d:%02d:%02d",diffHours,diffMinutes,diffSeconds);
                if(diffHours >= 4){
                    remainTime.setText("제한시간 종료");
                    t.interrupt();
                    return;
                }

                //10시간 기준
                String limitTIme = "4:00:00";
                Date date3 = new SimpleDateFormat("HH:mm:ss").parse(limitTIme);
                Date date4 = new SimpleDateFormat("HH:mm:ss").parse(endTime);
                timeDifInMillis = date3.getTime() - date4.getTime();
                diffSeconds = timeDifInMillis / 1000 % 60;
                diffMinutes = timeDifInMillis / (60 * 1000) % 60;
                diffHours = timeDifInMillis / (60 * 60 * 1000) % 24;
                endTime = String.format("%02d:%02d:%02d",diffHours,diffMinutes,diffSeconds);
                remainTime.setText(endTime);
            }catch(Exception e){}
        }else
            remainTime.setText(endTime);
    }
}

