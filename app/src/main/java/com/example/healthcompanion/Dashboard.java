package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

        sharedPreferences = getSharedPreferences("steps",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("First Time Second Time","Second Time");
        editor.commit();
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
        finish();
    }

    public void exercise(View view)
    {
        Intent intent = new Intent(this,ExerciseActivity.class);
        startActivity(intent);
        finish();
    }

    public void water(View view)
    {
        Intent intent = new Intent(this,WaterActivity.class);
        startActivity(intent);
        finish();
    }
}