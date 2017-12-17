package com.example.taehyung.ddac.Fragment;

/**
 * Created by TaeHyungKim on 2017-12-16.
 */


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taehyung.ddac.Adapter.ExpandableListAdapter;
import com.example.taehyung.ddac.Adapter.LoopPagerAdapter;
import com.example.taehyung.ddac.Adapter.RecyclerViewAdapter;
import com.example.taehyung.ddac.DDACMainActivity;
import com.example.taehyung.ddac.DataBase.DbOpenHelper;
import com.example.taehyung.ddac.Item.BoughtProduct;
import com.example.taehyung.ddac.Item.ProductItem;
import com.example.taehyung.ddac.MainActivity;
import com.example.taehyung.ddac.R;
import com.example.taehyung.ddac.RollView.RollPagerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class DdacFragment extends Fragment {
    private TextView remainTime;
    private View v;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListTitle;
    private HashMap<String, Integer> expandableListDetail;
    public static DdacFragment newInstance() {
        DdacFragment fragment = new DdacFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_ddac, container, false);
        remainTime = v.findViewById(R.id.remainTime);
        updateTimeEverySecond();
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListAdapter.getData(getContext());
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new ExpandableListAdapter(v.getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        Display newDisplay = getActivity().getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();
        expandableListView.setIndicatorBounds(width-100, width);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(v.getContext(), expandableListTitle.get(groupPosition) + " ListView Open.", Toast.LENGTH_SHORT).show();
            }
        });
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(v.getContext(), expandableListTitle.get(groupPosition) + " ListView Closed.", Toast.LENGTH_SHORT).show();

            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(v.getContext(), expandableListDetail.get(expandableListTitle.get(groupPosition)), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return v;
    }


    public void updateTimeEverySecond(){
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
        t.start();
    }
    private void updateTextView() {
        Date noteTS = Calendar.getInstance().getTime();
        String time = "hh:mm:ss";
        remainTime.setText(DateFormat.format(time, noteTS));
    }
}

