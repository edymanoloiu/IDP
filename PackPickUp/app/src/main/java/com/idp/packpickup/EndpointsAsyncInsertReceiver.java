package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.idp.api.receiverApi.ReceiverApi;
import com.idp.api.receiverApi.model.Receiver;

import java.io.IOException;

class EndpointsAsyncInsertReceiver extends AsyncTask<Void, Void, Boolean> {
    private static ReceiverApi myApiService = null;
    private Context context;
    private Receiver receiver = null;

    EndpointsAsyncInsertReceiver(Context context, Receiver receiver) {
        this.context = context;
        this.receiver = receiver;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            ReceiverApi.Builder builder = new ReceiverApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://peerless-fabric-93309.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        try {
            myApiService.insertReceiver(receiver).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
