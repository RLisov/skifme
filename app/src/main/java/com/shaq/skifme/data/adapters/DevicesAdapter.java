package com.shaq.skifme.data.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaq.skifme.R;
import com.shaq.skifme.data.res.DevicesRes;

import java.util.List;

public class DevicesAdapter extends RecyclerView.Adapter<DevicesAdapter.MyViewHolder> {

    private List<DevicesRes> devicesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,type;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            //type = (TextView) view.findViewById(R.id.device_type);

        }
    }


    public DevicesAdapter(List<DevicesRes> geozoneList) {
        this.devicesList = geozoneList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.devices_card_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DevicesRes devicesRes = devicesList.get(position);
        holder.title.setText(devicesRes.getCurrentTerminal().getAutoNumberModel());
        //holder.type.setText(devicesRes.getAutoModel());
    }

    @Override
    public int getItemCount() {
        if (devicesList != null)
            return devicesList.size();
        else return 0;
    }
}