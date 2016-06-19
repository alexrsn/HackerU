package com.company;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }
}

class MainFrame extends Frame{
    private Label label;
    private int counter = 0;
    private TextField textField;

    public MainFrame(){
        label = new Label();
        label.setBounds(10,120,100,30);
        add(label);

        textField = new TextField();
        textField.setBounds(10,40,100,30);
        add(textField);

        Button button = new Button("Click here");
        button.setBounds(10,80,100,30);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button Clicked");
                counter++;
                label.setText("counter: " + counter + " " + textField.getText() );
            }
        });
        add(button);

        setSize(400,350);
        setLayout(null);
        setVisible(true);
    }
}