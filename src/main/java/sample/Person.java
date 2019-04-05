package sample;

public class Person{
    String email;
    String first_name;
    String last_name;
    String mobile_number;

    public Person() {
    }

    public Person(String email, String first_name, String last_name, String mobile_number) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.mobile_number = mobile_number;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }


}
