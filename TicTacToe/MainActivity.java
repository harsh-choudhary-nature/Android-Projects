package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    char curPlayer='x';
    int dim=3;
    boolean gameIsActive=true;
    //n means not played
    char[][] gameState={{'n', 'n', 'n'},
                        {'n', 'n', 'n'},
                        {'n', 'n', 'n'}};
    public boolean checkWin(int tag){
        int rowNo=tag/dim;
        int columnNo=tag%dim;
        //check in current row
        boolean ans=true;
        for(int i=0;i<dim;i++){
            if(gameState[rowNo][i]!=curPlayer) {
                ans = false;
                break;
            }
        }
        if(ans) {
            return ans;
        }
        //check in current column
        ans=true;
        for(int i=0;i<dim;i++){
            if(gameState[i][columnNo]!=curPlayer) {
                ans = false;
                break;
            }
        }
        if(ans) {
            return ans;
        }
        //check on primary diagonal
        ans=true;
        for(int i=0;i<dim;i++){
            if(gameState[i][i]!=curPlayer) {
                ans = false;
                break;
            }
        }
        if(ans) {
            return ans;
        }
        //check on secondary diagonal
        ans=true;
        for(int i=0;i<dim;i++){
            if(gameState[i][dim-i-1]!=curPlayer) {
                ans = false;
                break;
            }
        }
        if(ans) {
            return ans;
        }

        return false;
    }
    public boolean isFullBoard(){
        //check if the board is full
        boolean boardFull=true;
        for(char[] row:gameState){
            for(char c:row){
                if(c=='n'){
                    boardFull=false;
                    break;
                }
            }
        }
        return boardFull;
    }
    public void declare(String toPrint){
        TextView message_view=(TextView)findViewById(R.id.messageId);
        message_view.setText(toPrint);
        LinearLayout linear_layout_box=(LinearLayout) findViewById(R.id.linearLayoutId);
        linear_layout_box.setVisibility(View.VISIBLE);
        ((GridLayout)findViewById(R.id.gridLayoutId)).setAlpha(0.25f);
    }
    public void playAgain(View view){
        //make the message linear layout invisible
        LinearLayout linear_layout_box=(LinearLayout) findViewById(R.id.linearLayoutId);
        linear_layout_box.setVisibility(View.INVISIBLE);
//        reset the gameState
        for(int i=0;i<dim;i++){
            for(int j=0;j<dim;j++){
                gameState[i][j]='n';
            }
        }
//        set the current player
        curPlayer='x';
//        set the game to be active
        gameIsActive=true;
//        clear all the image views
        GridLayout grid_layout_box=(GridLayout) findViewById(R.id.gridLayoutId);
        for(int i=0;i<grid_layout_box.getChildCount();i++){
            ((ImageView)grid_layout_box.getChildAt(i)).setImageResource(0);
        }
        ((GridLayout)findViewById(R.id.gridLayoutId)).setAlpha(1f);
    }
    public void fadeIn(View view){
        ImageView img_view=(ImageView) view;
        int tag=Integer.parseInt(img_view.getTag().toString());
        if(gameState[tag/dim][tag%3]=='n' && gameIsActive){
            gameState[tag/dim][tag%dim]=curPlayer;
            if(curPlayer=='x'){
                img_view.setImageResource(R.drawable.tic);
            }else{
                img_view.setImageResource(R.drawable.tac);
            }
            img_view.setAlpha(0f);
            img_view.animate().alpha(1f).setDuration(1000);
            //check if the current player move helped him win
            if(checkWin(tag)){
                gameIsActive=false;
                declare("\""+curPlayer+"\" has won!");
            }else if(isFullBoard()){
                gameIsActive=false;
                declare("The game has Drawn");
            }else{
                curPlayer=(curPlayer=='x')?'o':'x';
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}