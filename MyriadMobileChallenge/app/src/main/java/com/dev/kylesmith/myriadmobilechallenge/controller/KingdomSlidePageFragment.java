package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.kylesmith.myriadmobilechallenge.R;
import com.squareup.picasso.Picasso;

public class KingdomSlidePageFragment extends Fragment {
    private static final String NAME = "_name";
    private static final String ID = "_id";
    private static final String IMAGE = "_image";
    private static final String CLIMATE = "_climate";
    private static final String POPULATION = "_population";
    private static final String LANGUAGE = "_language";

    private int mKingdomID;
    private String mKingdomName;
    private String mKingdomImage;
    private int mKingdomPopulation;
    private String mKingdomClimate;
    private String mKingdomLanguage;

    private ImageView mKingdomImageView;
    private TextView mKingdomNameView;
    private TextView mKingdomPopulationView;
    private TextView mKingdomClimateView;
    private TextView mKingdomLanguageView;

    public static KingdomSlidePageFragment newInstance(int KingdomID, String Name, String Climate, int Population, String Image, String Language){
        KingdomSlidePageFragment fragment = new KingdomSlidePageFragment();
        Bundle args = new Bundle();

        args.putInt(ID, KingdomID);
        args.putString(NAME, Name);
        args.putString(IMAGE, Image);
        args.putString(CLIMATE, Climate);
        args.putInt(POPULATION, Population);
        args.putString(LANGUAGE, Language);

        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mKingdomID = getArguments().getInt(ID);
        mKingdomName = getArguments().getString(NAME);
        mKingdomImage = getArguments().getString(IMAGE);
        mKingdomPopulation = getArguments().getInt(POPULATION);
        mKingdomClimate = getArguments().getString(CLIMATE);
        mKingdomLanguage = getArguments().getString(LANGUAGE);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_kingdom_slide_page, container, false);

        mKingdomImageView = (ImageView)v.findViewById(R.id.kingdom_slider_image);
        mKingdomClimateView = (TextView) v.findViewById(R.id.kingdom_climate);
        mKingdomPopulationView = (TextView) v.findViewById(R.id.kingdom_population);
        mKingdomNameView = (TextView) v.findViewById(R.id.kingdom_slider_name);
        mKingdomLanguageView = (TextView) v.findViewById(R.id.kingdom_language);

        Picasso.with(getActivity()).load(mKingdomImage).fit().centerCrop().into(mKingdomImageView);
        mKingdomClimateView.setText(mKingdomClimate.toString());
        mKingdomNameView.setText(mKingdomName);
        mKingdomPopulationView.setText(Integer.toString(mKingdomPopulation));
        if(mKingdomLanguage.isEmpty()) mKingdomLanguageView.setText("Unknown");
        else mKingdomLanguageView.setText(mKingdomLanguage);

        // Inflate the layout for this fragment
        return v;
    }

}
