package com.example.healthcompanion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button signInButton;
    private TextView signIn;
    private TextView forgotPassword;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        firebaseAuth = FirebaseAuth.getInstance();

        sharedPreferences = getSharedPreferences("steps",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("First Time Second Time","").equalsIgnoreCase("Second Time"))
        {
            firebaseAuth.signInWithEmailAndPassword(sharedPreferences.getString("email",""),sharedPreferences.getString("password",""));
            Intent intent = new Intent(SignInActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        }


        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signInButton = findViewById(R.id.login);
        progressDialog = new ProgressDialog(this);
        signIn = findViewById(R.id.signIn);
        forgotPassword = findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this,ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Login()
    {
        editor.putString("First Time Second Time","First Time");
        editor.putString("email",email.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.commit();



        String emailid = email.getText().toString();
        String paasword = password.getText().toString();
        if (TextUtils.isEmpty(emailid))
        {
            email.setError("Enter your email");
            return;
        }
        else if (TextUtils.isEmpty(paasword))
        {
            password.setError("Enter your password");
            return;
        }
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.signInWithEmailAndPassword(emailid,paasword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignInActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignInActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SignInActivity.this, "Sign In Failed", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
}