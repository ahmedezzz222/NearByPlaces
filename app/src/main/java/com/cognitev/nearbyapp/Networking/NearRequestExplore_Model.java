package com.cognitev.nearbyapp.Networking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cognitev.nearbyapp.Adapter.ExploreListAdapter;
import com.cognitev.nearbyapp.Utilites.Constant;
import com.cognitev.nearbyapp.Model.VenuesExploreModel.Explore;
import com.cognitev.nearbyapp.Model.VenuesExploreModel.Item_;
import com.cognitev.nearbyapp.R;
import com.cognitev.nearbyapp.Utilites.SharedPref;
import com.cognitev.nearbyapp.Utilites.Utilis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearRequestExplore_Model {

    private Context context;
    private List<Item_> item_list = new ArrayList<Item_>();

    @SuppressLint("StaticFieldLeak")
    private static NearRequestExplore_Model instance = null;

    public NearRequestExplore_Model getInstance(Context ctx) {
        if (instance == null) {
            synchronized (ctx) {
                if (instance == null) {
                    instance = new NearRequestExplore_Model();
                }
            }
        }
        this.context = ctx;
        return instance;
    }


    public void AllNearPlaces_API(final Context cont, final ListView listView) {
        String latlong = SharedPref.RetriveLATALONG(cont);

        FourSquareService fourSquareService = FourSquareService.retrofit.create(FourSquareService.class);
        final Call<Explore> call = fourSquareService.requestExplore(Constant.Client_ID, Constant.Client_Secret, Constant.apiVersion, latlong);//Constant.geoLocation);
        call.enqueue(new Callback<Explore>() {
            @Override
            public void onResponse(Call<Explore> call, Response<Explore> response) {
                if (response != null) {
                    if (response.body() != null) {
                        if (response.body().getResponse() != null) {
                            if (response.body().getResponse().getGroups() != null) {
                                if (response.body().getResponse().getGroups().size() > 0) {
                                    item_list = response.body().getResponse().getGroups().get(0).getItems();
                                    ExploreListAdapter exploreListAdapter = new ExploreListAdapter(cont, R.layout.item_list, item_list);
                                    listView.setAdapter(exploreListAdapter);
                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Explore> call, Throwable t) {
                //t.printStackTrace();
            }
        });
    }

    public void AllNearPlaces_API_firstload(final Context cont, final ListView listView, final ProgressBar progress) {
        String latlong = SharedPref.RetriveLATALONG(cont);

        progress.setVisibility(View.VISIBLE);

        FourSquareService fourSquareService = FourSquareService.retrofit.create(FourSquareService.class);
        final Call<Explore> call = fourSquareService.requestExplore(Constant.Client_ID, Constant.Client_Secret, Constant.apiVersion, latlong);//Constant.geoLocation);
        call.enqueue(new Callback<Explore>() {
            @Override
            public void onResponse(Call<Explore> call, Response<Explore> response) {
                if (response != null) {
                    if (response.body() != null) {
                        if (response.body().getResponse() != null) {
                            if (response.body().getResponse().getGroups() != null) {
                                if (response.body().getResponse().getGroups().size() > 0) {
                                    item_list = response.body().getResponse().getGroups().get(0).getItems();
                                    ExploreListAdapter exploreListAdapter = new ExploreListAdapter(cont, R.layout.item_list, item_list);
                                    listView.setAdapter(exploreListAdapter);
                                    progress.setVisibility(View.GONE);
                                }
                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<Explore> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });
    }
}
