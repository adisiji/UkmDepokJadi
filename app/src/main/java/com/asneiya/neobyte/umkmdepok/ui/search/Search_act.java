package com.asneiya.neobyte.umkmdepok.ui.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.singleVolley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Search_act extends AppCompatActivity {

    private static final String url = "http://umkmdepok.16mb.com/show/search.php?kat=";
    private ProgressDialog mProgressDialog;
    private static int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_act);
        mProgressDialog = new ProgressDialog(this);
        Intent i = getIntent();
        x = i.getIntExtra("idKat",0);
        Log.d("Intent = ",Integer.toString(x));
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        if(ab!=null) {
            // Enable the Up button
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            ab.setDisplayHomeAsUpEnabled(true);
        }

        if(x!=0){
            showresult();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void showresult(){
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        JsonArrayRequest jsObjRequest = new JsonArrayRequest
                (Request.Method.GET, url+x, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray respon) {
                        try {
                            for (int i = 0; i < respon.length(); i++) {
                                JSONObject response = (JSONObject) respon.get(i);
                                String name = response.getString("nama_umkm");
                                String jen_produk = response.getString("jen_produk");
                                String tipe = response.getString("tipe");
                                String alamat = response.getString("alamat");
                                String lat = response.getString("lat_alamat");
                                String lon = response.getString("lon_alamat");
                                String email = response.getString("email");
                                String hp = response.getString("nomor_hp");
                                String pemilik = response.getString("pemilik");
                            }
                            mProgressDialog.dismiss();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),
                                    "Error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgressDialog.dismiss();
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(),
                                "Error: " + error.getMessage(),
                                Toast.LENGTH_LONG).show();
                    }
                });

        // Access the RequestQueue through your singleton class.
        singleVolley.getInstance(this).add(jsObjRequest);
    }
}
