package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.idp.api.userApi.UserApi;
import com.idp.api.userApi.model.User;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class EndpointsAsyncTaskUserLogin extends AsyncTask<Void, Void, List<User>> {
    private static UserApi myApiService = null;
    private Context context;
    private String username;
    private String password;
    private boolean loginSuccess;

    EndpointsAsyncTaskUserLogin(Context context, String username, String password) {
        this.context = context;
        this.username = username;
        this.password = password;
        loginSuccess = false;
    }

    public boolean getLoginSuccess(){
        return loginSuccess;
    }

    @Override
    protected List<User> doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://peerless-fabric-93309.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.listUsers().execute().getItems();
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }

    /*@Override
    protected void onPostExecute(List<User> result) {
        boolean userExists = false;
        for (User q : result) {
            if (q.getUsername().equals(username)) {
                if (q.getPassword().equals(password)) {
                    Toast.makeText(context, "register", Toast.LENGTH_LONG).show();
                    loginSuccess = true;
                }
                else
                    Toast.makeText(context, "the password", Toast.LENGTH_LONG).show();
                userExists = true;
            }
        }
        if (!userExists)
            Toast.makeText(context, "the user doesn't exists", Toast.LENGTH_LONG).show();
    }*/
}
