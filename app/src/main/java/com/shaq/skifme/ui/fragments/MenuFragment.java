package com.shaq.skifme.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.shaq.skifme.R;
import com.shaq.skifme.ui.activities.TopLevelActivity;

public class MenuFragment extends Fragment implements View.OnClickListener {

    View rootView;
    LinearLayout statistic_ll, notif_ll, settings_ll, help_ll, about_ll;
    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set Title of fragment
        ((TopLevelActivity) getActivity()).getSupportActionBar().setTitle("Меню");


    }

    @Override
    public void onStart() {
        super.onStart();

        rootView = getView();
        statistic_ll = (LinearLayout) rootView.findViewById(R.id.statistic_ll);
        notif_ll = (LinearLayout) rootView.findViewById(R.id.notifications_ll);
        settings_ll = (LinearLayout) rootView.findViewById(R.id.settings_ll);
        help_ll = (LinearLayout) rootView.findViewById(R.id.help_ll);
        about_ll = (LinearLayout) rootView.findViewById(R.id.about_ll);

        statistic_ll.setOnClickListener(this);
        notif_ll.setOnClickListener(this);
        settings_ll.setOnClickListener(this);
        help_ll.setOnClickListener(this);
        about_ll.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case    R.id.statistic_ll:
                Log.d("MENU","statistic");
                break;
            case  R.id.notifications_ll:

                break;
            case R.id.settings_ll:
                break;
            case R.id.help_ll:
                break;
            case R.id.about_ll:
                break;
        }
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, null);

    }


}
