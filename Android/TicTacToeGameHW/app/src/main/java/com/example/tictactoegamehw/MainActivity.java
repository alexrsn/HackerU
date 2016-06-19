package com.example.tictactoegamehw;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    LinearLayout mainLinearLayout;
    TicTakToe myGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGame = new TicTakToe();

        mainLinearLayout = (LinearLayout) findViewById(R.id.main_activity_linear_layout);
        mainLinearLayout.setBackgroundResource(R.drawable.background);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;

        for (int i = 0; i < 3; i++) {
            LinearLayout rowLayout = new LinearLayout(this);
            ImageView imageView;
            for (int j = 0; j < 3; j++) {
                imageView = new ImageView(this);
                imageView.setTag(i * 3 + j + 1);
                imageView.setOnClickListener(myOnClickListener);
                imageView.setPadding(15, 15, 15, 15);
                rowLayout.addView(imageView, params);
            }
            mainLinearLayout.addView(rowLayout, params);

        }
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int cell = (int) v.getTag();
            TicTakToe.MoveResult moveResult = myGame.makeMove(cell);
            if (moveResult == TicTakToe.MoveResult.INVALID_MOVE)
                Toast.makeText(MainActivity.this, "Please choose free place!!!", Toast.LENGTH_SHORT).show();
            else {
                ((ImageView) v).setImageResource(!myGame.isXTurn() ? R.drawable.pic_x : R.drawable.pix_o);
                if(moveResult != TicTakToe.MoveResult.VALID_MOVE) {
                    if (moveResult == TicTakToe.MoveResult.VICTORY)
                        showEndGameDialog((!myGame.isXTurn() ? "X" : "O") + " is a WINNER!!!");
                    if (moveResult == TicTakToe.MoveResult.DRAW)
                        showEndGameDialog("Draw!!!");
                }
            }
        }
    };

    public void showEndGameDialog(String textToShow) {
        FragmentManager fragmentManager = getFragmentManager();
        EndGameDialogFragment dialogFragment = new EndGameDialogFragment();
        dialogFragment.setCancelable(false);
        Bundle args = new Bundle();
        args.putString(EndGameDialogFragment.TITLE, textToShow);
        dialogFragment.setArguments(args);
        dialogFragment.setEndGameDialogListener(new EndGameDialogFragment.EndGameDialogListener() {
            @Override
            public void onFinishEndGameDialog(Boolean isYesSelected) {
                if (isYesSelected) {
                    myGame.resetGame();
                    for (int i = 1; i <= 9; i++) {
                        ImageView imageView = (ImageView) mainLinearLayout.findViewWithTag(i);
                        imageView.setImageResource(android.R.color.transparent);
                    }
                } else
                    finish();
            }
        });
        dialogFragment.show(fragmentManager, "end game dialog");
    }
}
