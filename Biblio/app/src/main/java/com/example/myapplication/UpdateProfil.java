package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class UpdateProfil extends AppCompatActivity {
    Button mUpdateBtn, mUpdateBtnEmail, mUpdateBtnPassword;
    EditText mUserName, mEmail, mEmailAct, mPassword, mEmailCurrent, mPasswordCurrent, mPasswordNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profil);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // The user object has basic properties such as display name, email, etc.
            String displayName = user.getDisplayName();
            getSupportActionBar().setTitle(displayName);

        }
        mUserName = findViewById(R.id.userNameUpdate);
        mUpdateBtn = findViewById(R.id.buttonUpdate);
        mUpdateBtnEmail = findViewById(R.id.buttonChangerEmail);
        mEmail = findViewById(R.id.modifierEmail);
        mEmailAct = findViewById(R.id.mailCurrent);
        mPassword = findViewById(R.id.actuelMdp);
        mPasswordCurrent = findViewById(R.id.ancienMdp);
        mPasswordNew = findViewById(R.id.nouveauMdp);
        mEmailCurrent = findViewById(R.id.mailActuel);
        mUpdateBtnPassword = findViewById(R.id.buttonChangerMdp);

        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = mUserName.getText().toString().trim();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                        .setDisplayName(userName)
                        .build();
                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(UpdateProfil.this, "UserNameRegistered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), UpdateProfil.class));
                                }
                            }
                        });
            }
        });
        mUpdateBtnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmail.getText().toString().trim();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String emaila = mEmailAct.getText().toString().trim();
                String passworda = mPassword.getText().toString().trim();
                if (!(emaila.contentEquals(user.getEmail()))) {
                    mEmailAct.setError("L'utiisateur n'existe pas");
                    return;
                }
                AuthCredential credential = EmailAuthProvider
                        .getCredential(emaila, passworda); // Current Login Credentials \\
                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Now change your email address \\
                                    //----------------Code for Changing Email Address----------\\
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    user.updateEmail(email)
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(UpdateProfil.this, "Email Changé, nouvel email : " + email, Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(getApplicationContext(), UpdateProfil.class));
                                                    } else {
                                                        Toast.makeText(UpdateProfil.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                                else {
                                    Toast.makeText(UpdateProfil.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
        mUpdateBtnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = mPasswordNew.getText().toString().trim();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String emaila = mEmailCurrent.getText().toString().trim();
                String passworda = mPasswordCurrent.getText().toString().trim();
                if (password.length() < 6) {
                    mPassword.setError("Le mot de passe doit avoir au moins 6 charactères");
                    return;
                }
                if (!(emaila.contentEquals(user.getEmail()))) {
                    mEmailCurrent.setError("L'utiisateur n'existe pas " );
                    return;
                }

                AuthCredential credential = EmailAuthProvider.getCredential(emaila, passworda); // Current Login Credentials \\
                // Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            //Now change your email address \\
                            //----------------Code for Changing Email Address----------\\
                            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                            user.updatePassword(password)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                Toast.makeText(UpdateProfil.this, "Mot de passe changé, nouveeau mot de passe : " + password, Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(getApplicationContext(), UpdateProfil.class));
                                            } else {
                                                Toast.makeText(UpdateProfil.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });
                        }
                        else {
                            Toast.makeText(UpdateProfil.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.update_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        return super.onOptionsItemSelected(item);
    }

}