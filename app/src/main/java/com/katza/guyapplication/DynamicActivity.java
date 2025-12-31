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

    LinearLayout linearLayout;
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

        linearLayout = findViewById(R.id.main);

        // גלילה אופקית
        HorizontalScrollView horizontalScrollView = new HorizontalScrollView(this);
        LinearLayout.LayoutParams hsParams =
                new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
        horizontalScrollView.setLayoutParams(hsParams);

        LinearLayout llScroll = new LinearLayout(this);
        llScroll.setOrientation(LinearLayout.HORIZONTAL);

        for (int i = 1; i <= 100; i++) {
            imageView = new ImageView(this);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(200, 200);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView.setLayoutParams(layoutParams);

            int imageKey = getResources().getIdentifier(
                    "img" + (i % 3),
                    "drawable",
                    getPackageName()
            );
            imageView.setImageResource(imageKey);

            llScroll.addView(imageView);
        }

        horizontalScrollView.addView(llScroll);
        linearLayout.addView(horizontalScrollView);

        // גלילה אנכית
        ScrollView scrollView = new ScrollView(this);

        LinearLayout llVertical = new LinearLayout(this);
        llVertical.setOrientation(LinearLayout.VERTICAL);

        for (int i = 1; i <= 50; i++) {
            ImageView imageView1 = new ImageView(this);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(200, 200);
            layoutParams.setMargins(5, 5, 5, 5);
            imageView1.setLayoutParams(layoutParams);

            int imageKey = getResources().getIdentifier(
                    "img" + (i % 3),
                    "drawable",
                    getPackageName()
            );
            imageView1.setImageResource(imageKey);

            llVertical.addView(imageView1);
        }

        scrollView.addView(llVertical);
        linearLayout.addView(scrollView);
    }
}
