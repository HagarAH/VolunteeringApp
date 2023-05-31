package com.mobil.bizden.API;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class LocationCCD {
    private static final String TAG = LocationCCD.class.getSimpleName();

    private static final String PROVINCES_API_URL = "https://turkiyeapi.cyclic.app/api/v1/provinces";
    private static final String DISTRICTS_API_URL = "https://turkiyeapi.cyclic.app/api/v1/districts";

    public interface LocationListener {
        void onProvincesLoaded(List<String> provinces);

        void onDistrictsLoaded(List<String> districts);

        void onLocationLoadFailed(String error);
    }

    public void loadProvinces(LocationListener listener) {
        new ProvincesAsyncTask(listener).execute(PROVINCES_API_URL);

    }

    public void loadDistricts(String provinceName, LocationListener listener) {
        String url = DISTRICTS_API_URL + "?province=" + provinceName;

        new DistrictsAsyncTask(listener, provinceName).execute(url);
    }

    private static class ProvincesAsyncTask extends AsyncTask<String, Void, List<String>> {
        private LocationListener listener;

        ProvincesAsyncTask(LocationListener listener) {
            this.listener = listener;
        }

        @Override
        protected List<String> doInBackground(String... urls) {
            String apiUrl = urls[0];
            try {
                URL url = new URL(apiUrl);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        JSONObject jsonResponse = new JSONObject(response.toString());
                        JSONArray provincesArray = jsonResponse.getJSONArray("data");

                        List<String> provinces = new ArrayList<>();
                        for (int i = 0; i < provincesArray.length(); i++) {
                            JSONObject provinceObject = provincesArray.getJSONObject(i);
                            String provinceName = provinceObject.getString("name");
                            provinces.add(provinceName);
                        }
                        return provinces;
                    }
                } else {
                    Log.e(TAG, "HTTP error code: " + responseCode);
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error loading provinces data", e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(List<String> provinces) {
            if (provinces != null) {
                listener.onProvincesLoaded(provinces);
            } else {
                listener.onLocationLoadFailed("Failed to load provinces data");
            }
        }
    }

    private static class DistrictsAsyncTask extends AsyncTask<String, Void, List<String>> {
        private LocationListener listener;
        private String provinceName;

        DistrictsAsyncTask(LocationListener listener, String provinceName) {
            this.listener = listener;
            this.provinceName = provinceName;
        }

        @Override
        protected List<String> doInBackground(String... urls) {
            String apiUrl = urls[0];

            try {
                URL url = new URL(apiUrl);
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"))) {
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }

                        JSONObject jsonResponse = new JSONObject(response.toString());
                        JSONArray districtsArray = jsonResponse.getJSONArray("data");

                        List<String> districts = new ArrayList<>();
                        for (int i = 0; i < districtsArray.length(); i++) {
                            JSONObject districtObject = districtsArray.getJSONObject(i);
                            String districtName = districtObject.getString("name");
                            String districtProvince = districtObject.getString("province");
                            if (districtProvince.equalsIgnoreCase(provinceName)) {
                                districts.add(districtName);
                            }
                        }
                        return districts;
                    }
                } else {
                    Log.e(TAG, "HTTP error code: " + responseCode);
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error loading districts data", e);
            }


            return null;
        }

        @Override
        protected void onPostExecute(List<String> districts) {
            if (districts != null) {
                listener.onDistrictsLoaded(districts);
            } else {
                listener.onLocationLoadFailed("Failed to load districts data");
            }
        }
    }
}
