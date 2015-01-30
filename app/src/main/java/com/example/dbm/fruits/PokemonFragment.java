package com.example.dbm.fruits;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonFragment extends Fragment {

    public static final String ARGS_POKEMON_NAME = "com.example.dbm.fruits.PokemonFragment.mName";

    private ImageView mImageView;
    private TextView mTextPokemonName;
    private String mName;

    public PokemonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pokemon, container, false);
        mTextPokemonName = (TextView) rootView.findViewById(R.id.text_pokemon_name);
        mImageView = (ImageView) rootView.findViewById(R.id.image_pokemon);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mName == null) {
                    String url = "http://pokeapi.co/api/v1/pokemon/1";
                    RequestQueue queue = Volley.newRequestQueue(getActivity());

                    final ProgressDialog progressDialog = ProgressDialog.show(
                            getActivity(),
                            getResources().getString(R.string.loading),
                            getResources().getString(R.string.wait));

                    progressDialog.show();

                    final JSONObject myPokemon = new JSONObject();
                    JsonObjectRequest request = new JsonObjectRequest(
                            url,
                            myPokemon,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        mName = response.getString("name");
                                        setText(mName);
                                    } catch (JSONException e) {
                                        mTextPokemonName.setText(getResources().getString(R.string.error));
                                    } finally {
                                        progressDialog.dismiss();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity(), getResources().getString(R.string.error), Toast.LENGTH_SHORT).show();
                                }
                            }
                    );

                    queue.add(request);
                }
            }
        });
        return rootView;
    }

    private void setText(String name) {
        String newMessage = String.format("%s %s", "Name: ", name);
        mTextPokemonName.setText(newMessage);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ARGS_POKEMON_NAME, mName);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            mName = savedInstanceState.getString(ARGS_POKEMON_NAME);
            setText(mName);
        }
    }
}
