package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.kylesmith.myriadmobilechallenge.R;
import com.dev.kylesmith.myriadmobilechallenge.model.Kingdom;
import com.dev.kylesmith.myriadmobilechallenge.model.RestClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class KingdomListFragment extends Fragment {

    RecyclerView mRecyclerView;
    List<Kingdom> mKingdoms;
    RestClient restClient = new RestClient();
    KingdomAdapter kingdomAdapter;
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
        restClient.get().getKingdoms(new KingdomCallback());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        kingdomAdapter = new KingdomAdapter();
        mRecyclerView.setAdapter(kingdomAdapter);

        return v;

    }




    // Called after kingdom data has been retrieved
    private class KingdomCallback implements Callback<List<Kingdom>> {
        @Override
        public void success(List<Kingdom> kingdoms, Response response){
            mKingdoms = kingdoms;
            kingdomAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure(RetrofitError error){
            Log.e("KINGDOM API:", error.toString());
        }
    }





    // Holder for recycler view items
    private class KingdomHolder extends RecyclerView.ViewHolder{
        private Kingdom kingdom;
        private ImageView kingdomImage;
        private TextView kingdomName;
        public KingdomHolder(View v){
            super(v);
            kingdomImage = (ImageView) v.findViewById(R.id.kingdom_image);
            kingdomName = (TextView) v.findViewById(R.id.kingdom_name);
        }

        public void bindKingdom(Kingdom kingdom){
            this.kingdom = kingdom;
            Picasso.with(getActivity()).load(kingdom.getImage()).into(kingdomImage);
            kingdomName.setText(kingdom.getName());
        }
    }






    private class KingdomAdapter extends RecyclerView.Adapter<KingdomHolder>{
        @Override
        public KingdomHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.list_item_kingdom, parent, false);
            return new KingdomHolder(view);
        }

        @Override
        public void onBindViewHolder(KingdomHolder holder, int position) {
            Kingdom k = mKingdoms.get(position);
            holder.bindKingdom(k);
        }

        @Override
        public int getItemCount() {
            if(mKingdoms == null) return 0;
            return mKingdoms.size();
        }
    }
}
