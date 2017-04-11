package com.labs.ramdanix.swiperefreshfragment.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.labs.ramdanix.swiperefreshfragment.Config;
import com.labs.ramdanix.swiperefreshfragment.MyApplication;
import com.labs.ramdanix.swiperefreshfragment.R;
import com.labs.ramdanix.swiperefreshfragment.adapter.MyAdapter;
import com.labs.ramdanix.swiperefreshfragment.model.UserPinjam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by ramdanix on 11/04/17.
 */

public class MainFragment extends Fragment{

    private static final String TAG = "REQUEST";
    ListView listView;
    List<UserPinjam> userPinjamsList;
    MyAdapter myAdapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    public static MainFragment newInstance(){
        MainFragment mainFragment = new MainFragment();
        return mainFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ambilData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_main_lay, container, false);

        listView = (ListView) rootView.findViewById(R.id.list_user_pinjam);
        swipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);

        userPinjamsList = new ArrayList<>();


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ambilData();
            }
        });


        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        ambilData();
                                    }
                                }
        );


        return rootView;
    }

    void ambilData(){

        // Volley's json array request object
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,
                Config.URL_FULL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, "response " + response.toString());

                    if (response.length() > 0 ) {

                        try {

                            JSONArray jsonArray = response.getJSONArray("data");

                            Log.d(TAG, "jumlah data dari server " + jsonArray.length());

                            userPinjamsList.clear();

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject data = jsonArray.getJSONObject(i);
                                String namaSiswa = data.getString(Config.TAG_NAMA);
                                String kelasSiswa = data.getString(Config.TAG_KELAS);
                                String waktuPinjamSiswa = data.getString(Config.TAG_WAKTU_PINJAM);

                                UserPinjam userPinjam = new UserPinjam(namaSiswa, kelasSiswa, waktuPinjamSiswa);

                                userPinjamsList.add(i, userPinjam);
                            }

                            myAdapter = new MyAdapter(getActivity(),userPinjamsList);

                            listView.setAdapter(myAdapter);

                            Log.d(TAG, "jumlah data dari list " + userPinjamsList.size());


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    //stopping swipe refresh
                    swipeRefreshLayout.setRefreshing(false);

                }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Server Error: " + error.getMessage());

                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_LONG).show();

                // stopping swipe refresh
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        MyApplication.getInstance().addToRequestQueue(req);
    }

}
