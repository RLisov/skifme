package com.shaq.skifme.data.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.shaq.skifme.R;
import com.shaq.skifme.data.res.ObjectsRes;
import com.shaq.skifme.data.room.Controls;
import com.shaq.skifme.data.room.Objects;
import com.shaq.skifme.ui.activities.TopLevelActivity;
import com.shaq.skifme.ui.fragments.AddObjectFragment;
import com.shaq.skifme.ui.fragments.ObjectFragment;

import java.util.List;

public class ObjectsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private static final int TYPE_CARD = 0;
    private static final int TYPE_BUTTON = 1;
    private final LayoutInflater mInflater;
    private List<Objects> mObjects;
    private List<Controls> mControls; // Cached copy of array
    private Context mContext;
    private String choosenId;

    class CardViewHolder extends RecyclerView.ViewHolder {
        private final TextView geoItemView, object_place_tv,last_time_tv;
        private final ImageView avatar_img,ellipse_back_iv;
        private final TextView battery_title;
        private final ConstraintLayout cv_wrapper;

        private CardViewHolder(final View itemView, final Context context) {
            super(itemView);
            geoItemView = itemView.findViewById(R.id.card_view_title);
            object_place_tv = itemView.findViewById(R.id.object_place_tv);
            avatar_img = itemView.findViewById(R.id.avatar);
            battery_title = itemView.findViewById(R.id.battery_lvl_tv);
            cv_wrapper = itemView.findViewById(R.id.cv_wrapper);
            last_time_tv = itemView.findViewById(R.id.last_time_tv);
            ellipse_back_iv = itemView.findViewById(R.id.ellipse_back_iv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(mContext instanceof TopLevelActivity){

                        ((TopLevelActivity) context).loadFragment(new ObjectFragment());

                    }
                }
            });

        }
    }

    class ButtonViewHolder extends RecyclerView.ViewHolder {
        private final ImageView add_object_btn;
        private final TextView geoItemView, battery_title, object_place_tv,last_time_tv;
        private final ImageView avatar_img, signal_img,battery_lvl,ellipse_back_iv;

        private ButtonViewHolder(View itemView, final Context context) {
            super(itemView);
            avatar_img = itemView.findViewById(R.id.avatar);
            signal_img = itemView.findViewById(R.id.signal_iv);
            battery_lvl = itemView.findViewById(R.id.battery_iv);
            geoItemView = itemView.findViewById(R.id.card_view_title);
            add_object_btn = itemView.findViewById(R.id.add_object_cross);
            battery_title = itemView.findViewById(R.id.battery_lvl_tv);
            object_place_tv = itemView.findViewById(R.id.object_place_tv);
            last_time_tv = itemView.findViewById(R.id.last_time_tv);
            ellipse_back_iv = itemView.findViewById(R.id.ellipse_back_iv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    if(mContext instanceof TopLevelActivity){
                        ((TopLevelActivity) context).loadFragment(new AddObjectFragment());

                    }
                }
            });
        }
    }

    public ObjectsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        this.mContext=context;
    }

    @Override
    public int getItemViewType(int position) {
        int viewType;

        if (mObjects.size() -1 == position  ) {
            viewType = TYPE_BUTTON;

        } else viewType=TYPE_CARD;

        return viewType;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //View itemView = mInflater.inflate(R.layout.devices_card_view, parent, false);
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {

            case TYPE_BUTTON:
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.devices_card_view, parent, false);
                //view.setBackgroundResource(mBackground);
                return new ButtonViewHolder(view, mContext);
            case TYPE_CARD:
                View view2 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.devices_card_view, parent, false);

                return new CardViewHolder(view2, mContext);

            default:
                View view1 = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.devices_card_view, parent, false);
                //view1.setBackgroundResource(mBackground);
                return new CardViewHolder(view1, mContext);
        }
        //return new CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder , int position) {
//        if (mGeozones != null) {
//            Objects current = mGeozones.get(position);
//            holder.geoItemView.setText(current.getName());
//        } else {
//            // Covers the case of data not being ready yet.
//            holder.geoItemView.setText("No Objects");
//        }
        switch (viewHolder.getItemViewType()) {

            case TYPE_CARD:
                final CardViewHolder holder = (CardViewHolder) viewHolder;
                Objects objects = mObjects.get(position);
                holder.geoItemView.setText(objects.getName());
                holder.object_place_tv.setText(objects.getCurrentPlace());
                holder.battery_title.setText(String.valueOf(objects.getBatteryLevel())+"%");
                holder.last_time_tv.setText(String.valueOf(objects.getLastOnline()+" мин. назад"));
                holder.cv_wrapper.setBackgroundResource(R.color.white);
                if(objects.isAlert()) {
                    holder.cv_wrapper.setBackgroundResource(R.color.colorAlert);
                }

                //SETTING VIEW
                break;
            case TYPE_BUTTON:
                final ButtonViewHolder holder1 = (ButtonViewHolder) viewHolder;
                holder1.geoItemView.setVisibility(View.INVISIBLE);
                holder1.signal_img.setVisibility(View.INVISIBLE);
                holder1.avatar_img.setVisibility(View.INVISIBLE);
                holder1.add_object_btn.setVisibility(View.VISIBLE);
                holder1.battery_title.setVisibility(View.INVISIBLE);
                holder1.object_place_tv.setVisibility(View.INVISIBLE);
                holder1.battery_lvl.setVisibility(View.INVISIBLE);
                holder1.last_time_tv.setVisibility(View.INVISIBLE);
                holder1.ellipse_back_iv.setVisibility(View.INVISIBLE);
                break;

        }
    }



    public void setObjects(List<Objects> objects){
        mObjects = objects;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mObjects != null)
            return mObjects.size();
        else return 0;
    }
}