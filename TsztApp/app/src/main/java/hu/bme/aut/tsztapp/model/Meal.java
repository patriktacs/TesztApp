package hu.bme.aut.tsztapp.model;

public class Meal {

    private Pasta meals[];

    public Meal(Pasta[] meals) {
        this.meals = meals;
    }

    public Pasta[] getMeals() {
        return meals;
    }
}
