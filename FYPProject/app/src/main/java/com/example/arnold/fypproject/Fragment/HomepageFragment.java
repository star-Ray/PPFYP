package com.example.arnold.fypproject.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.arnold.fypproject.R;


public class HomepageFragment extends android.support.v4.app.Fragment {
    public static final String ARG_OBJECT = "object";

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_text, container, false);
        Bundle args = getArguments();
        ((TextView)rootView.findViewById(R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}

