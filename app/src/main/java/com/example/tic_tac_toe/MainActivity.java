package com.example.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<int[]> combinationsList = new ArrayList<>();
    private int[] boxPositions = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 0;

    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);
        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        combinationsList.add(new int[]{0, 1, 2});
        combinationsList.add(new int[]{3, 4, 5});
        combinationsList.add(new int[]{6, 7, 8});
        combinationsList.add(new int[]{0, 3, 6});
        combinationsList.add(new int[]{1, 4, 7});
        combinationsList.add(new int[]{2, 5, 8});
        combinationsList.add(new int[]{2, 4, 6});
        combinationsList.add(new int[]{0, 4, 8});

        final String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        image1.setOnClickListener(v -> {
            if (isBoxSelectable(0)) performAction(image1, 0);
        });

        image2.setOnClickListener(v -> {
            if (isBoxSelectable(1)) performAction(image2, 1);
        });

        image3.setOnClickListener(v -> {
            if (isBoxSelectable(2)) performAction(image3, 2);
        });

        image4.setOnClickListener(v -> {
            if (isBoxSelectable(3)) performAction(image4, 3);
        });

        image5.setOnClickListener(v -> {
            if (isBoxSelectable(4)) performAction(image5, 4);
        });

        image6.setOnClickListener(v -> {
            if (isBoxSelectable(5)) performAction(image6, 5);
        });

        image7.setOnClickListener(v -> {
            if (isBoxSelectable(6)) performAction(image7, 6);
        });

        image8.setOnClickListener(v -> {
            if (isBoxSelectable(7)) performAction(image8, 7);
        });

        image9.setOnClickListener(v -> {
            if (isBoxSelectable(8)) performAction(image9, 8);
        });
    }

    private void performAction(ImageView imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.cross_icon);

            if (checkPlayerWin()) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        playerOneName.getText().toString() + " has won the match", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (totalSelectedBoxes == 8) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        "It is a draw!", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        } else {
            imageView.setImageResource(R.drawable.zero_icon);

            if (checkPlayerWin()) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        playerTwoName.getText().toString() + " has won the match", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else if (totalSelectedBoxes == 8) {
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        "It is a draw!", MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn;

        if (playerTurn == 1) {
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        } else {
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }

    private boolean checkPlayerWin() {
        for (int[] combination : combinationsList) {
            if (boxPositions[combination[0]] == playerTurn &&
                    boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoxSelectable(int boxPosition) {
        return boxPositions[boxPosition] == 0;
    }

    public void restartMatch() {
        boxPositions = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        playerTurn = 1;
        totalSelectedBoxes = 0;

        image1.setImageResource(R.drawable.round_back_blue_border);
        image2.setImageResource(R.drawable.round_back_blue_border);
        image3.setImageResource(R.drawable.round_back_blue_border);
        image4.setImageResource(R.drawable.round_back_blue_border);
        image5.setImageResource(R.drawable.round_back_blue_border);
        image6.setImageResource(R.drawable.round_back_blue_border);
        image7.setImageResource(R.drawable.round_back_blue_border);
        image8.setImageResource(R.drawable.round_back_blue_border);
        image9.setImageResource(R.drawable.round_back_blue_border);
    }
}
