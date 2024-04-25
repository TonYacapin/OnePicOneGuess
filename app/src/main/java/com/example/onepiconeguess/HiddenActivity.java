package com.example.onepiconeguess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onepiconeguess.MainActivity;
import com.example.onepiconeguess.R;

public class HiddenActivity extends AppCompatActivity {

    private String category;
    private ImageView imgcategory;
    private TextView tvPoints, tvHint;
    private EditText tbGuess;
    private int currentImageIndex = 0;
    private int score = 0;

    private int[] animalImages = {R.drawable.antelope, R.drawable.bear, R.drawable.dog, R.drawable.elephant,
            R.drawable.flamingo, R.drawable.giraffe, R.drawable.lion, R.drawable.monkey,
            R.drawable.owl, R.drawable.zebra};
    private String[] animalAnswers = {"antelope", "bear", "dog", "elephant", "flamingo", "giraffe",
            "lion", "monkey", "owl", "zebra"};
    private String[] animalHints = {"Has long horns", "Large mammal with fur", "Known as man's best friend",
            "Has a trunk", "Tall, pink bird", "Tallest land animal", "King of the jungle",
            "Swings from trees", "Nocturnal bird", "Has black and white stripes"};

    private int[] sportsImages = {R.drawable.badminton, R.drawable.baseball, R.drawable.basketball, R.drawable.billiard,
            R.drawable.bowling, R.drawable.chess, R.drawable.golf, R.drawable.lawntennis,
            R.drawable.skateboarding, R.drawable.soccer};
    private String[] sportsAnswers = {"badminton", "baseball", "basketball", "billiard", "bowling", "chess",
            "golf", "lawn tennis", "skateboarding", "soccer"};
    private String[] sportsHints = {"Played with racket and shuttlecock", "Bat-and-ball game", "Played with a round ball",
            "Tabletop cue sport", "Rolling a ball towards pins", "Strategy board game",
            "Club-and-ball sport", "Played on grass or synthetic surface",
            "Riding on a skateboard", "Played with a spherical ball"};

    private int[] fruitImages = {R.drawable.apple, R.drawable.banana, R.drawable.coconut, R.drawable.grapes,
            R.drawable.guava, R.drawable.mango, R.drawable.orange, R.drawable.pineapple,
            R.drawable.strawberry, R.drawable.watermelon};
    private String[] fruitAnswers = {"apple", "banana", "coconut", "grapes", "guava", "mango",
            "orange", "pineapple", "strawberry", "watermelon"};
    private String[] fruitHints = {"Red or green fruit", "Yellow fruit with peel", "Hard-shelled fruit with water inside",
            "Small, juicy fruit in bunches", "Tropical fruit with green skin", "Sweet tropical fruit",
            "Citrus fruit", "Tropical fruit with spiky skin", "Small, red fruit with seeds on surface",
            "Large, green fruit with red interior"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hidden);

        // Retrieve the category from the intent extras
        category = getIntent().getStringExtra("category");

        // Initialize views
        imgcategory = findViewById(R.id.img_category);
        tvPoints = findViewById(R.id.tv_points);
        tvHint = findViewById(R.id.tv_hint);
        tbGuess = findViewById(R.id.tb_Guess);

        // Set initial points and hint
        updatePoints();
        updateHint();

        // Initialize buttons for next and previous images
        Button btnNextImage = findViewById(R.id.button);
        Button btnPreviousImage = findViewById(R.id.btn_previousimage);
        Button btnSubmit = findViewById(R.id.btn_submit);
        Button btnBackToMenu = findViewById(R.id.btn_mainmenu);

        // Set onClickListener for next image button
        btnNextImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex++;
                updateImage();
                updateHint();
            }
        });

        // Set onClickListener for previous image button
        btnPreviousImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentImageIndex--;
                updateImage();
                updateHint();
            }
        });

        // Set onClickListener for submit button
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkGuess();
            }
        });

        // Set onClickListener for back to menu button
        btnBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to MainActivity
                Intent intent = new Intent(HiddenActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish the current activity to prevent going back to it when pressing back button
            }
        });

        // Generate the initial image
        updateImage();
    }

    private void updateImage() {
        // Ensure the currentImageIndex stays within bounds
        currentImageIndex = Math.max(0, Math.min(9, currentImageIndex));

        // Set the image based on the current category and image index
        switch (category) {
            case "animals":
                imgcategory.setImageResource(animalImages[currentImageIndex]);
                break;
            case "sports":
                imgcategory.setImageResource(sportsImages[currentImageIndex]);
                break;
            case "fruits":
                imgcategory.setImageResource(fruitImages[currentImageIndex]);
                break;
            default:
                break;
        }
    }

    private void updateHint() {
        // Update the hint text based on the current category and image index
        String hint = "Hint: ";
        switch (category) {
            case "animals":
                hint += animalHints[currentImageIndex];
                break;
            case "sports":
                hint += sportsHints[currentImageIndex];
                break;
            case "fruits":
                hint += fruitHints[currentImageIndex];
                break;
            default:
                hint = "Hint: ";
                break;
        }
        tvHint.setText(hint);
    }
    private boolean[] itemsGuessedCorrectly = new boolean[10]; // Assuming there are 10 items in each category

    private void checkGuess() {
        String guess = tbGuess.getText().toString().trim().toLowerCase();
        String answer = getAnswer();

        if (itemsGuessedCorrectly[currentImageIndex]) {
            // Item has already been guessed correctly
            Toast.makeText(this, "You've already guessed this item correctly.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (guess.equals(answer)) {
            // Correct guess
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
            updatePoints();
            itemsGuessedCorrectly[currentImageIndex] = true; // Mark the item as guessed correctly
            // Optionally, you can load the next image here
        } else {
            // Incorrect guess
            Toast.makeText(this, "Incorrect. Try again!", Toast.LENGTH_SHORT).show();
        }

        // Clear the guess input field
        tbGuess.getText().clear();
    }

    private String getAnswer() {
        switch (category) {
            case "animals":
                return animalAnswers[currentImageIndex];
            case "sports":
                return sportsAnswers[currentImageIndex];
            case "fruits":
                return fruitAnswers[currentImageIndex];
            default:
                return "";
        }
    }

    private void updatePoints() {
        tvPoints.setText("Points: " + score);
    }
}
