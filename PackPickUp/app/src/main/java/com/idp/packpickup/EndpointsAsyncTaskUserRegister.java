package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.idp.api.userApi.UserApi;
import com.idp.api.userApi.model.User;

import java.io.IOException;

public class EndpointsAsyncTaskUserRegister extends AsyncTask<Void, Void, Boolean> {
    private static UserApi myApiService = null;
    private Context context;
    private User user = null;

    EndpointsAsyncTaskUserRegister(Context context, User user) {
        this.context = context;
        this.user = user;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            UserApi.Builder builder = new UserApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://peerless-fabric-93309.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            myApiService.insertUser(user).execute();
            //Toast.makeText(context, "registration succesufly", Toast.LENGTH_LONG).show();
            return true;
        } catch (IOException e) {
            //Toast.makeText(context, "error registering user", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
