package com.example.messaginglistviewhw;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MessageArrayAdapter extends ArrayAdapter<Message> {
    public Activity activity;
    private List<Message> messages;

    public MessageArrayAdapter(Activity activity, List<Message> messages) {
        super(activity, R.layout.message_item, messages);
        this.activity = activity;
        this.messages = messages;
    }

    static class ViewContainer {
        TextView lblSenderName;
        TextView lblMessage;
        TextView lblTimeReceived;
        Button btnEditMessage;
        Button btnDeleteMessage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewContainer viewContainer = null;
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = activity.getLayoutInflater();
            rowView = inflater.inflate(R.layout.message_item, parent, false);
            viewContainer = new ViewContainer();
            viewContainer.lblSenderName = (TextView) rowView.findViewById(R.id.lblSenderName);
            viewContainer.lblMessage = (TextView) rowView.findViewById(R.id.lblMessage);
            viewContainer.lblTimeReceived = (TextView) rowView.findViewById(R.id.lblTimeReceived);
            viewContainer.btnEditMessage = (Button) rowView.findViewById(R.id.btnEditMessage);
            viewContainer.btnDeleteMessage = (Button) rowView.findViewById(R.id.btnDeleteMessage);
            rowView.setTag(viewContainer);
        } else {
            viewContainer = (ViewContainer) rowView.getTag();
        }
        viewContainer.lblSenderName.setText(messages.get(position).getSender());
        viewContainer.lblMessage.setText(messages.get(position).getMessage());
        viewContainer.lblTimeReceived.setText(messages.get(position).getTime() + "");
        viewContainer.btnEditMessage.setTag(position);
        viewContainer.btnEditMessage.setOnClickListener(editMessageOnClickListener);
        viewContainer.btnDeleteMessage.setTag(position);
        viewContainer.btnDeleteMessage.setOnClickListener(deleteMessageOnClickListener);
        if (viewContainer.lblSenderName.getText().equals("me") ||
                viewContainer.lblSenderName.getText().equals("Me") ||
                viewContainer.lblSenderName.getText().equals("ME")) {
            rowView.setBackgroundColor(rowView.getResources().getColor(R.color.colorMyMessage));
        } else {
            rowView.setBackgroundColor(rowView.getResources().getColor(R.color.colorMessage));        }
        return rowView;
    }

    private View.OnClickListener editMessageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            EditMessageDialogFragment dialogFragment = new EditMessageDialogFragment();
            dialogFragment.setCancelable(false);
            Bundle args = new Bundle();
            args.putString(EditMessageDialogFragment.MESSAGE, messages.get((Integer) v.getTag()).getMessage());
            args.putInt(DeleteMessageDialogFragment.POSITION, (Integer) v.getTag());
            dialogFragment.setArguments(args);
            dialogFragment.setEditMessageDialogListener(new EditMessageDialogFragment.EditMessageDialogListener() {
                @Override
                public void onFinishEditMessageDialog(Boolean isSaveSelected, int position, String message) {
                    if (isSaveSelected) {
                        messages.get(position).setMessage(message);
                        notifyDataSetChanged();
                    }
                }
            });
            dialogFragment.show(fragmentManager, "edit message dialog");
        }
    };

    private View.OnClickListener deleteMessageOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FragmentManager fragmentManager = activity.getFragmentManager();
            DeleteMessageDialogFragment dialogFragment = new DeleteMessageDialogFragment();
            dialogFragment.setCancelable(false);
            Bundle args = new Bundle();
            args.putString(DeleteMessageDialogFragment.MESSAGE, "Delete message:"
                    + messages.get((Integer) v.getTag()).getMessage()
                    + "\nAre yo sure?");
            args.putInt(DeleteMessageDialogFragment.POSITION, (Integer) v.getTag());
            dialogFragment.setArguments(args);
            dialogFragment.setDeleteDialogListener(new DeleteMessageDialogFragment.DeleteDialogListener() {
                @Override
                public void onFinishDeleteMessageDialog(Boolean isYesSelected, int position) {
                    if (isYesSelected) {
                        messages.remove(position);
                        notifyDataSetChanged();
                    }
                }
            });
            dialogFragment.show(fragmentManager, "delete message dialog");
        }
    };
}
