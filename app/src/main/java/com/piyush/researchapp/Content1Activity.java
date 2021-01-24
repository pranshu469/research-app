package com.piyush.researchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Content1Activity extends AppCompatActivity {
    Button content1_Btn;
    String email;
    String mAccountUserId;
    Intent intent;
    TextView textViewContent1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content1);
        intent = getIntent();

        content1_Btn = findViewById(R.id.content1_Btn);
        textViewContent1 = findViewById(R.id.textViewContent1);
        textViewContent1.setMovementMethod(new ScrollingMovementMethod());



        mAccountUserId = intent.getStringExtra("mAccountUserId");
        email = intent.getStringExtra("email");

        content1_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoContent2Activity();
            }
        });
    }

    private void gotoContent2Activity() {
        Intent intent=new Intent(this,Content2Activity.class);
        intent.putExtra("email", email);
        intent.putExtra("mAccountUserId", mAccountUserId);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Disabled Back Press", Toast.LENGTH_SHORT).show();
    }
}