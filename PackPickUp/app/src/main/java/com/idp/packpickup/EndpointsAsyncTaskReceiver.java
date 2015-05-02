package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.idp.api.receiverApi.ReceiverApi;
import com.idp.api.receiverApi.model.Receiver;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

class EndpointsAsyncTaskReceiver extends AsyncTask<Void, Void, List<Receiver>> {
    private static ReceiverApi myApiService = null;
    private Context context;

    EndpointsAsyncTaskReceiver(Context context) {
        this.context = context;
    }

    @Override
    protected List<Receiver> doInBackground(Void... params) {
        if (myApiService == null) { // Only do this once
            ReceiverApi.Builder builder = new ReceiverApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://peerless-fabric-93309.appspot.com/_ah/api/");

            myApiService = builder.build();
        }

        try {
            return myApiService.listReceivers().execute().getItems();
        } catch (IOException e) {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    protected void onPostExecute(List<Receiver> result) {
        for (Receiver q : result) {
            Toast.makeText(context, q.getStartCity() + " : " + q.getDestination() + " : " + q.getDate(), Toast.LENGTH_LONG).show();
        }
    }
}
