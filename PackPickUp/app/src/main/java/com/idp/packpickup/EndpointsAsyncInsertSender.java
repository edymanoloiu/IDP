package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.idp.api.senderApi.SenderApi;
import com.idp.api.senderApi.model.Sender;

import java.io.IOException;

class EndpointsAsyncInsertSender extends AsyncTask<Void, Void, Boolean> {
    private static SenderApi myApiService = null;
    private Context context;
    private Sender sender = null;

    EndpointsAsyncInsertSender(Context context, Sender sender) {
        this.context = context;
        this.sender = sender;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            SenderApi.Builder builder = new SenderApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://peerless-fabric-93309.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            myApiService.insertSender(sender).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
