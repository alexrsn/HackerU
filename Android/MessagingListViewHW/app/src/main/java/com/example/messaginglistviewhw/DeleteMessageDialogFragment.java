package com.example.messaginglistviewhw;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DeleteMessageDialogFragment extends DialogFragment {

    public static final String MESSAGE = "message";
    public static final String POSITION = "position";
    private Button btnYes;
    private Button btnNo;
    private TextView textView;
    private String message;
    private DeleteDialogListener deleteMessageDialogListener;


    public interface DeleteDialogListener {
        void onFinishDeleteMessageDialog(Boolean isYesSelected,int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_delete_message_dialog, container);
        btnYes = (Button) view.findViewById(R.id.btnYes);
        btnNo = (Button) view.findViewById(R.id.btnNo);
        textView = (TextView) view.findViewById(R.id.txtMessage);
        btnYes.setOnClickListener(myOnClickListener);
        btnNo.setOnClickListener(myOnClickListener);
        message = getArguments().getString(MESSAGE);
        textView.setText(message);
        getDialog().setTitle(message);
        return view;
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (deleteMessageDialogListener != null)
                deleteMessageDialogListener.onFinishDeleteMessageDialog(v.getTag().equals("1")
                        ,getArguments().getInt(POSITION));
            dismiss();
        }
    };

    public void setDeleteDialogListener(DeleteDialogListener deleteDialogListener) {
        this.deleteMessageDialogListener = deleteDialogListener;
    }
}
