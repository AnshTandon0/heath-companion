package com.example.healthcompanion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private EditText email;
    private EditText password1;
    private EditText password2;
    private Button signUpButton;
    private TextView signIn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password1 = findViewById(R.id.password1);
        password2 = findViewById(R.id.password2);
        signUpButton = findViewById(R.id.register);
        progressDialog = new ProgressDialog(this);
        signIn = findViewById(R.id.signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Register();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                 startActivity(intent);
                 finish();
            }
        });
    }
    private void Register()
    {
        String emailid = email.getText().toString();
        String paasword1 = password1.getText().toString();
        String paasword2 = password2.getText().toString();
        if (TextUtils.isEmpty(emailid))
        {
            email.setError("Enter your email");
            return;
        }
        else if (TextUtils.isEmpty(paasword1))
        {
            password1.setError("Enter your password");
            return;
        }
        else if (TextUtils.isEmpty(paasword2))
        {
            password2.setError("Confirm your password");
            return;
        }
        else if (!paasword1.equals(paasword2))
        {
            password2.setError("Password doesn't match");
            return;
        }
        else if (paasword2.length()<6)
        {
            password1.setError("at least 6");
            return;
        }
        else if (!isVallidEmail(emailid))
        {
            email.setError("Invalid Email");
            return;
        }
        progressDialog.setMessage("Please wait....");
        progressDialog.show();
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth.createUserWithEmailAndPassword(emailid,paasword1).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            }
        });
    }
    private Boolean isVallidEmail(CharSequence charSequence)
    {
         return (!TextUtils.isEmpty(charSequence)&& Patterns.EMAIL_ADDRESS.matcher(charSequence).matches());
    }
}
