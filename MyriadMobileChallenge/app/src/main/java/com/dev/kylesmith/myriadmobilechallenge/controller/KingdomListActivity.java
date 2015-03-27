package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.dev.kylesmith.myriadmobilechallenge.R;

public class KingdomListActivity extends ActionBarActivity implements android.widget.SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener {
    SharedPreferences settings;
    android.widget.SearchView mSearchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingdom_list);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.myriad_logo);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        settings = getSharedPreferences(getString(R.string.SHARED_PREF_NAME), 0);
        setTitle(settings.getString(getString(R.string.USER_EMAIL_KEY), "No email found"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kingdom_list, menu);

        final MenuItem searchItem = menu.findItem(R.id.search);
        mSearchView = (android.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        if (mSearchView != null) {
            mSearchView.setOnQueryTextListener(this);
            mSearchView.setQueryHint("Search Kingdoms");
            MenuItemCompat.setOnActionExpandListener(searchItem, this);
            MenuItemCompat.setActionView(searchItem, mSearchView);
            ImageView clearSearchButton = (ImageView)mSearchView.findViewById(mSearchView.getContext().getResources().getIdentifier("android:id/search_close_btn", null, null));
            clearSearchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onQueryTextChange("");
                    mSearchView.setQuery("", false);
                    searchItem.collapseActionView();
                }
            });
        }
        return true;
    }


    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }


    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mSearchView.getWindowToken(), 0);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        KingdomListFragment kingdomListFragment = (KingdomListFragment) getFragmentManager().findFragmentById(R.id.list);
        kingdomListFragment.updateList(s);
        return false;
    }


    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            SharedPreferences.Editor editor = settings.edit();
            editor.remove(getString(R.string.USER_EMAIL_KEY));
            editor.commit();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
        else if(id == R.id.search){
            android.widget.SearchView searchView = (android.widget.SearchView) MenuItemCompat.getActionView(item);
            searchView.setFocusable(true);
            searchView.requestFocus();
            searchView.setIconified(false);
        }

        return super.onOptionsItemSelected(item);
    }
}
