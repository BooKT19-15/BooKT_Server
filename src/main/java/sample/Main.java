package sample;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.*;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class Main {

    private static FirebaseOptions options ;
    private static FirebaseDatabase database ;
    private static DatabaseReference databaseReference ;
    private static FirebaseAuth firebaseAuth;
    private static final String username = "bookt19.15@gmail.com";
    private static final String password = "oma19152019";




    public static void main(String[] args)  {



        FileInputStream serviceAccount =
                null;
        try {
            serviceAccount = new FileInputStream("firebase_con.json");


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

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");


        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = database.getReference("Restaurants");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildName) {
                RestaurantDetails restaurantDetails = snapshot.child("restaurantDetails").getValue(RestaurantDetails.class);
                Person person = snapshot.child("person").getValue(Person.class);
                tableListSingle single = null;
                if(snapshot.child("tableListSingle").exists()) {
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

                ArrayList<Image> images = new ArrayList<Image>();

                for(int i=0;i<snapshot.child("imageList").getChildrenCount();i++){
                    images.add(new Image(snapshot.child("imageList").child(""+i).child("image").getValue(String.class)));
                }

                Rest rest = new Rest();
                if(restaurantDetails!=null){
                    rest.setRestaurantDetails(restaurantDetails);

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

                RestaurantInfo restaurantInfo = new RestaurantInfo(
                        restaurantDetails != null ? restaurantDetails.getRestaurant_location() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_name() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_open() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_close() : null
                        , restaurantDetails != null ? restaurantDetails.getRestaurant_price() : null
                        , images.get(0).getImage()
                        , restaurantDetails != null ? restaurantDetails.getFirebase_id() : null);

                DatabaseReference databaseReferenceinside;
                databaseReferenceinside = database.getReference("Country")
                        .child("Saudi Arabia")
                        .child("cities")
                        .child(rest.getRestaurantDetails().getRestaurant_city())
                        .child("Cuisine")
                        .child("Cuisine_names")
                        .child(rest.getRestaurantDetails().getRestaurant_cuisine());
                Type type = new Type(rest.getRestaurantDetails().getRestaurant_cuisine());

                databaseReferenceinside.setValueAsync(type);

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

                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("Reservation").child(rest.getRestaurantDetails().getFirebase_id()).child("sections").child("single");
                if (rest.getTableListSingle() != null) {

                    int [] max = new int[rest.getTableListSingle().getTableListSingle().size()];
                    for (int i = 0; i < rest.getTableListSingle().getTableListSingle().size(); i++) {
                        myRef.child("T" + rest.getTableListSingle().getTableListSingle().get(i).getSeatCount()).child("max").setValueAsync(rest.getTableListSingle().getTableListSingle().get(i).tableCount);
                        max[i] = Integer.parseInt(rest.getTableListSingle().getTableListSingle().get(i).getSeatCount());
                    }
                    String maxString = Arrays.toString(max);
                    maxString = maxString.substring(1,maxString.length()-1).replaceAll(" ","");
                    myRef = FirebaseDatabase.getInstance().getReference("Restaurants").child(rest.getRestaurantDetails().getFirebase_id()).child("tableListSingle").child("tables");
                    myRef.setValueAsync(maxString);
                }
                myRef = FirebaseDatabase.getInstance().getReference().child("Reservation").child(rest.getRestaurantDetails().getFirebase_id()).child("sections").child("family");
                if (rest.getTableListFamily() != null) {
                    int [] max = new int [rest.getTableListFamily().getTableListFamily().size()];
                    for (int i = 0; i < rest.getTableListFamily().getTableListFamily().size(); i++) {
                        myRef.child("T" + rest.getTableListFamily().getTableListFamily().get(i).getSeatCount()).child("max").setValueAsync(rest.getTableListFamily().getTableListFamily().get(i).tableCount);
                        max [i] = Integer.parseInt(rest.getTableListFamily().getTableListFamily().get(i).getSeatCount());
                    }
                    myRef = FirebaseDatabase.getInstance().getReference("Restaurants").child(rest.getRestaurantDetails().getFirebase_id()).child("tableListFamily").child("tables");
                    String maxString = Arrays.toString(max);
                    maxString = maxString.substring(1,maxString.length()-1).replaceAll(" ","");
                    myRef.setValueAsync(maxString);
                }


                myRef = FirebaseDatabase.getInstance().getReference("Search").child(rest.getRestaurantDetails().getFirebase_id());
                myRef.setValueAsync(new searchObject(rest.getRestaurantDetails().getRestaurant_name(),rest.getRestaurantDetails().getRestaurant_location(),rest.getRestaurantDetails().getRestaurant_cuisine()));
                System.out.println("done with restaurant "+rest.getRestaurantDetails().getFirebase_id());

                try {
               //     UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(rest.getPerson().email);


                Session session = Session.getInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication(username, password);
                            }
                        });
                try {

                    javax.mail.internet.MimeMessage message = new javax.mail.internet.MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(javax.mail.internet.MimeMessage.RecipientType.TO,
                            InternetAddress.parse("memeos_135@hotmail.com"));
                    message.setSubject("Bookt Team");
                    message.setText("Thanks for joining us\n we have reviewed your application please confirm your E-mail \n "+ firebaseAuth.generateEmailVerificationLink(rest.getPerson().email)+"\n Now you can login from our app Bookt-Owners ");




                    Transport.send(message);

                } catch (MessagingException e1) {
                    e1.printStackTrace();
                }


                } catch (FirebaseAuthException e1) {
                    e1.printStackTrace();
                }


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


        databaseReference = database.getReference("Users");

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        for(DataSnapshot dataSnapshot : dataSnapshot1.getChildren()) {
                            DataSnapshot ce = dataSnapshot;
                            dataSnapshot = dataSnapshot.child("reservations").child("active");
                            for (DataSnapshot dataSnapshot2 : dataSnapshot.getChildren()) {
                                for (DataSnapshot dataSnapshot3 : dataSnapshot2.getChildren()) {
                                    Reservation reservation = dataSnapshot3.getValue(Reservation.class);
                                    SimpleDateFormat time = new SimpleDateFormat("hh");
                                    SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
                                    try {

                                        long millis = System.currentTimeMillis();
                                        java.sql.Date date3 = new java.sql.Date(millis);
                                        String x[] = date3.toString().split("-");
                                        String e = x[1] + "/" + x[2] + "/" + x[0];
                                        Date cdate = date.parse(e);
                                        Date rdate = date.parse(reservation.getDate());
                                        Date ctime = new Date(Calendar.HOUR_OF_DAY);
                                        Date rtime = time.parse(reservation.getHour());
                                        if(cdate.equals(rdate)){
                                            if(ctime.after(rtime))
                                            FirebaseDatabase.getInstance().getReference("Users").child(ce.getKey()).child("reservations").child("inactive").child(dataSnapshot2.getKey()).child(dataSnapshot3.getKey()).setValueAsync(reservation);
                                            FirebaseDatabase.getInstance().getReference("Users").child(ce.getKey()).child("reservations").child("active").child(dataSnapshot2.getKey()).child(dataSnapshot3.getKey()).removeValueAsync();

                                        }
                                        else if(cdate.after(rdate)){
                                            FirebaseDatabase.getInstance().getReference("Users").child(ce.getKey()).child("reservations").child("inactive").child(dataSnapshot2.getKey()).child(dataSnapshot3.getKey()).setValueAsync(reservation);
                                            FirebaseDatabase.getInstance().getReference("Users").child(ce.getKey()).child("reservations").child("active").child(dataSnapshot2.getKey()).child(dataSnapshot3.getKey()).removeValueAsync();
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        },0,1,TimeUnit.HOURS);



        FirebaseDatabase.getInstance().getReference("QueueList").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Restaurant restaurant = dataSnapshot.getValue(Restaurant.class);
                Message message = Message.builder()
                        .setToken("cz9dMZJujJ4:APA91bFOTitRqMgWrW-Z68OE4vweYTQzzB9cqzSR6FTzj_dAbS0eVR8XZ4R4aJoTMMOCVT7cdUsQ7_w5jTmThDcVARyDlW1KT8DjrASh8W678D04RC6MMlCUAfHTubnoOcvaQ6EHt-wz")
                        .setAndroidConfig(AndroidConfig.builder().setNotification(AndroidNotification.builder().setTitle("New Restaurant on the queueList").setBody(restaurant.getRestaurant_name()).build()).build()).build();

                FirebaseMessaging.getInstance().sendAsync(message);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });









        while (true);
    }
}
