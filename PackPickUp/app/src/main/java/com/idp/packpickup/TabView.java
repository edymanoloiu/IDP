package com.idp.packpickup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Base64;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.idp.api.receiverApi.model.Receiver;
import com.idp.api.senderApi.model.Sender;
import com.microsoft.windowsazure.messaging.NotificationHub;
import com.microsoft.windowsazure.notifications.NotificationsManager;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import static com.idp.packpickup.MyHandler.tabView;


public class TabView extends Activity {
    private TabHost tabHost;
    private ViewFlipper switcher;
    private Button RequestSender, RequestDeliver, Previous, Previous2;
    private boolean isDeliver;
    private ArrayAdapter<String> ArrayAdapterSender, ArrayAdapterDeliver;
    private String notificationMessage;
    private static String username = null;
    private String[] items = new String[]{
            "Arad",
            "Bucuresti",
            "Buftea",
            "Busteni",
            "Braila",
            "Buzau",
            "Focsani"
    };
    private String SENDER_ID = "488445484034";
    private GoogleCloudMessaging gcm;
    private NotificationHub hub;
    private String HubName = "packpickupnotificationhub";
    private String HubListenConnectionString = "Endpoint=sb://packpickupnotificationhub-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultListenSharedAccessSignature;SharedAccessKey=mm4USW+F5ggL4/yj9eNumacmKiT3ZpK94Mxe5KK6Ffs=";
    private String HubEndpoint = null;
    private String HubSasKeyName = null;
    private String HubSasKeyValue = null;
    private String HubFullAccess = "Endpoint=sb://packpickupnotificationhub-ns.servicebus.windows.net/;SharedAccessKeyName=DefaultFullSharedAccessSignature;SharedAccessKey=uMq2U0gtlvSjAKpWcFxRfl2MU/tGflwnGXXJ+8ZcTuQ=";
    private static String phoneNumber = null;
    private String destionationPhoneNumber;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null && (username == null && phoneNumber == null)) {
            username = extras.getString("username");
            //phoneNumber = extras.getString("phone");
            phoneNumber = "+40733981234";
        }

        tabView = this;

        NotificationsManager.handleNotifications(this, SENDER_ID, MyHandler.class);
        gcm = GoogleCloudMessaging.getInstance(this);
        hub = new NotificationHub(HubName, HubListenConnectionString, this);
        registerWithNotificationHubs();

        switcher = (ViewFlipper) findViewById(R.id.ViewSwitcher);
        RequestSender = (Button) findViewById(R.id.button);
        RequestDeliver = (Button) findViewById(R.id.button2);
        Previous = (Button) findViewById(R.id.Previous);
        Previous2 = (Button) findViewById(R.id.Previous2);
        isDeliver = false;
        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec("Send");
        spec1.setContent(R.id.tab1);
        spec1.setIndicator("Send package");

        TabHost.TabSpec spec2 = tabHost.newTabSpec("Deliver");
        spec2.setIndicator("Deliver package");
        spec2.setContent(R.id.tab2);

        Spinner dropdown_from = (Spinner) findViewById(R.id.spinner1);
        Spinner dropdown_to = (Spinner) findViewById(R.id.spinner12);
        Spinner dropdown_from_rec = (Spinner) findViewById(R.id.spinner2);
        Spinner dropdown_to_rec = (Spinner) findViewById(R.id.spinner21);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);
        dropdown_from.setAdapter(adapter);
        dropdown_to.setAdapter(adapter);
        dropdown_from_rec.setAdapter(adapter);
        dropdown_to_rec.setAdapter(adapter);

        tabHost.addTab(spec1);
        tabHost.addTab(spec2);

        RequestSender.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                isDeliver = false;
                TextView text = (TextView) findViewById(R.id.t2);
                try {
                    populateLists();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ListView listSender = (ListView) findViewById(R.id.listView);
                listSender.setAdapter(ArrayAdapterSender);
                new AnimationUtils();
                switcher.setAnimation(AnimationUtils.makeInAnimation
                        (getBaseContext(), true));
                switcher.setDisplayedChild(1);
            }
        });

        RequestDeliver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                isDeliver = true;
                TextView text = (TextView) findViewById(R.id.t2);
                text.setText("Senders list:");
                try {
                    populateLists();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ListView listSender = (ListView) findViewById(R.id.listView);
                listSender.setAdapter(ArrayAdapterDeliver);
                new AnimationUtils();
                switcher.setAnimation(AnimationUtils.makeInAnimation
                        (getBaseContext(), true));
                switcher.setDisplayedChild(1);
            }
        });

        Previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new AnimationUtils();
                switcher.setAnimation(AnimationUtils.makeInAnimation
                        (getBaseContext(), true));
                switcher.setDisplayedChild(0);
            }
        });

        Previous2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText edittext = (EditText) findViewById(R.id.text);
                edittext.setText(null);
                new AnimationUtils();
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(edittext.getWindowToken(), 0);
                switcher.setAnimation(AnimationUtils.makeInAnimation
                        (getBaseContext(), true));
                switcher.setDisplayedChild(1);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void populateLists() throws ExecutionException, InterruptedException {
        try {
            Spinner plecareSender = (Spinner) findViewById(R.id.spinner1);
            Spinner destinatieSender = (Spinner) findViewById(R.id.spinner12);
            Spinner plecareDeliver = (Spinner) findViewById(R.id.spinner2);
            Spinner destinatieDeliver = (Spinner) findViewById(R.id.spinner21);
            DatePicker dataSender = (DatePicker) findViewById(R.id.datePicker);
            String selectedDateSender = (dataSender.getDayOfMonth() < 10 ? "0" + dataSender.getDayOfMonth() : dataSender.getDayOfMonth()) + "/" + (dataSender.getMonth() + 1 < 10 ? "0" + (dataSender.getMonth() + 1) : dataSender.getMonth()) + "/" + dataSender.getYear();
            DatePicker dataDeliver = (DatePicker) findViewById(R.id.datePicker2);
            String selectedDateDeliver = (dataDeliver.getDayOfMonth() < 10 ? "0" + dataDeliver.getDayOfMonth() : dataDeliver.getDayOfMonth()) + "/" + (dataDeliver.getMonth() + 1 < 10 ? "0" + (dataDeliver.getMonth() + 1) : dataDeliver.getMonth()) + "/" + dataDeliver.getYear();
            String senderStartCity = plecareSender.getSelectedItem().toString();
            String senderDestinationCity = destinatieSender.getSelectedItem().toString();
            String deliverStartCity = plecareDeliver.getSelectedItem().toString();
            String deliverDestinationCity = destinatieDeliver.getSelectedItem().toString();

            if (isDeliver) {

                //insert deliver into DB
                Receiver receiver = new Receiver();
                receiver.setPhoneNumber(phoneNumber);
                receiver.setUsername(username);
                receiver.setDate(selectedDateDeliver);
                receiver.setDestination(deliverDestinationCity);
                receiver.setStartCity(deliverStartCity);
                new EndpointsAsyncInsertReceiver(this, receiver).execute();
                //

                String[] senders = getSenders(selectedDateDeliver, deliverStartCity, deliverDestinationCity);
                if (senders == null) {
                    senders = new String[1];
                    senders[0] = "No senders found";
                }
                ArrayAdapterDeliver = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, senders);
            } else {
                //insert sender into DB
                Sender sender = new Sender();
                sender.setPhoneNumber(phoneNumber);
                sender.setUsername(username);
                sender.setDate(selectedDateSender);
                sender.setDestination(senderDestinationCity);
                sender.setSentFrom(senderStartCity);
                new EndpointsAsyncInsertSender(this, sender).execute();
                //

                String[] delivers = getDelivers(selectedDateSender, senderStartCity, senderDestinationCity);
                if (delivers == null) {
                    delivers = new String[1];
                    delivers[0] = "No delivers found";
                }
                ArrayAdapterSender = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, delivers);
            }

            final ListView listview = (ListView) findViewById(R.id.listView);
            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    final Object o = listview.getItemAtPosition(position);
                    if (!o.toString().equals("No senders found") && !o.toString().equals("No delivers found")) {
                        switcher.setDisplayedChild(2);
                        String phNumber = o.toString().split(" ")[1];
                        destionationPhoneNumber = phNumber.equals("null") ? null : phNumber;
                        TextView textView = (TextView) findViewById(R.id.t3);
                        textView.setText("Send a message to: \n" + o.toString());
                        final EditText editText = (EditText) findViewById(R.id.text);
                        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                            @Override
                            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                                boolean handled = false;
                                if (actionId == EditorInfo.IME_ACTION_SEND) {
                                    sendMessage(editText.getText().toString(), username);
                                    handled = true;
                                }
                                return handled;
                            }
                        });
                    }
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error connectiong to the database", Toast.LENGTH_LONG);
        }
    }

    public String[] getSenders(String selectedDate, String senderStartCity, String senderDestinationCity) throws ExecutionException, InterruptedException {
        List<Sender> senders = new EndpointsAsyncTaskSender(this).execute().get();
        LinkedList<String> filteredSenders = new LinkedList<>();
        for (int i = 0; i < senders.size(); i++) {
            Sender sender = senders.get(i);
            if (sender.getDate().equals(selectedDate) && sender.getDestination().equals(senderDestinationCity) && sender.getSentFrom().equals(senderStartCity))
                filteredSenders.add(sender.getUsername() + ": " + sender.getPhoneNumber() + " - " + sender.getDate());
        }
        if (filteredSenders.size() == 0)
            return null;
        String[] sendersArray = new String[filteredSenders.size()];
        for (int i = 0; i < filteredSenders.size(); i++)
            sendersArray[i] = filteredSenders.get(i);
        return sendersArray;
    }

    public String[] getDelivers(String selectedDate, String deliverStartCity, String deliverDestinationCity) throws ExecutionException, InterruptedException {
        List<Receiver> receivers = new EndpointsAsyncTaskReceiver(this).execute().get();
        LinkedList<String> filteredReceivers = new LinkedList<>();
        for (int i = 0; i < receivers.size(); i++) {
            Receiver receiver = receivers.get(i);
            if (receiver.getDate().equals(selectedDate) && receiver.getStartCity().equals(deliverDestinationCity) && receiver.getDestination().equals(deliverStartCity))
                filteredReceivers.add(receiver.getUsername() + ": " + receiver.getPhoneNumber() + " - " + receiver.getDate());
        }
        if (filteredReceivers.size() == 0)
            return null;
        String[] receiversArray = new String[filteredReceivers.size()];
        for (int i = 0; i < filteredReceivers.size(); i++)
            receiversArray[i] = filteredReceivers.get(i);
        return receiversArray;
    }

    public void sendMessage(String message, String user) {
        EditText notificationText = (EditText) findViewById(R.id.text);
        notificationMessage = notificationText.getText().toString();
        final String json = "{\"data\":{\"message\":\"" + notificationText.getText().toString() + "\"}}";
        new Thread() {
            public void run() {
                try {
                    HttpClient client = new DefaultHttpClient();

                    // Based on reference documentation...
                    // http://msdn.microsoft.com/library/azure/dn223273.aspx
                    ParseConnectionString(HubFullAccess);
                    String url = HubEndpoint + HubName + "/messages/?api-version=2015-01";
                    HttpPost post = new HttpPost(url);

                    // Authenticate the POST request with the SaS token.
                    post.setHeader("Authorization", generateSasToken(url));

                    // JSON content for GCM
                    post.setHeader("Content-Type", "application/json;charset=utf-8");

                    // Notification format should be GCM
                    post.setHeader("ServiceBusNotification-Format", "gcm");
                    post.setEntity(new StringEntity(json));

                    HttpResponse response = client.execute(post);
                } catch (Exception e) {
                    DialogNotify("Exception", e.getMessage().toString());
                }
            }
        }.start();
        if (phoneNumber.length() > 0 && message.length() > 0)
            sendSMS(message);
        else
            Toast.makeText(getBaseContext(),
                    "Please enter both phone number and message.",
                    Toast.LENGTH_SHORT).show();
    }

    private void sendSMS(String message) {
        if (destionationPhoneNumber != null) {
            try {
                PendingIntent pi = PendingIntent.getActivity(this, 0,
                        new Intent(this, TabView.class), 0);
                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(destionationPhoneNumber, null, message, pi, null);
            } catch (Exception e) {
                Toast.makeText(this, "Unable to send SMS", Toast.LENGTH_LONG);
            }
        } else Toast.makeText(this, "Destionation has no phone number", Toast.LENGTH_LONG);
    }

    private void ParseConnectionString(String connectionString) {
        String[] parts = connectionString.split(";");
        if (parts.length != 3)
            throw new RuntimeException("Error parsing connection string: "
                    + connectionString);

        for (int i = 0; i < parts.length; i++) {
            if (parts[i].startsWith("Endpoint")) {
                this.HubEndpoint = "https" + parts[i].substring(11);
            } else if (parts[i].startsWith("SharedAccessKeyName")) {
                this.HubSasKeyName = parts[i].substring(20);
            } else if (parts[i].startsWith("SharedAccessKey")) {
                this.HubSasKeyValue = parts[i].substring(16);
            }
        }
    }

    public void DialogNotify(final String title, final String message) {
        final AlertDialog.Builder dlg;
        dlg = new AlertDialog.Builder(this);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                AlertDialog dlgAlert = dlg.create();
                dlgAlert.setTitle(title);
                dlgAlert.setButton(DialogInterface.BUTTON_POSITIVE,
                        (CharSequence) "OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                dlgAlert.setMessage(message);
                dlgAlert.setCancelable(false);
                dlgAlert.show();
            }
        });
    }

    private String generateSasToken(String uri) {
        String targetUri;
        try {
            targetUri = URLEncoder
                    .encode(uri.toString().toLowerCase(), "UTF-8")
                    .toLowerCase();

            long expiresOnDate = System.currentTimeMillis();
            int expiresInMins = 60; // 1 hour
            expiresOnDate += expiresInMins * 60 * 1000;
            long expires = expiresOnDate / 1000;
            String toSign = targetUri + "\n" + expires;

            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = HubSasKeyValue.getBytes("UTF-8");
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA256");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(toSign.getBytes("UTF-8"));

            // Using android.util.Base64 for Android Studio instead of
            // Apache commons codec.
            String signature = URLEncoder.encode(
                    Base64.encodeToString(rawHmac, Base64.NO_WRAP).toString(), "UTF-8");

            // construct authorization string
            String token = "SharedAccessSignature sr=" + targetUri + "&sig="
                    + signature + "&se=" + expires + "&skn=" + HubSasKeyName;
            return token;
        } catch (Exception e) {
            DialogNotify("Exception Generating SaS", e.getMessage().toString());
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private void registerWithNotificationHubs() {
        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... params) {
                try {
                    String regid = gcm.register(SENDER_ID);
                } catch (Exception e) {
                    DialogNotify("Notification Hub Error: ", e.getMessage());
                    return e;
                }
                return null;
            }
        }.execute(null, null, null);
    }

    public String getNotificationMessage() {
        return notificationMessage;
    }
}
