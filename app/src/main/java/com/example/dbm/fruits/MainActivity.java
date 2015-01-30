package com.example.dbm.fruits;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;


public class MainActivity extends ActionBarActivity implements MainFragment.OnMainInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MainFragment())
                    .commit();
        }
    }


    private void selectList(){
        FruitFragment newFragment = new FruitFragment();
        replaceFragment(newFragment);
    }

    private void selectRequest(){
        PokemonFragment newFragment = new PokemonFragment();
        replaceFragment(newFragment);
    }

    private void replaceFragment(Fragment newFragment){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container, newFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onMainInteraction(View v) {
        int id = v.getId();

        if(id == R.id.button_list)
            selectList();

        if(id == R.id.button_request)
            selectRequest();

    }
}
