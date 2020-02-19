package com.mshlab.quickaid.Fragment;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mshlab.quickaid.Obj.ResponeSearchPlcaes;
import com.mshlab.quickaid.PlacesViewAdapter;
import com.mshlab.quickaid.R;
import com.mshlab.quickaid.Uitls.App;
import com.mshlab.quickaid.Uitls.ItemClickSupport;
import com.mshlab.quickaid.Uitls.ServerProtocol;

import java.util.ArrayList;
import java.util.List;

;import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;


public class PlacesFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private PlacesViewAdapter adapter;
    private Context mContext;
    private List<ResponeSearchPlcaes.VenuesEntity> entityList = new ArrayList<>();

    private PlacesFragment.OnFragmentInteractionListener mListener;


    public PlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_places, container, false);
        mContext = v.getContext();
        mRecyclerView = v.findViewById(R.id.recycleViewContents);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new PlacesViewAdapter(mContext, entityList);

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mRecyclerView.getContext(), DividerItemDecoration.VERTICAL));


        SmartLocation.with(mContext).location()
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(Location location) {
                        String respone = ServerProtocol.GETHttpRequest(location.getLatitude() + "," + location.getLongitude(), App.mContext);
                        Log.e("respone", respone);
                        if (respone != null && respone.equals("-1") && TextUtils.isDigitsOnly(respone)) {
                            Toast.makeText(mContext, "we have problem", Toast.LENGTH_SHORT).show();
                        } else {
                            try {
                                ResponeSearchPlcaes responeSearchPlcaes = new Gson().fromJson(respone, ResponeSearchPlcaes.class);
                                List<ResponeSearchPlcaes.VenuesEntity> list = responeSearchPlcaes.getResponse().getVenues();
                                entityList.clear();
                                entityList.addAll(list);
                                adapter.notifyDataSetChanged();
                            }catch (Exception e){
                                Toast.makeText(mContext, "we have problem", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });

        ItemClickSupport.addTo(mRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                final ResponeSearchPlcaes.VenuesEntity venuesEntity = entityList.get(position);

            }


        });


        return v;

    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
