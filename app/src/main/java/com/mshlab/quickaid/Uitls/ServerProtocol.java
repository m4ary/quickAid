package com.mshlab.quickaid.Uitls;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class ServerProtocol {


    public ServerProtocol() {

    }


    /**
     * https://api.foursquare.com/v2/venues/search
     * ?client_id=CLIENT_ID
     * &client_secret=CLIENT_SECRET
     * &ll=40.7,-74
     * &query=sushi
     * &v=YYYYMMDD
     */


    public static String GETHttpRequest(String params, Context context) {
        String respones = null;
        try {
            respones = new GETAsyncTaskRequest().execute(params).get();

            boolean digitsOnly = TextUtils.isDigitsOnly(respones);

            if (digitsOnly) {
                int errorCode = Integer.parseInt(respones);

                if (errorCode == 403) {
                    return null;
                } else if (errorCode == 404) {
                    return null;
                } else if (errorCode == 500) {
                    return null;

                }
            } else {

                return respones;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
//            Loader.hide();
        }


        return respones;
    }


    private static class GETAsyncTaskRequest extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... params) {
            String urlString = "https://api.foursquare.com/v2/venues/search?" +
                    "client_id=" + App.clientId +
                    "&client_secret=" + App.client_secret +
                    "&v=20190305" +
                    "&ll=" + params[0]+
                    "&categoryId=4bf58dd8d48988d104941735"+
                    "&radius=100000";

            int code = -1;

            try {
                URL url = new URL(urlString);
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setRequestMethod("GET");
                client.addRequestProperty("client_id", App.clientId);
                client.addRequestProperty("client_secret", App.client_secret);
                client.connect();

                code = client.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = new BufferedInputStream(client.getInputStream());
                    String responses = getStringFromInputStream(inputStream);
                    inputStream.close();
                    return responses;
                } else {
                    return String.valueOf(code);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return String.valueOf(code);
            }

        }


    }


    public static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }


}
