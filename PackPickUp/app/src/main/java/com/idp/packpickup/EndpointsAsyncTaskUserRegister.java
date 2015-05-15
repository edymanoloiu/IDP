package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.idp.api.userApi.UserApi;
import com.idp.api.userApi.model.User;

import java.io.IOException;

public class EndpointsAsyncTaskUserRegister extends AsyncTask<Void, Void, Boolean> {
    private static UserApi myApiService = null;
    private User user = null;

    EndpointsAsyncTaskUserRegister(User user) {
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
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
