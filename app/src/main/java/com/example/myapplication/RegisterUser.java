package com.example.myapplication;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private TextView banner;
    private TextView registerUser;
    EditText editTextFullName, editTextEmail, editTextPassword;

    @Override
    public void onStart() {
        super.onStart();

        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.bannerid);
        banner.setOnClickListener(this);

        registerUser = (TextView) findViewById(R.id.registeruserid);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText) findViewById(R.id.fullnameid);
        editTextEmail = (EditText) findViewById(R.id.emailid);
        editTextPassword = (EditText) findViewById(R.id.passwordid);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bannerid:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.registeruserid:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullname = editTextFullName.getText().toString().trim();

        if (fullname.isEmpty()){
            editTextFullName.setError("Introduceți numele dvs!");
            editTextFullName.requestFocus();
            return;
        }

        if (email.isEmpty()){
            editTextEmail.setError("Introduceti adresa dvs. de e-mail!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()){
            editTextPassword.setError("Introduceti parola!");
            editTextPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Va rugam sa introduceti un e-mail valid!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.length() < 7){
            editTextEmail.setError("");
            editTextPassword.setError(" (peste 6 caractere)");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        int score = 0;
                        if (task.isSuccessful()) {
                            User user = new User(fullname, email, password, score);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterUser.this,"Utilizator inregistrat cu succes!", Toast.LENGTH_LONG).show();
                                        //redirect to login layout
                                    } else {
                                        Toast.makeText(RegisterUser.this,"Inregistrare esuata!", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else { Toast.makeText(RegisterUser.this,"Inregistrare esuata!", Toast.LENGTH_LONG).show(); }
                    }
                });
    }
}