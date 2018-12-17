package com.shaq.skifme.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.room.Objects;

import java.util.List;

public class ControlListAdapter extends RecyclerView.Adapter<ControlListAdapter.ControlViewHolder> {

    class ControlViewHolder extends RecyclerView.ViewHolder {
        private final TextView wordItemView;

        private ControlViewHolder(View itemView) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.control_name_rv);
        }
    }

    private final LayoutInflater mInflater;
    private List<Objects> mNames; // Cached copy of words

    public ControlListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @NonNull
    @Override
    public ControlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.control_list_rv, parent, false);
        return new ControlViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ControlViewHolder holder, int position) {
        if (mNames != null) {
            Objects current = mNames.get(position);
            holder.wordItemView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.
            holder.wordItemView.setText("No Word");
        }
    }

    public void setWords(List<Objects> names){
        mNames = names;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mNames != null)
            return mNames.size();
        else return 0;
    }
}