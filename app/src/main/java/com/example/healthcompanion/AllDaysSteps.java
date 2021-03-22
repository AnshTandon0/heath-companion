package com.example.healthcompanion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AllDaysSteps extends AppCompatActivity implements StepsAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private StepsAdapter stepsAdapter;
    private List<Steps> steps;
    private StepsRepository stepsRepository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_days_steps);
        stepsRepository = new StepsRepository(this);
        try {
            steps = stepsRepository.getAllSteps();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recyclerView = findViewById(R.id.recyclerView);
        stepsAdapter = new StepsAdapter(this, steps,this);
        recyclerView.setAdapter(stepsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void OnItemClick(int position) {
        Intent intent = new Intent(this,IndividualDayDisplay.class);
        try {
            intent.putExtra("date", stepsRepository.getAllSteps().get(position).getDate());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}