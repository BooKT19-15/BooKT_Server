package sample;

public class MenuItem  {
    String description;
    String image;
    String name;
    String price;

    public MenuItem() {
    }

    public MenuItem(String description, String image, String name, String price) {
        this.description = description;
        this.image = image;
        this.name = name;
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
