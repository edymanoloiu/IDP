package com.idp.packpickup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.idp.api.userApi.model.User;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View v) throws ExecutionException, InterruptedException {
        EditText uname = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);
        String username = uname.getText().toString();
        String password = pass.getText().toString();
        TelephonyManager phoneManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = phoneManager.getLine1Number();
        boolean userExists = false;
        List<User> result = new EndpointsAsyncTaskUserLogin(this, username, password).execute().get();
        for (User q : result)
            if (q.getUsername().equals(username)) {
                if (q.getPassword().equals(password)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();
                    Intent tabView = new Intent(this, TabView.class);
                    tabView.putExtra("username", username);
                    tabView.putExtra("phone", phoneNumber);
                    startActivity(tabView);
                } else
                    Toast.makeText(this, "Incorect password", Toast.LENGTH_LONG).show();
                userExists = true;
            }
        if (!userExists)
            Toast.makeText(this, "The user doesn't exists", Toast.LENGTH_LONG).show();
    }

    public void register(View v) throws ExecutionException, InterruptedException {
        EditText uname = (EditText) findViewById(R.id.username);
        EditText pass = (EditText) findViewById(R.id.password);
        String username = uname.getText().toString();
        String password = pass.getText().toString();
        TelephonyManager phoneManager = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String phoneNumber = phoneManager.getLine1Number();
        if (!username.isEmpty() && !password.isEmpty()) {
            User user = new User();
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
            user.setUsername(username);
            Boolean register = new EndpointsAsyncTaskUserRegister(this, user).execute().get();
            if (register) {
                Toast.makeText(this, "registration succesufly", Toast.LENGTH_LONG);
                Intent tabView = new Intent(this, TabView.class);
                tabView.putExtra("username", username);
                tabView.putExtra("phone", phoneNumber);
                startActivity(tabView);
            }
        } else
            Toast.makeText(this, "One or both fields are empty", Toast.LENGTH_LONG);
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
}
