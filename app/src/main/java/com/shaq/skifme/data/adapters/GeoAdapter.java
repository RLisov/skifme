package com.shaq.skifme.data.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.managers.DataManager;
import com.shaq.skifme.data.res.GeozonesRes;
import com.shaq.skifme.ui.activities.TopLevelActivity;
import com.shaq.skifme.ui.fragments.MapFragment;

import java.util.List;

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.MyViewHolder> {

    private List<GeozonesRes> geozoneList;
    Context ctx;
    MapFragment mMapFragment;
    DataManager mDataManager;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,type;
        public CheckBox mCheckBox;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.geo_name);
            type = (TextView) view.findViewById(R.id.geo_type);
            mCheckBox = (CheckBox) view.findViewById(R.id.geo_check_list_item);

        }
    }


    public GeoAdapter(List<GeozonesRes> geozoneList, Context ctx) {
        this.geozoneList = geozoneList;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geo_rv, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        mDataManager = DataManager.getInstance();
        GeozonesRes geozonesRes = geozoneList.get(position);
        holder.title.setText(geozonesRes.name);
        holder.type.setText(geozonesRes.type.getValueRu());

        if (!mDataManager.getPreferencesManager().getActionMode()) {
            holder.mCheckBox.setVisibility(View.GONE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            holder.mCheckBox.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {

        return geozoneList.size();

    }
}