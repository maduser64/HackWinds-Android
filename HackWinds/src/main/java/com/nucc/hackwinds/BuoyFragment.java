package com.nucc.hackwinds;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.koushikdutta.ion.Ion;
import com.koushikdutta.async.future.FutureCallback;

import java.util.ArrayList;

/**
 * Created by matthew on 10/30/14.
 */
public class BuoyFragment extends ListFragment {

    // URLs
    private String BI_URL = "http://www.ndbc.noaa.gov/data/realtime2/44097.txt";
    private String MTK_URL = "http://www.ndbc.noaa.gov/data/realtime2/44017.txt";

    // Data constants
    private int DATA_POINTS = 20;
    private int DATA_HEADER_LENGTH = 38;
    private int DATA_LINE_LENGTH = 19;
    private int HOUR_OFFSET = 3;
    private int MINUTE_OFFSET = 4;
    private int WVHT_OFFSET = 8;
    private int DPD_OFFSET = 9;
    private int DIRECTION_OFFSET = 11;
    private int BI_LOCATION = 41;
    private int MTK_LOCATION = 42;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.buoy_fragment, container, false);

        // Initialize it to Block Island
        getData(BI_LOCATION);

        return V;
    }

    public void getData(int location) {
        String URL;
        if (location == BI_LOCATION) {
            URL = BI_URL;
        } else {
            URL = MTK_URL;
        }

        Ion.with(getActivity()).load(URL).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                // download done...
                // Parse the data that was received
                ArrayList<Buoy> buoys = parseData(result);

                // Set the list adapter
            }
        });
    }

    public ArrayList<Buoy> parseData(String rawData) {
        ArrayList<Buoy> buoyDatas = new ArrayList<Buoy>();
        buoyDatas.add(new Buoy());

        // Return the list of buoy objects
        return buoyDatas;
    }

}