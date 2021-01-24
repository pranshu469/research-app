package com.piyush.researchapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class FillInfo extends AppCompatActivity {
    Button submit_fill_info_Btn, fbutton1, fbutton2, fbutton3, fbutton4;
    TextView questionsFillInfo;
    DatabaseReference rootRef, emailRef, reference;
    Map<String, Object> updates = new HashMap<String,Object>();
    String mAccountUserId;
    Boolean pretest;
    String email;
    Intent intent;
    int total=1;
    String a1,a2,a3,a4,a5,a6,a7,a8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_info);

        intent = getIntent();
        mAccountUserId = intent.getStringExtra("mAccountUserId");
        email = intent.getStringExtra("email");
        //submit_fill_info_Btn = findViewById(R.id.submit_fill_info_Btn);
        fbutton1 = findViewById(R.id.fbutton1);
        fbutton2 = findViewById(R.id.fbutton2);
        fbutton3 = findViewById(R.id.fbutton3);
        fbutton4 = findViewById(R.id.fbutton4);
        questionsFillInfo = (TextView)findViewById(R.id.questionsFillInfo);

        // Database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();
        // Database reference pointing to demo node
        emailRef = rootRef.child("data");

        updateQuestion();

        /*submit_fill_info_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootRef.child(mAccountUserId).child("a1").setValue(a1);
                rootRef.child(mAccountUserId).child("a2").setValue(a2);
                rootRef.child(mAccountUserId).child("a3").setValue(a3);
                rootRef.child(mAccountUserId).child("a4").setValue(a4);
                rootRef.child(mAccountUserId).child("a5").setValue(a5);
                rootRef.child(mAccountUserId).child("a6").setValue(a6);
                rootRef.child(mAccountUserId).child("a7").setValue(a7);
                rootRef.child(mAccountUserId).child("a8").setValue(a8);
                gotoPreTestActivity();
            }
        });
        */

    }

    private void updateQuestion() {
        if (total > 8) {
            Toast.makeText(getApplicationContext(),"Test Over! Going To Pre Test Activity",Toast.LENGTH_LONG).show();
            rootRef.child(mAccountUserId).child("pretest").setValue(true);
            rootRef.child(mAccountUserId).child("a1").setValue(a1);
            rootRef.child(mAccountUserId).child("a2").setValue(a2);
            rootRef.child(mAccountUserId).child("a3").setValue(a3);
            rootRef.child(mAccountUserId).child("a4").setValue(a4);
            rootRef.child(mAccountUserId).child("a5").setValue(a5);
            rootRef.child(mAccountUserId).child("a6").setValue(a6);
            rootRef.child(mAccountUserId).child("a7").setValue(a7);
            rootRef.child(mAccountUserId).child("a8").setValue(a8);
            /*rootRef.child(mAccountUserId).child("answers").setValue(answers);*/
            gotoPreTestActivity();
        } else {
            if (total==2 || total==7) {
                fbutton3.setVisibility(View.GONE);
                fbutton4.setVisibility(View.GONE);
            } else {
                fbutton3.setVisibility(View.VISIBLE);
                fbutton4.setVisibility(View.VISIBLE);
            }
            reference = rootRef.child("FillInfo").child(String.valueOf(total));
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    final PretestQuestion fillinfoquestion = snapshot.getValue(PretestQuestion.class);

                    questionsFillInfo.setText(fillinfoquestion.getQuestion());
                    fbutton1.setText(fillinfoquestion.getOption1());
                    fbutton2.setText(fillinfoquestion.getOption2());
                    fbutton3.setText(fillinfoquestion.getOption3());
                    fbutton4.setText(fillinfoquestion.getOption4());

                    fbutton1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*answers[total-1] = button1.getText().toString();*/
                            if (total==1) {
                                a1 = fbutton1.getText().toString();
                            } else if (total==2) {
                                a2 = fbutton1.getText().toString();
                            } else if (total==3) {
                                a3 = fbutton1.getText().toString();
                            }else if (total==4) {
                                a4 = fbutton1.getText().toString();
                            }else if (total==5) {
                                a5 = fbutton1.getText().toString();
                            }else if (total==6) {
                                a6 = fbutton1.getText().toString();
                            }else if (total==7) {
                                a7 = fbutton1.getText().toString();
                            }else if (total==8) {
                                a8 = fbutton1.getText().toString();
                            }

                                total++;
                                updateQuestion();
                        }
                    });

                    fbutton2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            /*answers[total-1] = button2.getText().toString();*/
                            if (total==1) {
                                a1 = fbutton2.getText().toString();
                            } else if (total==2) {
                                a2 = fbutton2.getText().toString();
                            } else if (total==3) {
                                a3 = fbutton2.getText().toString();
                            }else if (total==4) {
                                a4 = fbutton2.getText().toString();
                            }else if (total==5) {
                                a5 = fbutton2.getText().toString();
                            }else if (total==6) {
                                a6 = fbutton2.getText().toString();
                            }else if (total==7) {
                                a7 = fbutton2.getText().toString();
                            }else if (total==8) {
                                a8 = fbutton2.getText().toString();
                            }


                                total++;
                                updateQuestion();

                        }
                    });

                    fbutton3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (total==1) {
                                a1 = fbutton3.getText().toString();
                            } else if (total==2) {
                                a2 = fbutton3.getText().toString();
                            } else if (total==3) {
                                a3 = fbutton3.getText().toString();
                            }else if (total==4) {
                                a4 = fbutton3.getText().toString();
                            }else if (total==5) {
                                a5 = fbutton3.getText().toString();
                            }else if (total==6) {
                                a6 = fbutton3.getText().toString();
                            }else if (total==7) {
                                a7 = fbutton3.getText().toString();
                            }else if (total==8) {
                                a8 = fbutton3.getText().toString();
                            }
                            /*answers[total-1] = button3.getText().toString();*/

                                total++;
                                updateQuestion();

                        }
                    });

                    fbutton4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (total==1) {
                                a1 = fbutton4.getText().toString();
                            } else if (total==2) {
                                a2 = fbutton4.getText().toString();
                            } else if (total==3) {
                                a3 = fbutton4.getText().toString();
                            }else if (total==4) {
                                a4 = fbutton4.getText().toString();
                            }else if (total==5) {
                                a5 = fbutton4.getText().toString();
                            }else if (total==6) {
                                a6 = fbutton4.getText().toString();
                            }else if (total==7) {
                                a7 = fbutton4.getText().toString();
                            }else if (total==8) {
                                a8 = fbutton4.getText().toString();
                            }

                                total++;
                                updateQuestion();

                        }
                    });
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void gotoPreTestActivity(){
        Intent intent=new Intent(this,PreTestActivity.class);
        intent.putExtra("email", email);
        intent.putExtra("mAccountUserId",mAccountUserId);
        intent.putExtra("pretest",pretest);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
    }
}