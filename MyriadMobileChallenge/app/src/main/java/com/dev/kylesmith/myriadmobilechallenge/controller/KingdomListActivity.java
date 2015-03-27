package com.dev.kylesmith.myriadmobilechallenge.controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dev.kylesmith.myriadmobilechallenge.R;

public class KingdomListActivity extends ActionBarActivity implements android.widget.SearchView.OnQueryTextListener {
    SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kingdom_list);
        settings = getSharedPreferences(getString(R.string.SHARED_PREF_NAME), 0);
        setTitle(settings.getString(getString(R.string.USER_EMAIL_KEY), "No email found"));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kingdom_list, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        android.widget.SearchView searchView = (android.widget.SearchView) MenuItemCompat.getActionView(searchItem);
        if (searchView != null) {
            searchView.setOnQueryTextListener(this);
        }

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
            Toast.makeText(this, "Successfully logged out!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
