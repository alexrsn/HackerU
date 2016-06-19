package com.example.displayingdialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class YesOrNoDialogFragment extends DialogFragment {

    public static final String TITLE = "title";
    private Button btnYes;
    private Button btnNo;
    private TextView textView;
    private String title;
    private YesNoDialogListener yesNoDialogListener;


    public interface YesNoDialogListener {
        void onFinishYesNoDialog(Boolean isYesSelected);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_yesorno_dialog, container);
        btnYes = (Button) view.findViewById(R.id.btnYes);
        btnNo = (Button) view.findViewById(R.id.btnNo);
        textView = (TextView) view.findViewById(R.id.txtYesOrNo);
        btnYes.setOnClickListener(myOnClickListener);
        btnNo.setOnClickListener(myOnClickListener);

        title = getArguments().getString(TITLE);
        textView.setText(title);
        getDialog().setTitle(title);
        return view;
    }

    private View.OnClickListener myOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (yesNoDialogListener != null)
                yesNoDialogListener.onFinishYesNoDialog(v.getTag().equals("1"));
            dismiss();//close fragment like finish() in activity
        }
    };

    public void setYesNoDialogListener(YesNoDialogListener yesNoDialogListener) {
        this.yesNoDialogListener = yesNoDialogListener;
    }
}
