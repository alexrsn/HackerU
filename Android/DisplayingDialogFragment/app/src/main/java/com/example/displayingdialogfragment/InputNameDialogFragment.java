package com.example.displayingdialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class InputNameDialogFragment extends DialogFragment {

    public static final String TITLE = "title";
    private EditText txtName;
    private Button btnDone;
    private String title;
    private InputNameDialogListener inputNameDialogListener;

    public interface InputNameDialogListener {
        void onFinishInputDialog(String inputText);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_inputname_dialog, container);
        txtName = (EditText) view.findViewById(R.id.txtName);
        btnDone = (Button) view.findViewById(R.id.btnDone);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputNameDialogListener != null)
                    inputNameDialogListener.onFinishInputDialog(txtName.getText().toString());
                dismiss();//close fragment like finish() in activity
            }
        });
        txtName.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);//opens keyboard
        title = getArguments().getString(TITLE);
        getDialog().setTitle(title);
        return view;
    }

    public void setInputNameDialogListener(InputNameDialogListener inputNameDialogListener) {
        this.inputNameDialogListener = inputNameDialogListener;
    }
}
