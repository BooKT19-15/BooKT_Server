package sample;

import java.util.ArrayList;

public class Menu  {

    String menuCategory;
    ArrayList<MenuItem> menuItems;

    public Menu() {
    }

    public Menu(String menuCategory, ArrayList<MenuItem> menuItems) {
        this.menuCategory = menuCategory;
        this.menuItems = menuItems;
    }




    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


}
