package sample;

public class RestaurantInfo  {

    private String restaurant_location;
    private String restaurant_name;
    private String restaurant_open;
    private String restaurant_close;
    private String restaurant_price;
    private String restaurant_image;
    private String firebase_id;

    public RestaurantInfo() {
    }

    public RestaurantInfo(String restaurant_location, String restaurant_name, String restaurant_open, String restaurant_close, String restaurant_price, String restaurant_image, String firebase_id) {
        this.restaurant_location = restaurant_location;
        this.restaurant_name = restaurant_name;
        this.restaurant_open = restaurant_open;
        this.restaurant_close = restaurant_close;
        this.restaurant_price = restaurant_price;
        this.restaurant_image = restaurant_image;
        this.firebase_id = firebase_id;
    }

    public String getRestaurant_location() {
        return restaurant_location;
    }

    public void setRestaurant_location(String restaurant_location) {
        this.restaurant_location = restaurant_location;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getRestaurant_open() {
        return restaurant_open;
    }

    public void setRestaurant_open(String restaurant_open) {
        this.restaurant_open = restaurant_open;
    }

    public String getRestaurant_close() {
        return restaurant_close;
    }

    public void setRestaurant_close(String restaurant_close) {
        this.restaurant_close = restaurant_close;
    }

    public String getRestaurant_price() {
        return restaurant_price;
    }

    public void setRestaurant_price(String restaurant_price) {
        this.restaurant_price = restaurant_price;
    }

    public String getRestaurant_image() {
        return restaurant_image;
    }

    public void setRestaurant_image(String restaurant_image) {
        this.restaurant_image = restaurant_image;
    }

    public String getFirebase_id() {
        return firebase_id;
    }

    public void setFirebase_id(String firebase_id) {
        this.firebase_id = firebase_id;
    }
}
