package com.example.rob.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Initialize variables.
    private int buttonNum = 0;
    private int player = 1;
    private Boolean gameOver = false;
    private int[] board;
    public Button button;
    List<Button> buttonList;
    private TextView gameStateView;

    /**
     * Creates layout, view text, stores created buttons in array list for easy access and
     * starts a new game.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameStateView = findViewById(R.id.gameStateView);

        buttonList = new ArrayList<>();
        buttonList.add((Button) findViewById(R.id.button0));
        buttonList.add((Button) findViewById(R.id.button1));
        buttonList.add((Button) findViewById(R.id.button2));
        buttonList.add((Button) findViewById(R.id.button3));
        buttonList.add((Button) findViewById(R.id.button4));
        buttonList.add((Button) findViewById(R.id.button5));
        buttonList.add((Button) findViewById(R.id.button6));
        buttonList.add((Button) findViewById(R.id.button7));
        buttonList.add((Button) findViewById(R.id.button8));

        initGame();
    }

    /**
     * Creates a new game of tic tac toe by setting player back to first, changing gamestate from gameover
     * creating a new board and initializing all positions to 0 and resetting display text.
     */
    private void initGame(){
        player = 1;
        gameOver = false;
        board = new int[9];
        Arrays.fill(board, 0);
        gameStateView.setText(getResources().getString(R.string.xTurn));

        for (Button button : buttonList) {
            button.setText(getResources().getString(R.string.XO));
        }
    }

    /**
     * Main game thread. On button click checks for the button pressed
     * - If board position associated is not claimed and game is still in progress, updates the board.
     * - Checks is the last move created a winning position and declares a winner if so.
     * - Checks if the last move created a draw and declares the game a draw if so.
     * - If gameOver is not set, switches the player's turn.
     * @param v
     */
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.button0:
                button = findViewById(R.id.button0);
                buttonNum = 0;
                break;
            case R.id.button1:
                button = findViewById(R.id.button1);
                buttonNum = 1;
                break;
            case R.id.button2:
                button = findViewById(R.id.button2);
                buttonNum = 2;
                break;
            case R.id.button3:
                button = findViewById(R.id.button3);
                buttonNum = 3;
                break;
            case R.id.button4:
                button = findViewById(R.id.button4);
                buttonNum = 4;
                break;
            case R.id.button5:
                button = findViewById(R.id.button5);
                buttonNum = 5;
                break;
            case R.id.button6:
                button = findViewById(R.id.button6);
                buttonNum = 6;
                break;
            case R.id.button7:
                button = findViewById(R.id.button7);
                buttonNum = 7;
                break;
            case R.id.button8:
                button = findViewById(R.id.button8);
                buttonNum = 8;
                break;
        }

        if (board[buttonNum] == 0 && !gameOver) {
            board[buttonNum] = player;

            if (player == 1) {
                button.setText("X");
            } else {
                button.setText("O");
            }

            if (checkWin()) {
                gameOver = true;
                if (player == 1) {
                    gameStateView.setText(getResources().getString(R.string.xWin));
                } else {
                    gameStateView.setText(getResources().getString(R.string.oWin));
                }
            }
            else if (checkDraw()) {
                gameOver = true;
                gameStateView.setText(getResources().getString(R.string.draw));
            } else {
                nextTurn();
            }
        }
    }

    /**
     * Alternates the player's turns between X and O.
     */
    private void nextTurn() {
        if (player == 1) {
            player = 2;
            gameStateView.setText(getResources().getString(R.string.oTurn));
        } else {
            player = 1;
            gameStateView.setText(getResources().getString(R.string.xTurn));
        }
    }

    /**
     * Checks the board for winning positions.
     * @return
     */
    private Boolean checkWin() {
        if (board[0] != 0) {
            if (board[0] == board[1] && board[0] == board[2]){
                return true;
            }
            else if (board[0] == board[3] && board[0] == board[6]){
                return true;
            }
        }
        if (board[4] != 0) {
            if (board[4] == board[0] && board[4] == board[8]){
                return true;
            }
            else if (board[4] == board[2] && board[4] == board[6]){
                return true;
            }
            else if (board[4] == board[1] && board[4] == board[7]){
                return true;
            }
            else if (board[4] == board[3] && board[4] == board[5]){
                return true;
            }
        }
        if (board[8] != 0) {
            if (board[8] == board[2] && board[8] == board[5]){
                return true;
            }
            else if (board[8] == board[6] && board[8] == board[7]){
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the board for drawn positions.
     * @return
     */
    private Boolean checkDraw() {
        for(Integer i: board) {
            if(i.equals(0)){
                return false;
            }
        }
        return true;
    }

    /**
     * Initiates a new game on button press.
     * @param v
     */
    public void newGame(View v){
        initGame();
    }
}
