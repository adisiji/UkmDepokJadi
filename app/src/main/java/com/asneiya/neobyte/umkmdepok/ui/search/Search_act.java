package com.asneiya.neobyte.umkmdepok.ui.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.asneiya.neobyte.umkmdepok.R;
import com.asneiya.neobyte.umkmdepok.model.umkm.ContentUmkm;
import com.asneiya.neobyte.umkmdepok.singleVolley;
import com.asneiya.neobyte.umkmdepok.ui.adapter.AdapterSearchResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Search_act extends AppCompatActivity {

    private static final String url = "http://umkmdepok.16mb.com/show/search.php?kat=";
    private ProgressDialog mProgressDialog;
    private RecyclerView rvSearch;
    private AdapterSearchResult adapt;
    private static int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_act);
        mProgressDialog = new ProgressDialog(this);
        rvSearch = (RecyclerView)findViewById(R.id.RV_search_Act);
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
                            List<ContentUmkm> konten = new ArrayList<>();
                            for (int i = 0; i < respon.length(); i++) {
                                JSONObject response = (JSONObject) respon.get(i);
                                String name = response.getString("nama_umkm");
                                String kategori = response.getString("id_kategori");
                                String jen_produk = response.getString("jen_produk");
                                String tipe = response.getString("tipe");
                                String alamat = response.getString("alamat");
                                String lat = response.getString("lat_alamat");
                                String lon = response.getString("lon_alamat");
                                String email = response.getString("email");
                                String logo = response.getString("logo_umkm");
                                String hp = response.getString("nomor_hp");
                                String pemilik = response.getString("pemilik");
                                //Set Isi card View
                                ContentUmkm cu = new ContentUmkm();
                                cu.setNama(name);
                                cu.setAlamat(alamat);
                                cu.setJen_produk(jen_produk);
                                cu.setTipe(tipe);
                                cu.setLat(Float.parseFloat(lat));
                                cu.setLon(Float.parseFloat(lon));
                                cu.setEmail(email);
                                cu.setHp(hp);
                                cu.setLogo(logo);
                                cu.setPemilik(pemilik);
                                konten.add(cu);
                            }
                            adapt = new AdapterSearchResult(getApplicationContext(),konten);
                            rvSearch.setAdapter(adapt);
                            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
                            rvSearch.setLayoutManager(layoutManager);
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
