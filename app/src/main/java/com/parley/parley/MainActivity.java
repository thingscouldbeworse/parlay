package com.parley.parley;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int SIGN_IN_REQUEST = 1;
    private int currentYear;
    Date date = new Date();
    private FirebaseListAdapter<ChatMessage> adapter;
    private DatabaseReference parley = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //user sign in
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            // Start sign in/sign up activity
            startActivityForResult(
                    AuthUI.getInstance().createSignInIntentBuilder().build(),
                    SIGN_IN_REQUEST
            );
        } else {

            //save current year
            currentYear = date.getYear();

            // Load chat room contents
            displayChatMessages();
        }


        //to post new message by clicking on the send button
        FloatingActionButton send =
                (FloatingActionButton) findViewById(R.id.send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText message = (EditText) findViewById(R.id.message);

                // Read the input field and push a new instance
                // of com.parley.parley.ChatMessage to the Firebase database
                parley.child("messages").push().setValue(new ChatMessage(message.getText()
                        .toString(), FirebaseAuth.getInstance().getCurrentUser().getDisplayName())
                );

                // Clear the input
                message.setText("");
            }
        });


        //opens up the settings activity page if settingsButton is clicked
        ImageButton setting = (ImageButton) findViewById(R.id.settingsButton);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(MainActivity.this, Settings.class));
            }
        });

        //opens up menu if the menu button is selected to allow the user to logout or delete all messages
        //in the database

        ImageButton menuButton = (ImageButton) findViewById(R.id.menuButton);

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //shows the menu options


                final AlertDialog.Builder menuBuild = new AlertDialog.Builder(MainActivity.this);
                menuBuild.setTitle("MENU");

                //creates the listener if the user presses the sign out button
                menuBuild.setNegativeButton(R.string.sign_out_caps, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AuthUI.getInstance().signOut(MainActivity.this).addOnCompleteListener(new
                                                                                                      OnCompleteListener<Void>() {
                                                                                                          @Override
                                                                                                          public void onComplete(@NonNull Task<Void> task) {
                                                                                                              Toast.makeText(MainActivity.this, "You have signed out of Parley.",
                                                                                                                      Toast.LENGTH_LONG).show();
                                                                                                          }
                                                                                                      });
                        finish();
                    }
                });

                //creates the listener if the user presses the delete all button
                menuBuild.setPositiveButton(R.string.delete_all_caps, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        parley.removeValue();
                        dialog.dismiss();
                    }
                });

                //creates the listener if the user presses the cancel button
                menuBuild.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                menuBuild.create();
                menuBuild.show();
                //allow user to delete messages on device
                //@Override
                // protected void onListItemClick(ListView clicked, View view, int position, long id){a
                //ChatMessage deletedMessage;
                //deletedMessage = getListView().

            }
        });
    }


    //displays the messages
    private void displayChatMessages() {
        final ListView chatMessages = (ListView) findViewById(R.id.chat_messages);
        //allows each individual message to be clicked
        chatMessages.setClickable(true);

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                R.layout.message, parley.child("messages")) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                // Get references to the views of message.xml
                TextView messText = (TextView) v.findViewById(R.id.mess_text);
                TextView messUser = (TextView) v.findViewById(R.id.mess_user);
                TextView messTime = (TextView) v.findViewById(R.id.mess_time);

                //test the message text to ensure it is not a blank message
                //String message;
                //message = model.getMessageText();

                //if ((message != null) && (!message.isEmpty())){

                // Set their text to the message
                messText.setText(model.getMessText());

                //Decide which text Bubble shape to show or to show none if no box is checked
                SharedPreferences settings = getSharedPreferences("prefs", MODE_PRIVATE);
                boolean circleChecked = settings.getBoolean("circle",false);
                boolean starChecked = settings.getBoolean("star",false);
                boolean triangleChecked = settings.getBoolean("triangle",false);
                boolean hexagonChecked = settings.getBoolean("hexagon",false);
                boolean quoteChecked = settings.getBoolean("quote", false);

                // Retrieve font info
                Float fontSize = settings.getFloat("fontSize", 18);
                Integer fontColorIndex = settings.getInt("colorIndex", 0);
                int[] colorWheel = getApplicationContext().getResources().getIntArray(R.array.colorReferences);
                int fontColor = colorWheel[fontColorIndex];
                String fontStyle = settings.getString("fontStyle", "sans-serif");

                // Retrieve background info
                Integer backgroundColorIdx = settings.getInt("backgroundColor", 0);
                int[] backgroundColorWheel = getApplicationContext().getResources().getIntArray(R.array.backgroundReferences);
                int backgroundColor = backgroundColorWheel[backgroundColorIdx];

                //Retrieve text Bubble color
                Integer bubbleColorIndex = settings.getInt("bubbleColor", 6);

                // Change message view
                if (circleChecked) {
                    switch(bubbleColorIndex){
                        case 0:
                            messText.setBackgroundResource(R.drawable.circle9);
                            break;
                        case 1:
                            messText.setBackgroundResource(R.drawable.circlered9);
                            break;
                        case 2:
                            messText.setBackgroundResource(R.drawable.circleorange9);
                            break;
                        case 3:
                            messText.setBackgroundResource(R.drawable.circlegreen9);
                            break;
                        case 4:
                            messText.setBackgroundResource(R.drawable.circleblue9);
                            break;
                        case 5:
                            messText.setBackgroundResource(R.drawable.circlepurple9);
                            break;
                        default:
                            messText.setBackgroundResource(R.drawable.circleblack9);
                    }
                }
                else if (starChecked) {
                    switch (bubbleColorIndex) {
                        case 0:
                            messText.setBackgroundResource(R.drawable.star9);
                            break;
                        case 1:
                            messText.setBackgroundResource(R.drawable.starred9);
                            break;
                        case 2:
                            messText.setBackgroundResource(R.drawable.starorange9);
                            break;
                        case 3:
                            messText.setBackgroundResource(R.drawable.stargreen9);
                            break;
                        case 4:
                            messText.setBackgroundResource(R.drawable.starblue9);
                            break;
                        case 5:
                            messText.setBackgroundResource(R.drawable.starpurple9);
                            break;
                        default:
                            messText.setBackgroundResource(R.drawable.starblack9);
                    }
                }
                else if (triangleChecked) {
                    switch (bubbleColorIndex) {
                        case 0:
                            messText.setBackgroundResource(R.drawable.triangle9);
                            break;
                        case 1:
                            messText.setBackgroundResource(R.drawable.trianglered9);
                            break;
                        case 2:
                            messText.setBackgroundResource(R.drawable.triangleorange9);
                            break;
                        case 3:
                            messText.setBackgroundResource(R.drawable.trianglegreen9);
                            break;
                        case 4:
                            messText.setBackgroundResource(R.drawable.triangleblue9);
                            break;
                        case 5:
                            messText.setBackgroundResource(R.drawable.trianglepurple9);
                            break;
                        default:
                            messText.setBackgroundResource(R.drawable.triangleblack9);
                    }
                }
                else if (hexagonChecked) {
                    switch(bubbleColorIndex) {
                        case 0:
                            messText.setBackgroundResource(R.drawable.hexagon9);
                            break;
                        case 1:
                            messText.setBackgroundResource(R.drawable.hexagonred9);
                            break;
                        case 2:
                            messText.setBackgroundResource(R.drawable.hexagonorange9);
                            break;
                        case 3:
                            messText.setBackgroundResource(R.drawable.hexagongreen9);
                            break;
                        case 4:
                            messText.setBackgroundResource(R.drawable.hexagonblue9);
                            break;
                        case 5:
                            messText.setBackgroundResource(R.drawable.hexagonpurple9);
                            break;
                        default:
                            messText.setBackgroundResource(R.drawable.hexagonblack9);
                    }
                }
                else if (quoteChecked) {
                    switch(bubbleColorIndex) {
                        case 0:
                            messText.setBackgroundResource(R.drawable.quote9);
                            break;
                        case 1:
                            messText.setBackgroundResource(R.drawable.quotered9);
                            break;
                        case 2:
                            messText.setBackgroundResource(R.drawable.quoteorange9);
                            break;
                        case 3:
                            messText.setBackgroundResource(R.drawable.quotegreen9);
                            break;
                        case 4:
                            messText.setBackgroundResource(R.drawable.quoteblue9);
                            break;
                        case 5:
                            messText.setBackgroundResource(R.drawable.quotepurple9);
                            break;
                        default:
                            messText.setBackgroundResource(R.drawable.quoteblack9);
                    }
                }
              
                chatMessages.setBackgroundColor(backgroundColor);

                messText.setTextSize(TypedValue.COMPLEX_UNIT_SP, fontSize);
                messText.setTextColor(fontColor);
                messText.setTypeface(Typeface.create(fontStyle, Typeface.NORMAL));

                String colorString = "#" + settings.getString("background_color", "FFFFFF");
                if(colorString.length() != 7 || !colorString.startsWith("#"))
                    colorString = "#FFFFFF";
                chatMessages.setBackgroundColor(Color.parseColor(String.valueOf(colorString)));


                messUser.setText(model.getMessUser());

                // Format the date before showing it
                String timeString = dateString(model.getMessTime());
                messTime.setText(timeString);
            }
        };


        chatMessages.setAdapter(adapter);

        //allow user to delete all messages on device and in Firebase database
        chatMessages.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> parent, View view, final int position,
                                           long id) {
                //shows the delete options
                final AlertDialog.Builder deleteBuild = new AlertDialog.Builder(MainActivity.this);
                deleteBuild.setTitle("DELETE");

                //creates the listener if the user presses the delete all button
                deleteBuild.setNegativeButton(R.string.delete_all_caps, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        parley.removeValue();
                        dialog.dismiss();
                    }
                });

                //creates the listener if the user presses the delete this message button
                //not currently implemented because hard to retrieve unique ID generated by push
                deleteBuild.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                //creates the listener if the user presses the cancel button
                deleteBuild.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });

                deleteBuild.create();
                deleteBuild.show();
                return true;
            }
        });
    }


    //once user has signed in
    @Override
    protected void onActivityResult(int request, int result, Intent data) {

        super.onActivityResult(request, result, data);

        //Display welcome toast if succesful sign in and load the chat messages
        if (request == SIGN_IN_REQUEST) {
            if (result == RESULT_OK) {
                Toast.makeText(this, "Successfully signed in. Welcome to Parley!",
                        Toast.LENGTH_LONG).show();

                //load the Chat Messages
                displayChatMessages();

                //Display error message if unsuccesful sign in
            } else {
                Toast.makeText(this,
                        "Sorry. We couldn't sign you into Parley. Please try again later.",
                        Toast.LENGTH_LONG).show();

                // Close the app
                finish();
            }
        }

    }

   public boolean isCurrentYear(int year) {
        if (year == currentYear)
            return true;
        return false;
    }

    public String dateString(long messageDateLong) {
        String day = null;
        Date messageDate = new Date(messageDateLong);
        int previousYear = messageDate.getYear();


            if (!isCurrentYear(previousYear)) {
                day = DateFormat.format("M/dd/yyyy h:mm a", messageDateLong).toString();
            }

            else {
                day = DateFormat.format("M/dd h:mm a", messageDateLong).toString();
            }







        messageDate = date;


        return day;
    }
}