package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        String usernameKey = "username";

        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5ms1", Context.MODE_PRIVATE);

        if (!sharedPreferences.getString(usernameKey, "").equals("")) {
            // User was logged in so start second activity
            // Get name of user
            usernameKey = sharedPreferences.getString(usernameKey, "");
            goToActivity2(usernameKey);
        } else {
            // No username key set
            // Load main activity (screen 1)
            setContentView(R.layout.activity_main);
        }
    }

    public void onButtonClick(View view) {
        //1. Get username and password via EditText view.
        //2. Add username to SharedPreferenes object.
        EditText editText = (EditText) findViewById(R.id.username);
        String username = editText.getText().toString();
        //3. Start activity
        goToActivity2(username);
    }

    public void goToActivity2(String s) {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5ms1", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString("username", s).apply();
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("username", s); // putExtra helps us pass data to second activity
        startActivity(intent);
    }
}
