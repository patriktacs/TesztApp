package hu.bme.aut.tsztapp.model;

import com.google.gson.annotations.SerializedName;

public class Pasta {

    @SerializedName("strMeal")
    private String name;
    @SerializedName("strThumb")
    private String imageURL;
    @SerializedName("idMeal")
    private String id;

    public Pasta(String name, String imageURL, String id) {
        this.name = name;
        this.imageURL = imageURL;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getId() {
        return id;
    }
}
