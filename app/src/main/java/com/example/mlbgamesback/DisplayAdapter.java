package com.example.mlbgamesback;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mlbgamesback.data.MLBObject;

import java.util.ArrayList;

public class DisplayAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<MLBObject> mlbObject;


    public DisplayAdapter(Context fragmentGameBack, ArrayList<MLBObject> mlbObject) {
        this.mContext = fragmentGameBack;
        //transfer content from database to temporary memory
        this.mlbObject = mlbObject;
    }

    public int getCount() {
        return mlbObject.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }



    public View getView(int pos, View v, ViewGroup parent) {
        Holder mHolder;
        LayoutInflater layoutInflater;
        if (v == null) {
            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.list_cell, null);
            mHolder = new Holder();

            //link to TextView
            mHolder.txtcola = v.findViewById(R.id.txtcola);
            mHolder.txtcolb = v.findViewById(R.id.txtcolb);
            v.setTag(mHolder);
        } else {
            mHolder = (Holder) v.getTag();
        }
        //transfer to TextView in screen

        if (mlbObject.get(pos).getDivisionId() == null && mlbObject.get(pos).getTeamName() == null) {
            mHolder.txtcola.setText(mlbObject.get(pos).getDivisionName());
        } else {
            mHolder.txtcola.setText(mlbObject.get(pos).getTeamName());
        }
        mHolder.txtcolb.setText(mlbObject.get(pos).getGameBack());
        return v;
    }
    public class Holder {
        TextView txtcola;
        TextView txtcolb;
    }

}

