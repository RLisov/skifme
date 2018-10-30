package com.shaq.skifme.data.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.res.GeozonesRes;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {

    private List<GeozonesRes> geozoneList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.geo_name);

        }
    }


    public MoviesAdapter(List<GeozonesRes> moviesList) {
        this.geozoneList = moviesList;
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
    }

    @Override
    public int getItemCount() {
        return geozoneList.size();
    }
}