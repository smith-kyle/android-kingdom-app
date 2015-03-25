package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;

import com.dev.kylesmith.myriadmobilechallenge.R;
import com.dev.kylesmith.myriadmobilechallenge.model.Kingdom;
import com.dev.kylesmith.myriadmobilechallenge.model.RestClient;
import com.dev.kylesmith.myriadmobilechallenge.model.quest;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class QuestActivity extends FragmentActivity {

    private int mKingdomID;
    private Kingdom mKingdom;
    private RestClient restClient = new RestClient();
    private int number_of_quests;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        // Get Kingdom ID that was passed into activity and retrieve more detailed information
        mKingdomID = getIntent().getIntExtra(getString(R.string.KINGDOM_ID_KEY), -1);
        if(mKingdomID != -1) restClient.get().getQuests(mKingdomID, new QuestCallback());
    }



    // When back button is pressed switch to previous slide if not on the last slide
    @Override
    public void onBackPressed() {
        if(mPager.getCurrentItem() == 0) super.onBackPressed();
        else mPager.setCurrentItem(mPager.getCurrentItem()-1);
    }




    private class QuestCallback implements Callback<Kingdom> {
        @Override
        public void success(Kingdom kingdom, Response response) {
            mKingdom = kingdom;
            number_of_quests = mKingdom.quests.size();
            mPager = (ViewPager) findViewById(R.id.pager);
            mPager.setAdapter(mPagerAdapter);
        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("QUEST API", error.toString());
        }
    }




    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {
            quest q = mKingdom.quests.get(position);
            return QuestSlidePageFragment.newInstance(q.id, q.name, q.giver.id, q.giver.name, q.giver.image);
        }

        @Override
        public int getCount() {
            return number_of_quests;
        }
    }
}
