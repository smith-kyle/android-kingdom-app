package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.kylesmith.myriadmobilechallenge.R;

public class KingdomListFragment extends Fragment {

    RecyclerView mRecyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_kingdom_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);


        return v;

    }
}
