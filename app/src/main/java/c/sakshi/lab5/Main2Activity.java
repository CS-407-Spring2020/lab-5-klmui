package c.sakshi.lab5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Get Intent that started this activity and get string
//        Intent intent = getIntent();
//        String username = intent.getStringExtra("username");
        // Get username using sharedPreferences
        String usernameKey = "";
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5ms1", Context.MODE_PRIVATE);
        usernameKey = sharedPreferences.getString("username", "");

        // Change textview
        textView = findViewById(R.id.welcomeMessage);
        textView.setText("Welcome " + usernameKey + "!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            // Erase username from sharedPreferences
            Intent intent = new Intent(this, MainActivity.class);
            SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5ms1", Context.MODE_PRIVATE);
            sharedPreferences.edit().remove("username").apply();
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
