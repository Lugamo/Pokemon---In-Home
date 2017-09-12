package com.example.labsoftware20.pokemon;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

public class MainActivity extends Activity {
    TextView mTxtDisplay;
    ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTxtDisplay = (TextView) findViewById(R.id.txtDisplay);
        String url = "http://pokeapi.co/api/v2/pokemon/4";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            nombre = response.getString("name");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        JSONObject imagen = null;
                        try {
                            imagen = response.getJSONObject("sprites");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            mobile = imagen.getString("back_default");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        mTxtDisplay.setText("Response: " + mobile);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
    }



}
