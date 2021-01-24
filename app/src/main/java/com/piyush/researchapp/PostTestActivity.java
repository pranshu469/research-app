package com.piyush.researchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class PostTestActivity extends AppCompatActivity {
    Button submit_post_test_Btn, button1post, button2post, button3post, button4post;
    TextView questionsPostTest;
    int total=1;
    int correct=0;
    int wrong=0;
    DatabaseReference rootRef, emailRef, reference;
    Map<String, Object> updates = new HashMap<String,Object>();
    String mAccountUserId;
    String email;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_test);

        intent = getIntent();
        mAccountUserId = intent.getStringExtra("mAccountUserId");
        email = intent.getStringExtra("email");

        submit_post_test_Btn = findViewById(R.id.submit_post_test_Btn);
        button1post = findViewById(R.id.button1post);
        button2post = findViewById(R.id.button2post);
        button3post = findViewById(R.id.button3post);
        button4post = findViewById(R.id.button4post);
        questionsPostTest = (TextView)findViewById(R.id.questionsPostTest);

        // Database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        // Database reference pointing to demo node
        emailRef = rootRef.child("data");
        updateQuestion();

        submit_post_test_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child(mAccountUserId).child("posttestmarks").setValue(correct);
                gotoContent1Activity();
            }
        });
    }

    private void updateQuestion() {
        if (total > 30) {
            Toast.makeText(getApplicationContext(),"Post Test Completed & Submitted",Toast.LENGTH_LONG).show();
            rootRef.child(mAccountUserId).child("posttestmarks").setValue(correct);
            rootRef.child(mAccountUserId).child("posttest").setValue(true);
            gotoContent1Activity();

        } else {
            reference = rootRef.child("Posttest").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final PretestQuestion posttestQuestion = snapshot.getValue(PretestQuestion.class);

                    questionsPostTest.setText(posttestQuestion.getQuestion());
                    button1post.setText(posttestQuestion.getOption1());
                    button2post.setText(posttestQuestion.getOption2());
                    button3post.setText(posttestQuestion.getOption3());
                    button4post.setText(posttestQuestion.getOption4());

                    button1post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (button1post.getText().toString().equals(posttestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                            rootRef.child(mAccountUserId).child("posttestmarks").setValue(correct);
                        }
                    });

                    button2post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (button2post.getText().toString().equals(posttestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                            rootRef.child(mAccountUserId).child("posttestmarks").setValue(correct);
                        }
                    });

                    button3post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (button3post.getText().toString().equals(posttestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                            rootRef.child(mAccountUserId).child("posttestmarks").setValue(correct);
                        }
                    });

                    button4post.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (button4post.getText().toString().equals(posttestQuestion.getAnswer())) {
                                correct++;
                                total++;
                                updateQuestion();
                            } else {
                                wrong++;
                                total++;
                                updateQuestion();
                            }
                            rootRef.child(mAccountUserId).child("posttestmarks").setValue(correct);
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
