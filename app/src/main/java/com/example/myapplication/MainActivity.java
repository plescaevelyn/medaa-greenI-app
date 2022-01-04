package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView register, forgotPassword;
    private EditText editTextEmail, editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        register = (TextView) findViewById(R.id.registeruserid);
        register.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.loginid);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.emailid);
        editTextPassword = (EditText) findViewById(R.id.passwordid);

        forgotPassword = (TextView) findViewById(R.id.forgotpasswordid);
        forgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registeruserid:
                startActivity(new Intent(MainActivity.this,RegisterUser.class));
                break;
            case R.id.loginid:
                userLogin();
                break ;
            case R.id.forgotpasswordid:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()){
            editTextEmail.setError("Introduceti adresa de email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextEmail.setError("Introduceti parola!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Introduceti adresa de email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 7){
            editTextEmail.setError("");
            editTextPassword.setError(" (peste 6 caractere)");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, RewardsActivity.class));
                }
            }
        });

    }
    public void openUser() {
        Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
        startActivity(intent);
    }
}
