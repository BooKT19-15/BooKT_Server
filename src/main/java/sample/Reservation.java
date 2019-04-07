package sample;

public class Reservation {

    private String additional_info;
    private String date;
    private String day;
    private String email;
    private String hour;
    private String location;
    private String mobile;
    private String month;
    private String name;
    private String restaurantName;
    private String sections;
    private String year;

    public Reservation() {
    }

    public Reservation(String additional_info, String date, String day, String email, String hour, String location, String mobile, String month, String name, String restaurantName, String sections, String year) {
        this.additional_info = additional_info;
        this.date = date;
        this.day = day;
        this.email = email;
        this.hour = hour;
        this.location = location;
        this.mobile = mobile;
        this.month = month;
        this.name = name;
        this.restaurantName = restaurantName;
        this.sections = sections;
        this.year = year;
    }

    public String getAdditional_info() {
        return additional_info;
    }

    public void setAdditional_info(String additional_info) {
        this.additional_info = additional_info;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getSections() {
        return sections;
    }

    public void setSections(String sections) {
        this.sections = sections;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
