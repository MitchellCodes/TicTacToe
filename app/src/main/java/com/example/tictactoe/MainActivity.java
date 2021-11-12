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

    /**
     * Gets all of the buttons that are used in the game
     * and clears their text as well as re-enables them.
     * Also sets the current player's turn to X's.
     */
    public void clearBoard(View view) {
        Button[] allTileButtons = {
                findViewById(R.id.topLeftButton),
                findViewById(R.id.topCenterButton),
                findViewById(R.id.topRightButton),
                findViewById(R.id.centerLeftButton),
                findViewById(R.id.centerButton),
                findViewById(R.id.centerRightButton),
                findViewById(R.id.bottomLeftButton),
                findViewById(R.id.bottomCenterButton),
                findViewById(R.id.bottomRightButton)
        };

        for (Button currentButton : allTileButtons) {
            currentButton.setText(""); // clear button out
            currentButton.setEnabled(true); // re-enable the button
        }

        TextView outputText = findViewById(R.id.outputText);
        outputText.setText(R.string.playerXTurn);
    }

    /**
     * Determines what player's turn it is by checking the
     * text in the View with the id outputText.
     * @return The symbol that is taking their turn as a string
     */
    public String getCurrentPlayerSymbol() {
        TextView outputText = findViewById(R.id.outputText);
        // looks in strings.xml for string with name of playerXTurn
        if (outputText.getText().equals("Player X's turn")) {
            outputText.setText(R.string.playerOTurn);
            return "X";
        }
        else {
            outputText.setText(R.string.playerXTurn);
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
        checkForWinner(currentPlayer);
    }

    /**
     * Checks all 8 different combinations of tiles that the player could get to
     * make three in a row.
     * @param symbol The symbol of the player to check if they won
     */
    public void checkForWinner(String symbol) {
        // buttons have tools:text set to their index in this array
        Button[] allTileButtons = {
                findViewById(R.id.topLeftButton),
                findViewById(R.id.topCenterButton),
                findViewById(R.id.topRightButton),
                findViewById(R.id.centerLeftButton),
                findViewById(R.id.centerButton),
                findViewById(R.id.centerRightButton),
                findViewById(R.id.bottomLeftButton),
                findViewById(R.id.bottomCenterButton),
                findViewById(R.id.bottomRightButton)
        };

        // check if the current player won in any row
        if (checkFor3(allTileButtons[0], allTileButtons[1], allTileButtons[2], symbol)
                || checkFor3(allTileButtons[3], allTileButtons[4], allTileButtons[5], symbol)
                || checkFor3(allTileButtons[6], allTileButtons[7], allTileButtons[8], symbol)) {
            endGame(allTileButtons, symbol);
        }

        // check if the current player won in any column
        if (checkFor3(allTileButtons[0], allTileButtons[3], allTileButtons[6], symbol)
                || checkFor3(allTileButtons[1], allTileButtons[4], allTileButtons[7], symbol)
                || checkFor3(allTileButtons[2], allTileButtons[5], allTileButtons[8], symbol)) {
            endGame(allTileButtons, symbol);
        }

        // check if the current player won in either diagonal
        if (checkFor3(allTileButtons[0], allTileButtons[4], allTileButtons[8], symbol)
                || checkFor3(allTileButtons[2], allTileButtons[4], allTileButtons[6], symbol)) {
            endGame(allTileButtons, symbol);
        }

        // check if the game was a draw (only need to check there are no tiles left enabled)
        boolean areAllTilesFilled = true;
        for (Button currentButton : allTileButtons) {
            if (currentButton.getText().equals("")) {
                areAllTilesFilled = false;
            }
        }

        // the game was a draw
        if (areAllTilesFilled) {
            endGame(allTileButtons, "C");
        }
    }

    /**
     * Helper method for checkForWinner. Used to check if the three given buttons
     * have the same symbol.
     * @param b1 The first button to check
     * @param b2 The second button to check
     * @param b3 The third button to check
     * @param symbol The symbol to check for
     * @return True if all three buttons have matching symbols
     */
    public boolean checkFor3(Button b1, Button b2, Button b3, String symbol) {
        return b1.getText().equals(symbol)
                && b2.getText().equals(symbol)
                && b3.getText().equals(symbol);
    }

    /**
     * Helper method for checkForWinner. Used to determine what to show in the TextView
     * outputText and also disable all of the buttons once the game ends.
     * @param buttons A list of all of the buttons
     * @param symbol The symbol of the player that won, anything other than "X" or "O"
     *               is considered to be a draw
     */
    public void endGame(Button[] buttons, String symbol) {
        TextView outputText = findViewById(R.id.outputText);
        if (symbol.equals("X")) {
            outputText.setText(R.string.playerXWins);
        }
        else if (symbol.equals("O")) {
            outputText.setText(R.string.playerOWins);
        }
        else {
            outputText.setText(R.string.draw);
        }

        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }
}