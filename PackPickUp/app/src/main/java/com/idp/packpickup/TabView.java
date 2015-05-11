package com.idp.packpickup;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
import android.widget.ViewFlipper;

import com.idp.api.receiverApi.model.Receiver;
import com.idp.api.senderApi.model.Sender;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class TabView extends Activity {
    private TabHost tabHost;
    private ViewFlipper switcher;
    private Button RequestSender, RequestDeliver, Previous, Previous2;
    private boolean isDeliver;
    private ArrayAdapter<String> ArrayAdapterSender, ArrayAdapterDeliver;
    private String username;
    private String[] items = new String[]{
            "Arad",
            "Bucuresti",
            "Buftea",
            "Busteni",
            "Braila",
            "Buzau",
            "Focsani"
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_view);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }

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
                // TODO Auto-generated method stub
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
                // TODO Auto-generated method stub
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
                // TODO Auto-generated method stub
                new AnimationUtils();
                switcher.setAnimation(AnimationUtils.makeInAnimation
                        (getBaseContext(), true));
                switcher.setDisplayedChild(0);
            }
        });

        Previous2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void populateLists() throws ExecutionException, InterruptedException {
        //se vor trimite parametri de mai jos catre server si se intoarce un rapuns
        Spinner plecareSender = (Spinner) findViewById(R.id.spinner1);
        Spinner destinatieSender = (Spinner) findViewById(R.id.spinner12);
        Spinner plecareDeliver = (Spinner) findViewById(R.id.spinner2);
        Spinner destinatieDeliver = (Spinner) findViewById(R.id.spinner21);
        DatePicker data = (DatePicker) findViewById(R.id.datePicker);
        String selectedDate = data.getDayOfMonth() + "/" + (data.getMonth() < 10 ? "0" + data.getMonth() : data.getMonth()) + "/" + data.getYear();
        String senderStartCity = plecareSender.getSelectedItem().toString();
        String senderDestinationCity = destinatieSender.getSelectedItem().toString();
        String deliverStartCity = plecareDeliver.getSelectedItem().toString();
        String deliverDestinationCity = destinatieDeliver.getSelectedItem().toString();

        if (isDeliver) {
            String[] senders = getSenders(selectedDate, deliverStartCity, deliverDestinationCity);
            if (senders == null) {
                senders = new String[1];
                senders[0] = "No senders found";
            }
            ArrayAdapterDeliver = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, senders);
        } else {
            String[] delivers = getDelivers(selectedDate, senderStartCity, senderDestinationCity);
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
                switcher.setDisplayedChild(2);
                final Object o = listview.getItemAtPosition(position);
                TextView textView = (TextView) findViewById(R.id.t3);
                textView.setText("Send a message to: \n" + o.toString());

                final EditText editText = (EditText) findViewById(R.id.text);
                editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        boolean handled = false;
                        if (actionId == EditorInfo.IME_ACTION_SEND) {
                            sendMessage(editText.getText().toString(), o.toString());
                            handled = true;
                        }
                        return handled;
                    }
                });
            }
        });
    }

    public String[] getSenders(String selectedDate, String senderStartCity, String senderDestinationCity) throws ExecutionException, InterruptedException {
        List<Sender> senders = new EndpointsAsyncTaskSender(this).execute().get();
        LinkedList<String> filteredSenders = new LinkedList<>();
        for (int i = 0; i < senders.size(); i++) {
            Sender sender = senders.get(i);
            if (sender.getDate().equals(selectedDate) && sender.getDestination().equals(senderDestinationCity) && sender.getSentFrom().equals(senderStartCity))
                filteredSenders.add(sender.getDate() + " " + sender.getDestination());
        }
        if (filteredSenders.size() == 0)
            return null;
        return (String[]) filteredSenders.toArray();
    }

    public String[] getDelivers(String selectedDate, String deliverStartCity, String deliverDestinationCity) throws ExecutionException, InterruptedException {
        List<Receiver> receivers = new EndpointsAsyncTaskReceiver(this).execute().get();
        LinkedList<String> filteredReceivers = new LinkedList<>();
        for (int i = 0; i < receivers.size(); i++) {
            Receiver receiver = receivers.get(i);
            if (receiver.getDate().equals(selectedDate) && receiver.getDestination().equals(deliverDestinationCity) && receiver.getStartCity().equals(deliverStartCity))
                filteredReceivers.add(receiver.getDate() + " " + receiver.getDestination());
        }
        if (filteredReceivers.size() == 0)
            return null;
        return (String[]) filteredReceivers.toArray();
    }

    public void sendMessage(String message, String user) {  //metoda care trimite message la user

    }
}
