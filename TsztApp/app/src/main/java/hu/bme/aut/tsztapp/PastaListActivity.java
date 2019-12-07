package hu.bme.aut.tsztapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import hu.bme.aut.tsztapp.adapter.PastaListAdapter;
import hu.bme.aut.tsztapp.api.ListAPI;
import hu.bme.aut.tsztapp.model.Meal;
import hu.bme.aut.tsztapp.model.Pasta;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PastaListActivity extends AppCompatActivity implements PastaListAdapter.OnPastaSelectedListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private Pasta[] pastas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_list);

        recyclerView = (RecyclerView) findViewById(R.id.PastaListRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ListAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ListAPI listAPI = retrofit.create(ListAPI.class);

        Call<Meal> call = listAPI.getPastaList();

        call.enqueue(new Callback<Meal>() {
            @Override
            public void onResponse(Call<Meal> call, Response<Meal> response) {
                Meal meal = response.body();

                pastas = meal.getMeals();

                initRecyclerView();
            }

            @Override
            public void onFailure(Call<Meal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.PastaListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PastaListAdapter(pastas, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onPastaSelected(Pasta pasta) {
        String currentPastaId = pasta.getId();
        Intent showDetailsIntent = new Intent();
        showDetailsIntent.setClass(PastaListActivity.this, PastaDetailsActivity.class);
        showDetailsIntent.putExtra(PastaDetailsActivity.EXTRA_PASTA_ID, currentPastaId);
        startActivity(showDetailsIntent);
    }
}
