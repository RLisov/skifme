package com.shaq.skifme.data.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.res.GeozonesRes;

import java.util.List;

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.MyViewHolder> {

    private List<GeozonesRes> geozoneList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,type;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.geo_name);
            type = (TextView) view.findViewById(R.id.geo_type);

        }
    }


    public GeoAdapter(List<GeozonesRes> geozoneList) {
        this.geozoneList = geozoneList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.geo_rv, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GeozonesRes geozonesRes = geozoneList.get(position);
        holder.title.setText(geozonesRes.name);
        holder.type.setText(geozonesRes.type.getValueRu());
    }

    @Override
    public int getItemCount() {

        return geozoneList.size();

    }
}