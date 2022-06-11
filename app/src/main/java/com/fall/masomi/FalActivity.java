package com.fall.masomi;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fall.masomi.retrofit_api.ApiClient;
import com.fall.masomi.retrofit_api.ApiInterface;
import com.fall.masomi.retrofit_api.GetFallResponse;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class FalActivity extends AppCompatActivity {

    Button btnRefresh;
    TextView textViewTitle;
    TextView textViewLeft;
    TextView textViewRight;
    Button btnBack;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fal);

        textViewTitle = findViewById(R.id.textViewTitle);
        textViewLeft = findViewById(R.id.textViewLeft);
        textViewRight = findViewById(R.id.textViewRight);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnRefresh = findViewById(R.id.btnRefresh);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });
        btnRefresh.setOnClickListener(v -> {
            getFal();
        });

        getFal();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void getFal() {
        pDialog.setMessage("در حال دریافت...");
        showDialog();
        ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
        apiInterface.getFall().enqueue(new Callback<GetFallResponse>() {
            @Override
            public void onResponse(Call<GetFallResponse> call, retrofit2.Response<GetFallResponse> response) {
                hideDialog();
                if (response.body() == null) {
                    Toast.makeText(getApplicationContext(), "خطایی رخ داده است", Toast.LENGTH_LONG).show();
                    return;
                }
                StringBuilder stringBuilderLeft = new StringBuilder();
                StringBuilder stringBuilderRight = new StringBuilder();

                if (response.body().verses != null)
                    for (int i = 0; i < response.body().verses.size(); i++) {
                        if (response.body().verses.get(i).vOrder % 2 == 0) {
                            stringBuilderLeft.append(response.body().verses.get(i).text);
                        } else {
                            stringBuilderRight.append(response.body().verses.get(i).text);
                        }

                    }
                textViewRight.setText(stringBuilderRight);
                textViewLeft.setText(stringBuilderLeft);
                textViewTitle.setText(response.body().title);

            }

            @Override
            public void onFailure(Call<GetFallResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });
    }
}