package com.example.onepiconeguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static String category = "animals";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button animals = findViewById(R.id.btn_Animal);
        Button sports = findViewById(R.id.btn_Sports);
        Button fruits = findViewById(R.id.btn_Fruits);
        TextView selectedCategory =findViewById(R.id.tv_category);
        Button play = findViewById(R.id.btn_play);


        selectedCategory.setText("Select category:" + category);


        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
category="animals";
selectedCategory.setText("Select category:" + category);
            }
        });

        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category="sports";
                selectedCategory.setText("Select category:" + category);
            }
        });

        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = "fruits";
                selectedCategory.setText("Select category:" + category);
            }
        });


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, HiddenActivity.class);
                intent.putExtra("category", category);
                startActivity(intent);

            }
        });



    }




}