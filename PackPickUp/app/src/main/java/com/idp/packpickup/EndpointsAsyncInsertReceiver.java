package com.idp.packpickup;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
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
            myApiService.insertReceiver(receiver).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
