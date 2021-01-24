package com.piyush.researchapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PreTestActivity extends AppCompatActivity {
    Button submit_pre_test_Btn, button1, button2, button3, button4;
    TextView questionsPreTest;
    int total=1;
    int correct=0;
    int wrong=0;
    DatabaseReference rootRef, emailRef, reference;
    Map<String, Object> updates = new HashMap<String,Object>();
    String mAccountUserId;
    Boolean pretest;
    String email;
    Intent intent;

    //Map<String, Object> updates2 = new HashMap<String,Object>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test);
        intent = getIntent();
        mAccountUserId = intent.getStringExtra("mAccountUserId");
        //pretest = intent.getBooleanExtra("pretest", false);
        email = intent.getStringExtra("email");
        submit_pre_test_Btn = findViewById(R.id.submit_pre_test_Btn);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        questionsPreTest = (TextView)findViewById(R.id.questionsPreTest);
        //answers[0] = "hello";
        // Database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        // Database reference pointing to demo node
        emailRef = rootRef.child("data");

        updateQuestion();

        submit_pre_test_Btn.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = intent.getStringExtra("email");
                //Toast.makeText(getApplicationContext(),email,Toast.LENGTH_LONG).show();
                // Push creates a unique id in database
                /*updates.put("email", email);
                updates.put("marks", "10");
//                emailRef.push().setValue(updates);
                rootRef.child("data").child(userId).setValue(updates);*/
//                ResearchData data = new ResearchData(correct);

                rootRef.child(mAccountUserId).child("marks").setValue(correct);

                gotoContent1Activity();
            }
        }));


    }

    private void updateQuestion() {
        if (total > 30) {

            rootRef.child(mAccountUserId).child("marks").setValue(correct);
            Toast.makeText(getApplicationContext(),"Test Over! Submitted the test!",Toast.LENGTH_LONG).show();

            /*rootRef.child(mAccountUserId).child("answers").setValue(answers);*/
            gotoContent1Activity();
        } else {

            reference = rootRef.child("Pretest").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final PretestQuestion pretestQuestion = snapshot.getValue(PretestQuestion.class);

                    questionsPreTest.setText(pretestQuestion.getQuestion());
                    button1.setText(pretestQuestion.getOption1());
                    button2.setText(pretestQuestion.getOption2());
                    button3.setText(pretestQuestion.getOption3());
                    button4.setText(pretestQuestion.getOption4());

                    button1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (button1.getText().toString().equals(pretestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                        }
                    });

                    button2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (button2.getText().toString().equals(pretestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                        }
                    });

                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (button3.getText().toString().equals(pretestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                        }
                    });

                    button4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (button4.getText().toString().equals(pretestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


    private void gotoContent1Activity(){
        Intent intent=new Intent(this,Content1Activity.class);
        intent.putExtra("email", email);
        intent.putExtra("mAccountUserId",mAccountUserId);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
    }
}