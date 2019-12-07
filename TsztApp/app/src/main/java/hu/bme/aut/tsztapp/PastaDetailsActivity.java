package hu.bme.aut.tsztapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import hu.bme.aut.tsztapp.api.ListAPI;
import hu.bme.aut.tsztapp.api.PastaIdAPI;
import hu.bme.aut.tsztapp.model.DetailedMeal;
import hu.bme.aut.tsztapp.model.DetailedPasta;
import hu.bme.aut.tsztapp.model.Meal;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PastaDetailsActivity extends AppCompatActivity {

    public static final String EXTRA_PASTA_ID = "extra.pasta_id";
    private String pastaId;
    private DetailedPasta detailedPasta;

    Boolean isSetFav = false;

    TextView pastaName;
    TextView area;
    TextView instructions;
    TextView ingr1;
    TextView ingr2;
    TextView ingr3;
    TextView ingr4;
    TextView ingr5;
    ImageView img;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_detail);

        pastaId = getIntent().getStringExtra(EXTRA_PASTA_ID);

        pastaName = (TextView) findViewById(R.id.nameText);
        area = (TextView) findViewById(R.id.areaText);
        instructions = (TextView) findViewById(R.id.instText);
        ingr1 = (TextView) findViewById(R.id.ingr1Text);
        ingr2 = (TextView) findViewById(R.id.ingr2Text);
        ingr3 = (TextView) findViewById(R.id.ingr3Text);
        ingr4 = (TextView) findViewById(R.id.ingr4Text);
        ingr5 = (TextView) findViewById(R.id.ingr5Text);

        img = (ImageView) findViewById(R.id.pastaImage);

        btn = (Button) findViewById(R.id.addToFavButton);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PastaIdAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PastaIdAPI pastaIdAPI = retrofit.create(PastaIdAPI.class);

        Call<DetailedMeal> call = pastaIdAPI.getPastaById(pastaId);

        call.enqueue(new Callback<DetailedMeal>() {
            @Override
            public void onResponse(Call<DetailedMeal> call, Response<DetailedMeal> response) {
                DetailedMeal meal = response.body();

                detailedPasta = meal.getMeals()[0];

                pastaName.setText(detailedPasta.getStrMeal());
                area.setText(detailedPasta.getStrArea());
                instructions.setText(detailedPasta.getStrInstructions());

                String ing1 = detailedPasta.getStrIngredient1() + " " + detailedPasta.getStrMeasure1();
                ingr1.setText(ing1);

                String ing2 = detailedPasta.getStrIngredient2() + " " + detailedPasta.getStrMeasure2();
                ingr2.setText(ing2);

                String ing3 = detailedPasta.getStrIngredient3() + " " + detailedPasta.getStrMeasure3();
                ingr3.setText(ing3);

                String ing4 = detailedPasta.getStrIngredient4() + " " + detailedPasta.getStrMeasure4();
                ingr4.setText(ing4);

                String ing5 = detailedPasta.getStrIngredient5() + " " + detailedPasta.getStrMeasure5();
                ingr5.setText(ing5);

                Picasso.get().load(detailedPasta.getStrMealTHumb()).into(img);
            }

            @Override
            public void onFailure(Call<DetailedMeal> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isSetFav) {
                    isSetFav = true;
                } else {
                    Toast.makeText(getApplicationContext(), "This pasta has already been added to your favourites!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
