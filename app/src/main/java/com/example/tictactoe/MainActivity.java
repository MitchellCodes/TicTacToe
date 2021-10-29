package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // for the future when the game needs to determine a winner.
    // idea: have startNewGame() contain a loop that
    // calls other methods so players keep taking turns
    // picking tiles until someone wins or there are
    // no tiles left to pick
//    public void startNewGame() {
//        String[][] emptyBoard = {
//                {"", "", ""},
//                {"", "", ""},
//                {"", "", ""}
//        };
//    }

    /**
     * Determines what player's turn it is by checking the
     * text in the View with the id outputText.
     * @return The symbol that is taking their turn as a string
     */
    public String getCurrentPlayerSymbol() {
        TextView outputText = findViewById(R.id.outputText);
        // looks in strings.xml for string with name of playerXTurn
        if (outputText.getText().equals("Player X's turn")) {
            return "X";
        }
        else {
            return "O";
        }
    }

    /**
     * Marks the button that was clicked with the
     * current player's symbol.
     * @param view The button that was clicked
     */
    public void markTile(View view) {
        String currentPlayer = getCurrentPlayerSymbol();
        Button button = (Button) view;
        button.setText(currentPlayer);
        view.setEnabled(false);
    }
}