package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.idp.api.senderApi.SenderApi;
import com.idp.api.senderApi.model.Sender;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

class EndpointsAsyncTaskSender extends AsyncTask<Void, Void, List<Sender>> {
    private static SenderApi myApiService = null;
    private Context context;

    EndpointsAsyncTaskSender(Context context) {
        this.context = context;
    }

    @Override
    protected List<Sender> doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            SenderApi.Builder builder = new SenderApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://peerless-fabric-93309.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.listSenders().execute().getItems();
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void onPostExecute(List<Sender> result) {
        for (Sender q : result) {
            Toast.makeText(context, q.getCity() + " : " + q.getDate(), Toast.LENGTH_LONG).show();
        }
    }
}
