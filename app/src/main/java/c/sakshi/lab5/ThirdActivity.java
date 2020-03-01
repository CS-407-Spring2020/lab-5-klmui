package c.sakshi.lab5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ThirdActivity extends AppCompatActivity {

    int noteid = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // 1. Get EditText view.
        EditText editText = findViewById(R.id.editText);
        // 2. Get Intent.
        Intent intent = getIntent();
        // 3. Get the value of integer "noteid" from intent.
        noteid = intent.getIntExtra("noteid", -1);

        // 4. Initialize class variable "noteid" with the value from intent.
        if (noteid != -1) {
            // Display the content of note by retrieving "notes" ArrayList in SecondActivity
            Note note = Main2Activity.notes.get(noteid);
            String noteContent = note.getContent();
            // Use editText.setText() to display the contents of this note on screen.
            editText.setText(noteContent);
        }
    }

    public void saveMethod(View view) {
        // 1. Get editText view and the content that the user entered.
        EditText editText = findViewById(R.id.editText);
        String noteContent = editText.getText().toString();

        // 2. Initialize database instance.
        Context context = getApplicationContext();
        SQLiteDatabase sqLiteDatabase = context.openOrCreateDatabase("notes", Context.MODE_PRIVATE, null);

        // 3. Initialize DBHelper class.
        DBHelper dbHelper = new DBHelper(sqLiteDatabase);

        // 4. Set username in the following variable by fetching it from SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.lab5ms1", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");

        // 5. Save information to database.
        String title;
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String date = dateFormat.format(new Date());

        if (noteid == -1) {
            title = "NOTE_" + (Main2Activity.notes.size() + 1);
            dbHelper.saveNotes(username, title, noteContent, date);
        } else {
            title = "NOTE_" + (noteid + 1);
            dbHelper.updateNote(title, date, noteContent);
        }

        // 6. Go to second activity.
        Intent intent = new Intent(this, Main2Activity.class);
        startActivity(intent);
    }
}
