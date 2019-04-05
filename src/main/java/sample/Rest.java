package sample;

import java.util.ArrayList;

public class Rest  {

    private RestaurantDetails restaurantDetails;
    private Person person;
    private ArrayList<Image> imageList;
    private ArrayList<Menu> menuItems;
    sample.tableListSingle tableListSingle;
    sample.tableListFamily tableListFamily;

    public Rest() {
    }

    public Rest(RestaurantDetails restaurantDetails, Person person, ArrayList<Image> imageList, ArrayList<Menu> menuItems, sample.tableListSingle tableListSingle, sample.tableListFamily tableListFamily) {
        this.restaurantDetails = restaurantDetails;
        this.person = person;
        this.imageList = imageList;
        this.menuItems = menuItems;
        this.tableListSingle = tableListSingle;
        this.tableListFamily = tableListFamily;
    }

    public RestaurantDetails getRestaurantDetails() {
        return restaurantDetails;
    }

    public void setRestaurantDetails(RestaurantDetails restaurantDetails) {
        this.restaurantDetails = restaurantDetails;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Image> getImageList() {
        return imageList;
    }

    public void setImageList(ArrayList<Image> imageList) {
        this.imageList = imageList;
    }

    public ArrayList<Menu> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<Menu> menuItems) {
        this.menuItems = menuItems;
    }

    public sample.tableListSingle getTableListSingle() {
        return tableListSingle;
    }

    public void setTableListSingle(sample.tableListSingle tableListSingle) {
        this.tableListSingle = tableListSingle;
    }

    public sample.tableListFamily getTableListFamily() {
        return tableListFamily;
    }

    public void setTableListFamily(sample.tableListFamily tableListFamily) {
        this.tableListFamily = tableListFamily;
    }
}
