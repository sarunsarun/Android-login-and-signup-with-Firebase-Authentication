package com.example.date;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
{
    TextInputLayout t1,t2 ;
    ProgressBar bar ;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        t1=(TextInputLayout)findViewById(R.id.email);
        t2=(TextInputLayout)findViewById(R.id.pwd);
        bar=(ProgressBar) findViewById(R.id.progressBar);
    }
    public void gotosignin(View view)
    {
        startActivity(new Intent(MainActivity.this,login.class));
    }

    public void signuphere(View view)
    {
        bar.setVisibility(View.VISIBLE);
        String email;
        email = Objects.requireNonNull(t1.getEditText()).getText().toString();
        String password= Objects.requireNonNull(t2.getEditText()).getText().toString();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, (OnCompleteListener<AuthResult>) task -> {
                    if (task.isSuccessful())
                    {
                        bar.setVisibility(View.INVISIBLE);
                        t1.getEditText().setText("");
                        t2.getEditText().setText("");
                        Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        bar.setVisibility(View.INVISIBLE);
                        t1.getEditText().setText("");
                        t2.getEditText().setText("");
                        Toast.makeText(getApplicationContext(),"Process Error",Toast.LENGTH_LONG).show();
                    }
                });

    }
}