package hu.bme.aut.tsztapp.api;

import hu.bme.aut.tsztapp.model.Meal;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ListAPI {

    String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";

    @GET("filter.php?c=Pasta")
    Call<Meal> getPastaList();
}
