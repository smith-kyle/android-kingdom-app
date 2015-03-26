package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.kylesmith.myriadmobilechallenge.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class QuestSlidePageFragment extends Fragment {
    private static final String QUEST_NAME = "_quest_name";
    private static final String QUEST_ID = "_quest_id";
    private static final String GIVER_ID = "_giver_id";
    private static final String GIVER_NAME = "_giver_name";
    private static final String GIVER_IMAGE = "_giver_image";
    private static final String GIVER_DESCRIPTION = "_giver_description";
    private static final String QUEST_DESCRIPTION = "_quest_description";

    private int mQuestID;
    private String mQuestName;
    private int mGiverID;
    private String mGiverName;
    private String mGiverImage;
    private String mGiverDesc;
    private String mQuestDesc;

    private TextView mGiverNameView;
    private TextView mQuestNameView;
    private TextView mGiverDescView;
    private TextView mQuestDescView;
    private ImageView mGiverImageView;

    public static QuestSlidePageFragment newInstance(int questID, String questName, int giverID, String giverName, String giverImage, String giverDescription, String questDescription){
        QuestSlidePageFragment fragment = new QuestSlidePageFragment();
        Bundle args = new Bundle();

        args.putInt(QUEST_ID, questID);
        args.putString(QUEST_NAME, questName);
        args.putInt(GIVER_ID, giverID);
        args.putString(GIVER_NAME, giverName);
        args.putString(GIVER_IMAGE, giverImage);
        args.putString(GIVER_DESCRIPTION, giverDescription);
        args.putString(QUEST_DESCRIPTION, questDescription);

        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mQuestID = getArguments().getInt(QUEST_ID);
        mQuestName = getArguments().getString(QUEST_NAME);
        mGiverID = getArguments().getInt(GIVER_ID);
        mGiverName = getArguments().getString(GIVER_NAME);
        mGiverImage = getArguments().getString(GIVER_IMAGE);
        mGiverDesc = getArguments().getString(GIVER_DESCRIPTION);
        mQuestDesc = getArguments().getString(QUEST_DESCRIPTION);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_quest_slide_page, container, false);

        mGiverImageView = (ImageView)v.findViewById(R.id.giver_image);
        mGiverNameView = (TextView) v.findViewById(R.id.giver_name);
        mQuestNameView = (TextView) v.findViewById(R.id.quest_name);
        mGiverDescView = (TextView) v.findViewById(R.id.giver_description);
        mQuestDescView = (TextView) v.findViewById(R.id.quest_description);

        mQuestNameView.setText(mQuestName);
        mGiverNameView.setText(mGiverName);
        if(mGiverDesc.isEmpty()) mGiverDescView.setText("Alas! The description of this Giver of Quests hath been shrouded in darkness");
        else mGiverDescView.setText(mGiverDesc);
        if(mQuestDesc.isEmpty()) mQuestDescView.setText("Take heart! The description of this quest hath not yet been made known to mortal man");
        else mQuestDescView.setText(mQuestDesc);

        Picasso.with(getActivity()).load(mGiverImage).fit().centerCrop().into(mGiverImageView);

        // Inflate the layout for this fragment
        return v;
    }

}
