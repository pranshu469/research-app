package com.piyush.researchapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    Button logoutBtn, start_pre_test_Btn,go_to_pre_test_Btn;
    TextView userName, userEmail, userId;
    ImageView profileImage;
    String email;
    String mAccountUserId;
    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;
    DatabaseReference rootRef, emailRef;
    Map<String, Object> updates = new HashMap<String, Object>();
    Boolean pretest2;
    //Boolean loggedIn;
    Intent intent;
    Boolean isEmptyData = false;
    private ResearchData userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        intent = getIntent();
        logoutBtn = findViewById(R.id.logoutBtn);
        start_pre_test_Btn = findViewById(R.id.start_pre_test_Btn);
        userName = findViewById(R.id.name);
        userEmail = findViewById(R.id.email);
        userId = findViewById(R.id.userId);
        profileImage = findViewById(R.id.profileImage);
        rootRef = FirebaseDatabase.getInstance().getReference();
        pretest2 = intent.getBooleanExtra("pretest2", false);
        //loggedIn = intent.getBooleanExtra("loggedIn", false);

        // Database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        // Database reference pointing to demo node
        emailRef = rootRef.child("data");

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                if (status.isSuccess()) {
                                    //loggedIn = true;
                                    gotoMainActivity();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Session not close", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });

        start_pre_test_Btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //gotoPreTestActivity();
                /*ResearchData data = new ResearchData(mAccountUserId, email,false);
                rootRef.child(mAccountUserId).setValue(data);*/
                //if (userData!=null) {
                    if (isEmptyData) {
                        start_pre_test_Btn.setText("Fill Info");
                        ResearchData data = new ResearchData(mAccountUserId, email, true);
                        rootRef.child(mAccountUserId).setValue(data);
                        rootRef.child(mAccountUserId).child("email").setValue(email);
                        //Toast.makeText(getApplicationContext(), "one", Toast.LENGTH_SHORT).show();
                        //rootRef.child(mAccountUserId).child("loggedIn").setValue(true);
                        gotoFillInfo();
                    } else {

                        //rootRef.child(mAccountUserId).child("loggedIn").setValue(true);
                        if (userData==null){
                            Toast.makeText(getApplicationContext(), "null user data. logging out. please login again", Toast.LENGTH_SHORT).show();
                            FirebaseAuth.getInstance().signOut();
                            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                                    new ResultCallback<Status>() {
                                        @Override
                                        public void onResult(Status status) {
                                            if (status.isSuccess()) {
                                                //loggedIn = true;
                                                gotoMainActivity();
                                            } else {
                                                Toast.makeText(getApplicationContext(), "Session not close", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            //gotoMainActivity();
                        }
                        else if (userData!=null) {

                            if (userData.getPretest2()) {
                                rootRef.child(mAccountUserId).child("pretest2").setValue(true);
                                rootRef.child(mAccountUserId).child("email").setValue(email);
                                if (userData.getPretest()) {
                                    rootRef.child(mAccountUserId).child("pretest").setValue(true);
                                    start_pre_test_Btn.setText("Go To Content 1");
                                    gotoContent1Activity();
                                } else {
                                    start_pre_test_Btn.setText("Go to Pre Test");
                                    gotoPreTestActivity();
                                }
                            }
                            else if (!userData.getPretest2()) {
                                start_pre_test_Btn.setText("Fill Info");
                                gotoFillInfo();
                            }
                            /*else if (userData.getPretest()) {
                                //Toast.makeText(getApplicationContext(), "two" + userData.getPretest(), Toast.LENGTH_SHORT).show();
                                //if (userData.getLoggedIn() == true) {
                                gotoContent1Activity();
                                //}
                            } else if (!userData.getPretest()){
                                //Toast.makeText(getApplicationContext(), "three" + userData.getPretest(), Toast.LENGTH_SHORT).show();
                                gotoFillInfo();
                            }*/
                        }
                    }
                //}
            }
        }));



        rootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ResearchData data = snapshot.child(mAccountUserId).getValue(ResearchData.class);
                userData = snapshot.child(mAccountUserId).getValue(ResearchData.class);
                if (data != null) {
                    isEmptyData = false;
                    /*if (data.getPretest()!=null && data.getPretest() ) {
                        gotoContent1Activity();
                    } else {
                        rootRef.child(mAccountUserId).child("pretest").setValue(true);
                        gotoPreTestActivity();
                    }*/
                } else {
//                                data = new ResearchData( true);
                    /*rootRef.child(mAccountUserId).setValue(data);
                    gotoPreTestActivity();*/
                    isEmptyData = true;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "Failed to read value.", error.toException());
            }

        });
    }

    private void goToSpecificActivity() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount account = result.getSignInAccount();
            mAccountUserId = account.getId();
            userName.setText(account.getDisplayName());
            userEmail.setText(account.getEmail());
            userId.setText(account.getId());
            email = account.getEmail();
            try {
                Glide.with(this).load(account.getPhotoUrl()).into(profileImage);
            } catch (NullPointerException e) {
                Toast.makeText(getApplicationContext(), "image not found", Toast.LENGTH_LONG).show();
            }

        } else {
            gotoMainActivity();
        }
    }

    private void gotoMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void gotoPreTestActivity() {
        Intent intent = new Intent(this, PreTestActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("mAccountUserId", mAccountUserId);
        intent.putExtra("pretest2", pretest2);
        startActivity(intent);
    }

    private void gotoFillInfo() {
        Intent intent = new Intent(this, FillInfo.class);
        intent.putExtra("email", email);
        intent.putExtra("mAccountUserId", mAccountUserId);
        intent.putExtra("pretest2", pretest2);
        startActivity(intent);
    }

    private void gotoContent1Activity() {
        Intent intent = new Intent(this, Content1Activity.class);
        intent.putExtra("email", email);
        intent.putExtra("mAccountUserId", mAccountUserId);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
    }
}