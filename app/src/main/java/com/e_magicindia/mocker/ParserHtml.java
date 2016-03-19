package com.e_magicindia.mocker;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by mkodekar on 3/19/2016.
 */
public class ParserHtml {

    Context mContext;
    SharedPreferences sharedPreferences;
    ParserHtml(Context context) {

        this.mContext = context;



    }



    public void parser() {
        String tag_string_req = "req_register";
        sharedPreferences  = mContext.getSharedPreferences("Html", 0);
// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, AppConfig.URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        StringBuffer buffer = new StringBuffer();

                        try {
                            System.out.println("Connected to " + AppConfig.URL);
                            Document doc = Jsoup.parse(response);
                            System.out.println("Connected to " + AppConfig.URL);

                            System.out.println(doc);

                            String titletext = doc.body().toString();


                            System.out.println("Body : " + titletext);
                            String newText = html2text(titletext);
                            System.out.println("Final html text " + newText );

                            sharedPreferences.edit().putString("body", newText).putString("message", "Data Sync Successful").apply();

                        } catch (Exception t) {
                            t.printStackTrace();
                            sharedPreferences.edit().putString("body", null).putString("message", t.getMessage()).apply();
                        }


                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        sharedPreferences.edit().putString("body", null).putString("message", error.getMessage()).apply();

                    }
                });
// Add the request to the RequestQueue.
        Sample.getInstance().addToRequestQueue(stringRequest, tag_string_req);

    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }
}
