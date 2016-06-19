package com.example.messaginglistviewhw;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditMessageDialogFragment  extends DialogFragment{
    public static final String MESSAGE = "message";
    public static final String POSITION = "position";
    private EditText txtMessage;
    private Button btnSave;
    private Button btnCancel;
    private String message;
    private EditMessageDialogListener editMessageDialogListener;

    public interface EditMessageDialogListener {
        void onFinishEditMessageDialog(Boolean isSaveSelected,int position,String message);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit_message_dialog, container);
        txtMessage = (EditText) view.findViewById(R.id.txtName);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(myOnClickListener);
        btnCancel.setOnClickListener(myOnClickListener);
        message = getArguments().getString(MESSAGE);
        txtMessage.setText(message);
        return view;
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (editMessageDialogListener != null)
                editMessageDialogListener.onFinishEditMessageDialog(v.getTag().equals("1")
                        ,getArguments().getInt(POSITION),txtMessage.getText().toString());
            dismiss();
        }
    };

    public void setEditMessageDialogListener(EditMessageDialogListener editMessageDialogListener) {
        this.editMessageDialogListener = editMessageDialogListener;
    }
}
