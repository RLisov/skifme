package com.shaq.skifme.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.shaq.skifme.R;
import com.shaq.skifme.data.res.ObjectsRes;
import com.shaq.skifme.data.room.Controls;

import java.util.List;

public class ControlListAdapter extends RecyclerView.Adapter<ControlListAdapter.ControlViewHolder> {

    class ControlViewHolder extends RecyclerView.ViewHolder {
        private final TextView controlItemView;
        private final Switch jobSwitch;


        private ControlViewHolder(View itemView) {
            super(itemView);
            controlItemView = itemView.findViewById(R.id.control_name_rv);
            jobSwitch = itemView.findViewById(R.id.switch_control);
        }
    }

    private final LayoutInflater mInflater;
    private List<Controls> mControls; // Cached copy of words
    private Context mContext;

    public ControlListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public ControlViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.control_list_rv, parent, false);
        return new ControlViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ControlViewHolder holder, final int position) {
        if (mControls != null) {
            final Controls current = mControls.get(position);
            holder.controlItemView.setText(current.getName());
            holder.jobSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    showToast(position,mContext, isChecked);
                    if(isChecked) {
                        Log.d("ControlList", String.valueOf(position));
                        //TODO: CONVERT POSITION TO ID AND SEND TO API
                    }
                }
            });
        } else {
            // Covers the case of data not being ready yet.
            holder.controlItemView.setText("No Word");
        }
    }

    private void showToast(final int position,Context context, boolean isChecked) {
        String toogleActive;
        if(isChecked) {
              toogleActive = " включен";
        } else toogleActive = " выключен";

        final Controls current = mControls.get(position);
        Toast toast = Toast.makeText(context,"Контроль "+current.getName()+ toogleActive,Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setControls(List<Controls> controls){
        mControls = controls;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mControls != null)
            return mControls.size();
        else return 0;
    }
}