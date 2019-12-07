package hu.bme.aut.tsztapp.model;

public class DetailedMeal {

    private DetailedPasta meals[];

    public DetailedMeal(DetailedPasta[] meals) {
        this.meals = meals;
    }

    public DetailedPasta[] getMeals() {
        return meals;
    }
}
