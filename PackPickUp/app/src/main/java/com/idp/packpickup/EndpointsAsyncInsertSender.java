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
import com.idp.api.senderApi.SenderApi;
import com.idp.api.senderApi.model.Sender;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
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
        if(myApiService == null) { // Only do this once
            SenderApi.Builder builder = new SenderApi.Builder(AndroidHttp.newCompatibleTransport(),
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
            myApiService.insertSender(sender).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
