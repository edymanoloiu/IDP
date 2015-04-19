package com.idp.packpickup;

/**
 * Created by Edi on 4/19/2015.
 */

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
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
        if(myApiService == null) { // Only do this once
            ReceiverApi.Builder builder = new ReceiverApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
// options for running against local devappserver
// - 10.0.2.2 is localhost's IP address in Android emulator
// - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
// end options for devappserver

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
