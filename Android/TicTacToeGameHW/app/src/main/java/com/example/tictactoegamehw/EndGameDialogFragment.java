package com.example.tictactoegamehw;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class EndGameDialogFragment extends DialogFragment {

    public static final String TITLE = "title";
    private TextView txtMessage;
    private Button btnNewGame;
    private Button btnExit;
    private String title;
    private EndGameDialogListener endGameDialogListener;


    public interface EndGameDialogListener {
        void onFinishEndGameDialog(Boolean isYesSelected);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_endgame_dialog, container);
        txtMessage = (TextView) view.findViewById(R.id.txtMessage);
        btnNewGame = (Button) view.findViewById(R.id.btnNewGame);
        btnExit = (Button) view.findViewById(R.id.btnExit);
        btnNewGame.setOnClickListener(myOnClickListener);
        btnExit.setOnClickListener(myOnClickListener);
        title = getArguments().getString(TITLE);
        txtMessage.setText(title);
        return view;
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (endGameDialogListener != null)
                endGameDialogListener.onFinishEndGameDialog(v.getTag().equals("1"));
            dismiss();
        }
    };

    public void setEndGameDialogListener(EndGameDialogListener endGameDialogListener) {
        this.endGameDialogListener = endGameDialogListener;
    }
}
