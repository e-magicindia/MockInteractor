package com.e_magicindia.mocker;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.emagicindia.mockinteractor.BackGroundActionCallback;
import com.emagicindia.mockinteractor.mainthread.ThreadExecutor;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;

/**
 * Created by mkodekar on 3/19/2016.
 */
public class MainActivity extends AppCompatActivity implements BackGroundActionCallback {

    TextView result;
    ProgressDialog progressDialog;

    SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        sharedPreferences = getSharedPreferences("Html", 0);
        progressDialog = new ProgressDialog(this);
        result = (TextView) findViewById(R.id.resultinTextView);
        runMockInteractor();
    }



    @Override
    public void runMockInteractor() {
        ThreadExecutor executor = new ThreadExecutor();
        progressDialog.show();
        executor.run(new BackGroundAction(this, this));

    }

    @Override
    public void onMockActionComplete() {
        progressDialog.dismiss();
        String body = sharedPreferences.getString("body", "");
        String message = sharedPreferences.getString("message", "");
        result.setText(body);
        if (body == null) {
            result.setText(message);
            result.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));

        }   else {
            result.setText(body);
            result.setBackgroundColor(getResources().getColor(android.R.color.holo_green_light));
        }
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
