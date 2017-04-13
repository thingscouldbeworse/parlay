package com.parley.parley;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

public class idTask extends AsyncTask<Void, Void, String>
{

    private Context context;
    private static final String TAG = "Async ID retrieval";
    private String AccountName="default";
    private String senderID = "default";
    private String userEmail = "kirk.hardy@uky.edu";
    private String registrationID = "00";

    public void setContext(Context context_retrieve)
    {
        context = context_retrieve;
    }

    public void setAccountName(String name)
    {
        AccountName = name;
    }

    public void setSenderId(String id)
    {
        senderID = id;
    }

    public void setUserEmail(String email)
    {
        userEmail = email;
    }

    public void setRegistrationID(String ID)
    {
        registrationID = ID;
    }

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected String doInBackground(Void... params)
    {


        //String accountName = "kirk";
        com.google.firebase.auth.FirebaseUser test = FirebaseAuth.getInstance().getCurrentUser();
        Log.d(TAG, AccountName);


        // Initialize the scope in order to create device group
        final String scope = "audience:server:client_id:"
                + "102389610320-s4k60hkvcego5qd42pmqnvm0vf2fe95k.apps.googleusercontent.com";
        String idToken = null;
        try {
            idToken = GoogleAuthUtil.getToken(context, AccountName, scope);
            Log.d(TAG, idToken);
        } catch (Exception e) {
            Log.d(TAG, "exception while getting idToken: " + e);
        }
        String response = "";
        try{
            response = addNotificationKey(senderID, userEmail, registrationID, idToken);
        } catch(Exception except) {
            response = "Failed to retrieve ID";
        }
        return response;
    }
    /**
     * <p>Runs on the UI thread after {@link #doInBackground}. The
     * specified result is the value returned by {@link #doInBackground}.</p>
     *
     * <p>This method won't be invoked if the task was cancelled.</p>
     *
     * @param result The result of the operation computed by {@link #doInBackground}.
     *
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(String result) {
        if (result != null){
            Log.d(TAG, "I don't understand threads");
        }
    }

    public String addNotificationKey(
            String senderId, String userEmail, String registrationId, String idToken)
            throws IOException, JSONException {
        URL url = new URL("https://android.googleapis.com/gcm/googlenotification");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoOutput(true);

        // HTTP request header
        con.setRequestProperty("project_id", senderId);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");
        con.connect();

        // HTTP request
        JSONObject data = new JSONObject();
        data.put("operation", "add");
        data.put("notification_key_name", userEmail);
        data.put("registration_ids", new JSONArray(Arrays.asList(registrationId)));
        data.put("id_token", idToken);

        OutputStream os = con.getOutputStream();
        os.write(data.toString().getBytes("UTF-8"));
        os.close();

        // Read the response into a string
        InputStream is = con.getInputStream();
        String responseString = new Scanner(is, "UTF-8").useDelimiter("\\A").next();
        is.close();

        // Parse the JSON string and return the notification key
        JSONObject response = new JSONObject(responseString);
        return response.getString("notification_key");

    }
}