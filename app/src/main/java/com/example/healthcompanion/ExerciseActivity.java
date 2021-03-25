package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class ExerciseActivity extends AppCompatActivity {

    private ExerciseRepository exerciseRepository;
    private List<String> startTime = new ArrayList<>();
    private List<String> endTime = new ArrayList<>();
    private String date;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        exerciseRepository = new ExerciseRepository(this);

        date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        sharedPreferences = getSharedPreferences("exercise",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("status","start exercise");
        editor.apply();

        try {
            if(exerciseRepository.selectAll().size() > 0 )
            {
                RecyclerView recyclerView = findViewById(R.id.recyclerViews);
                ExerciseAdapter exerciseAdapter = new ExerciseAdapter(this,exerciseRepository.selectAll());
                recyclerView.setAdapter(exerciseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void startExercise(View view) throws ExecutionException, InterruptedException {
        ExerciseAdapter exerciseAdapter;
        RecyclerView recyclerView;
        if ( sharedPreferences.getString("status","").equalsIgnoreCase("start exercise"))
        {

            Button button = findViewById(R.id.buttons);
            button.setText("Stop Exercise");
            editor.putString("status","stop exercise");
            editor.commit();


            if(sharedPreferences.getString("date","").equalsIgnoreCase(date)) {

                if(exerciseRepository.selectAll().size() > 0) {
                    startTime = DataConverter.gettingListFromString(exerciseRepository.selectStartTime(date));
                    endTime = DataConverter.gettingListFromString(exerciseRepository.selectEndTime(date));
                }

                startTime.add(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
                endTime.add("On Going");
                Exercise exercise = new Exercise(date,DataConverter.writingStringFromList(startTime),DataConverter.writingStringFromList(endTime));
                exerciseRepository.update(exercise);

                recyclerView = findViewById(R.id.recyclerViews);
                exerciseAdapter = new ExerciseAdapter(this,exerciseRepository.selectAll());
                recyclerView.setAdapter(exerciseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
            else
            {


                editor.putString("date", date);
                editor.commit();
                startTime.add(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
                endTime.add("On Going");

                Exercise exercise = new Exercise(date, DataConverter.writingStringFromList(startTime),DataConverter.writingStringFromList(endTime));
                exerciseRepository.insert(exercise);

                recyclerView = findViewById(R.id.recyclerViews);
                exerciseAdapter = new ExerciseAdapter(this,exerciseRepository.selectAll());
                recyclerView.setAdapter(exerciseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

            }

        }
        else if (sharedPreferences.getString("status","").equalsIgnoreCase("stop exercise"))
        {

            Button button = findViewById(R.id.buttons);
            button.setText("Start Exercise");
            editor.putString("status","start exercise");
            editor.commit();
            if(sharedPreferences.getString("date","").equalsIgnoreCase(date)) {

                Exercise exercise = exerciseRepository.search(date);
                endTime = DataConverter.gettingListFromString(exercise.getEnd_time());

                endTime.set(endTime.size()-1,new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));

                Exercise exercise1 = new Exercise(date,exercise.getStart_time(),DataConverter.writingStringFromList(endTime));
                exerciseRepository.update(exercise1);

                recyclerView = findViewById(R.id.recyclerViews);
                exerciseAdapter = new ExerciseAdapter(this,exerciseRepository.selectAll());
                recyclerView.setAdapter(exerciseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
            /*else
            {
                editor.putString("date",date);
                startTime.add(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
                endTime.add("On Going");
                Exercise exercise = new Exercise(date, startTime,endTime);
                exerciseRepository.insert(exercise);

                recyclerView = findViewById(R.id.recyclerView2);
                exerciseAdapter = new ExerciseAdapter(this,exerciseRepository.selectAll(),exerciseRepository.selectStartTime(date),exerciseRepository.selectEndTime(date));
                recyclerView.setAdapter(exerciseAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

            }*/
        }
    }
}