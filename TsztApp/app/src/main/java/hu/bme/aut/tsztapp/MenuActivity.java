package hu.bme.aut.tsztapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    private String[] availableForDaily = new String[] { "52839", "52835", "52829", "52844", "52837", "52982", "52838" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ImageButton btnDaily = findViewById(R.id.btnDaily);
        btnDaily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                int rnd = r.nextInt(6);

                Intent showDetailsIntent = new Intent();
                showDetailsIntent.setClass(MenuActivity.this, PastaDetailsActivity.class);
                showDetailsIntent.putExtra(PastaDetailsActivity.EXTRA_PASTA_ID, availableForDaily[rnd]);
                startActivity(showDetailsIntent);
            }
        });

        ImageButton btnMenu = findViewById(R.id.btnMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listIntent = new Intent(MenuActivity.this, PastaListActivity.class);
                startActivity(listIntent);
            }
        });

        ImageButton btnFavourite = findViewById(R.id.btnFavourite);
        btnFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent favouriteIntent = new Intent(MenuActivity.this, FavouritesActivity.class);
                startActivity(favouriteIntent);
            }
        });
    }
}
