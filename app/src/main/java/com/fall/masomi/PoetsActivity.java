package com.fall.masomi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fall.masomi.retrofit_api.ApiClient;
import com.fall.masomi.retrofit_api.ApiInterface;
import com.fall.masomi.retrofit_api.GetFallResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class PoetsActivity extends AppCompatActivity {


    ProgressDialog pDialog;
    RecyclerView recyclerView;
    List<Poet> mPoetList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poets);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


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
        apiInterface.getPoets().enqueue(new Callback<List<Poet>>() {
            @Override
            public void onResponse(Call<List<Poet>> call, retrofit2.Response<List<Poet>> response) {
                hideDialog();
                if (response.body() == null) {
                    Toast.makeText(getApplicationContext(), "خطایی رخ داده است", Toast.LENGTH_LONG).show();
                    return;
                }
                mPoetList = response.body();
                recyclerView.setAdapter(new PoetsAdapter());
            }

            @Override
            public void onFailure(Call<List<Poet>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        });
    }

    private class PoetsAdapter extends RecyclerView.Adapter<PoetsVH> {
        @NonNull
        @Override
        public PoetsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PoetsVH(LayoutInflater.from(PoetsActivity.this).inflate(R.layout.listitem_poet, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PoetsVH holder, int position) {
            Poet poet = mPoetList.get(position);
            holder.textViewName.setText(poet.name);
            holder.textViewTavalood.setText("تاریخ تولد: " + poet.birthYearInLHijri);
            holder.textViewVafaat.setText("تاریخ وفات: " + poet.deathYearInLHijri);
            holder.textViewPlaceBirth.setText("محل تولد: " + poet.birthPlace);
            holder.textViewPlaceDead.setText("محل وفات: " + poet.deathPlace);
        }

        @Override
        public int getItemCount() {
            return mPoetList.size();
        }
    }

    private static class PoetsVH extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewTavalood;
        TextView textViewVafaat;
        TextView textViewPlaceBirth;
        TextView textViewPlaceDead;

        public PoetsVH(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewTavalood = itemView.findViewById(R.id.textViewTavalood);
            textViewVafaat = itemView.findViewById(R.id.textViewVafaat);
            textViewPlaceBirth = itemView.findViewById(R.id.textViewPlaceBirth);
            textViewPlaceDead = itemView.findViewById(R.id.textViewPlaceDead);
        }
    }
}