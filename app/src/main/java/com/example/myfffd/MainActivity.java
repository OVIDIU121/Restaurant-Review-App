package com.example.myfffd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = findViewById(R.id.tv_main_regnow);
        String text = "Not a foodie yet? Register Now, it's free!";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick( View v) {

            }

        };

        ss.setSpan(clickableSpan, 18,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        textView = (TextView) findViewById(R.id.tv_main_regnow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(getApplicationContext(), Register.class);
                startActivity(go);
            }
        });
    }
}