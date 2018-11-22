package com.shaq.skifme.data.room;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaq.skifme.R;

import java.util.List;

public class GeoListAdapter extends RecyclerView.Adapter<GeoListAdapter.GeoViewHolder> {

    class GeoViewHolder extends RecyclerView.ViewHolder {
        private final TextView geoItemView;

        private GeoViewHolder(View itemView) {
            super(itemView);
            geoItemView = itemView.findViewById(R.id.geo_name);
        }
    }

    private final LayoutInflater mInflater;
    private List<Geozones> mGeozones; // Cached copy of words

    public GeoListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public GeoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.geo_rv, parent, false);
        return new GeoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GeoViewHolder holder, int position) {
        if (mGeozones != null) {
            Geozones current = mGeozones.get(position);
            holder.geoItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.geoItemView.setText("No Geozones");
        }
    }

    public void setGeozones(List<Geozones> geozones){
        mGeozones = geozones;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGeozones != null)
            return mGeozones.size();
        else return 0;
    }
}
