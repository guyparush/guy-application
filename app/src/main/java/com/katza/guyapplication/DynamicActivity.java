package com.katza.guyapplication;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DynamicActivity extends AppCompatActivity {
    LinearLayout linearLayout;  // ← שונה לאותיות קטנות
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dynamic);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        linearLayout = findViewById(R.id.main); // ← עכשיו זה משתנה, לא מחלקה

        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams hsParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        horizontalScrollView.setLayoutParams(hsParams);

        LinearLayout llScroll = new LinearLayout(this);
        LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        llScroll.setLayoutParams(llParams);
        llScroll.setOrientation(LinearLayout.HORIZONTAL); // ← עכשיו עובד בלי אזהרה

        for (int i = 1; i <= 100; i++) {
            imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);

            int imageKey = getResources().getIdentifier("img" + i % 3, "drawable", getPackageName());
            imageView.setImageResource(imageKey);

            llScroll.addView(imageView);
        }

        horizontalScrollView.addView(llScroll);
        linearLayout.addView(horizontalScrollView);

        ScrollView scrollView = new ScrollView(this);
        LinearLayout.LayoutParams svParmas = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(svParmas);

        LinearLayout llVertical = new LinearLayout(this);
        llVertical.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams llParamsV = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        llVertical.setLayoutParams(llParamsV);

        scrollView.addView(llVertical);

        for (int i=1;i<=50; i++){
           ImageView imageView = new ImageView(this);
           LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,200);
           layoutParams.setMargins(5,5,5,5);
           imageView.setLayoutParams(layoutParams);

           int imageKey = getResources().getIdentifier("img"+(i%3),"drawable",getPackageName());
           imageView.setImageResource(imageKey);
           llVertical.addView(imageView);

        }
        linearLayout.addView(scrollView);

    }
}
