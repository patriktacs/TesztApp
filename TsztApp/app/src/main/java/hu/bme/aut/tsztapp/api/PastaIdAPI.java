package hu.bme.aut.tsztapp.api;

import hu.bme.aut.tsztapp.model.DetailedMeal;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PastaIdAPI {

    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @GET("lookup.php")
    Call<DetailedMeal> getPastaById(@Query("i") String id);
}

