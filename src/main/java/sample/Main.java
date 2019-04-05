package sample;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {

    private static FirebaseOptions options ;
    private static FirebaseDatabase database ;
    private static DatabaseReference databaseReference ;




    public static void main(String[] args) {



        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("C:\\Users\\amuri\\Desktop\\Bookt-server\\src\\main\\resources\\firebase_con.json");


            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://bookt-a9889.firebaseio.com")
                    .build();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        FirebaseApp.initializeApp(options);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Restaurants");





//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//
//
//            }
//
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                System.out.println(snapshot);
                RestaurantDetails restaurantDetails = snapshot.child("restaurantDetails").getValue(RestaurantDetails.class);
                System.out.println("working here");
                Person person = snapshot.child("person").getValue(Person.class);
                System.out.println("working in person");
                tableListSingle single = null;
                if(snapshot.child("tableListSingle").exists()) {
                    System.out.println("working inside table single");
                    single = new tableListSingle();
                    single.setTables( snapshot.child("tableListSingle").child("tables").getValue(String.class));
                    ArrayList<Table> n = new ArrayList<Table>();
                    for(int i=0;i<snapshot.child("tableListSingle").getChildrenCount()-1;i++){
                        String x = snapshot.child("tableListSingle").child(""+i).child("seatCount").getValue(String.class);
                        String y = snapshot.child("tableListSingle").child(Integer.toString(i)).child("tableCount").getValue(String.class);
                        n.add(new Table(x,y));
                    }
                    single.setTableListSingle(n);
                }
                System.out.println("working aftre single");
                tableListFamily family = null;
                ArrayList<Table> e = new ArrayList<Table>();
                if(snapshot.child("tableListFamily").exists()) {
                    family = new tableListFamily();
                    family.setTables( snapshot.child("tableListFamily").child("tables").getValue(String.class));
                    for(int i=0;i<snapshot.child("tableListFamily").getChildrenCount()-1;i++){
                        String x = snapshot.child("tableListFamily").child(""+i).child("seatCount").getValue(String.class);
                        String y = snapshot.child("tableListFamily").child(Integer.toString(i)).child("tableCount").getValue(String.class);
                        e.add(new Table(x,y));
                    }
                    family.setTableListFamily(e);
                }
                System.out.println("working aftre family");
                ArrayList<Image> images = new ArrayList<Image>();
                System.out.println(snapshot.child("imageList").getChildrenCount());
                for(int i=0;i<snapshot.child("imageList").getChildrenCount();i++){
                    images.add(new Image(snapshot.child("imageList").child(""+i).child("image").getValue(String.class)));
                }
                System.out.println("working after images");
                Rest rest = new Rest();
                if(restaurantDetails!=null){
                    rest.setRestaurantDetails(restaurantDetails);
                    System.out.println("restaurantDetails  is not null");
                }
                if(person!=null){
                    rest.setPerson(person);
                }
                if(single!=null){
                    rest.setTableListSingle(single);
                }
                if(family!=null){
                    rest.setTableListFamily(family);
                }
                rest.setImageList(images);
                System.out.println("rest setting image list");
                RestaurantInfo restaurantInfo = new RestaurantInfo(
                        restaurantDetails != null ? restaurantDetails.getRestaurant_location() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_name() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_open() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_close() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_price() : null
                        , images.get(0).getImage()
                        , restaurantDetails != null ? restaurantDetails.getFirebase_id() : null);
                System.out.println("working aftre initializing restaurantInfo");
                DatabaseReference databaseReferenceinside;
                databaseReferenceinside = database.getReference("Country")
                        .child("Saudi Arabia")
                        .child("cities")
                        .child(rest.getRestaurantDetails().getRestaurant_city())
                        .child("Cuisine")
                        .child("Cuisine_names")
                        .child(rest.getRestaurantDetails().getRestaurant_cuisine());
                Type type = new Type(rest.getRestaurantDetails().getRestaurant_cuisine());
                System.out.println("working aftre initializing type");
                databaseReferenceinside.setValueAsync(type);
                System.out.println("working aftre setting the type");
                databaseReferenceinside = database.getReference("Country")
                        .child("Saudi Arabia")
                        .child("cities")
                        .child(rest.getRestaurantDetails().getRestaurant_city())
                        .child("Cuisine")
                        .child("ids")
                        .child(rest.getRestaurantDetails().getRestaurant_cuisine())
                        .child(rest.getRestaurantDetails().getFirebase_id())
                        .child("restaurant_info");

                databaseReferenceinside.setValueAsync(restaurantInfo);
                System.out.println("working aftre setValueAsync(restaurantInfo)");
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Reservation").child(rest.getRestaurantDetails().getFirebase_id()).child("sections").child("single");
                if (rest.getTableListSingle() != null) {
                    System.out.println("working inside single list max");
                    int [] max = new int[rest.getTableListSingle().getTableListSingle().size()];
                    for (int i = 0; i < rest.getTableListSingle().getTableListSingle().size(); i++) {
                        myRef.child("T" + rest.getTableListSingle().getTableListSingle().get(i).getSeatCount()).child("max").setValueAsync(rest.getTableListSingle().getTableListSingle().get(i).tableCount);
                        max[i] = Integer.parseInt(rest.getTableListSingle().getTableListSingle().get(i).getSeatCount());
                    }
                    System.out.println("working aftre single list max");
                    String maxString = Arrays.toString(max);
                    maxString = maxString.substring(1,maxString.length()-1);
                    myRef = FirebaseDatabase.getInstance().getReference("Restaurants").child(rest.getRestaurantDetails().getFirebase_id()).child("tableListSingle").child("tables");
                    myRef.setValueAsync(maxString);
                }
                myRef = FirebaseDatabase.getInstance().getReference().child("Reservation").child(rest.getRestaurantDetails().getFirebase_id()).child("sections").child("family");
                if (rest.getTableListFamily() != null) {
                    System.out.println("working inside family list max");
                    int [] max = new int [rest.getTableListFamily().getTableListFamily().size()];
                    for (int i = 0; i < rest.getTableListFamily().getTableListFamily().size(); i++) {
                        myRef.child("T" + rest.getTableListFamily().getTableListFamily().get(i).getSeatCount()).child("max").setValueAsync(rest.getTableListFamily().getTableListFamily().get(i).tableCount);
                        max [i] = Integer.parseInt(rest.getTableListFamily().getTableListFamily().get(i).getSeatCount());
                    }
                    System.out.println("working aftre family list max ");
                    myRef = FirebaseDatabase.getInstance().getReference("Restaurants").child(rest.getRestaurantDetails().getFirebase_id()).child("tableListFamily").child("tables");
                    String maxString = Arrays.toString(max);
                    maxString = maxString.substring(1,maxString.length()-1);
                    myRef.setValueAsync(maxString);
                    System.out.println("working aftre setValueAsync(max)");
                }


                myRef = FirebaseDatabase.getInstance().getReference("Search").child(rest.getRestaurantDetails().getFirebase_id());
                myRef.setValueAsync(new searchObject(rest.getRestaurantDetails().getRestaurant_name(),rest.getRestaurantDetails().getRestaurant_location(),rest.getRestaurantDetails().getRestaurant_cuisine()));





            }

            @Override
            public void onChildChanged(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot snapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println(error);
            }
        });

//        FirebaseMessaging.getInstance().send(Message.builder().setTopic("hello").build());
//
//        FirebaseMessaging.getInstance().sendAsync(Message.builder().setTopic("hello").build());



        while (true);
    }
}
