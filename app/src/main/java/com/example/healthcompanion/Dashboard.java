package com.example.healthcompanion;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Dashboard extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        TextView textView = findViewById(R.id.name);
        sharedPreferences = getSharedPreferences("steps",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if (sharedPreferences.getString("First Time Second Time","").equals("First Time"))
        {
            EditText editText = new EditText(this);
            editText.setHint("Name");

            AlertDialog.Builder alert = new AlertDialog.Builder(this)
                    .setMessage("Enter your Name ")
                    .setView(editText)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editor.putString("name",editText.getText().toString());
                            editor.apply();
                            textView.setText("Welcome " + sharedPreferences.getString("name",""));

                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            recreate();
                        }
                    });
            alert.show();
            editor.putString("First Time Second Time","Second Time");
            editor.commit();
        }
        textView.setText("Welcome " + sharedPreferences.getString("name",""));
    }

    public void logout(MenuItem item)
    {
        editor.putString("First Time Second Time","First Time");
        editor.commit();
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(Dashboard.this, "Logout Successfull", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Dashboard.this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    public void pedometer(View view)
    {
        Intent intent = new Intent(this,Pedometer.class);
        startActivity(intent);
    }

    public void exercise(View view)
    {
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
    }

    public void water(View view)
    {
        Intent intent = new Intent(this,WaterActivity.class);
        startActivity(intent);
    }
    public void sleep(View view)
    {
        Intent intent = new Intent(this,SleepActivity.class);
        startActivity(intent);
    }
}