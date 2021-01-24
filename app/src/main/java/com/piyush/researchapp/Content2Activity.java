package com.piyush.researchapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Content2Activity extends AppCompatActivity {
    Button content2_Btn;
    String email;
    String mAccountUserId;
    Intent intent;
    TextView textViewContent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content2);
        intent = getIntent();
        mAccountUserId = intent.getStringExtra("mAccountUserId");
        email = intent.getStringExtra("email");

        content2_Btn = findViewById(R.id.content2_Btn);
        textViewContent2 = findViewById(R.id.textViewContent2);
        textViewContent2.setMovementMethod(new ScrollingMovementMethod());

        content2_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoContent3Activity();
            }
        });
    }

    private void gotoContent3Activity() {
        Intent intent=new Intent(this,Content3Activity.class);
        intent.putExtra("email", email);
        intent.putExtra("mAccountUserId", mAccountUserId);
        startActivity(intent);
    }


}