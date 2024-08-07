package com.example.javafxtest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.util.Stack;

public class InfoCenter {
    private StackPane pane;
    private Label message;
    private Button startGameButton;

    public InfoCenter(){
        pane = new StackPane();
        pane.setMinSize(VariableControlls.APP_WIDTH,VariableControlls.INFO_CENTER_HEIGHT);
        pane.setTranslateX(VariableControlls.APP_WIDTH/2);
        pane.setTranslateY(VariableControlls.INFO_CENTER_HEIGHT/2);

        //tictactoe label
        message = new Label("Tic Tac Toe");
        message.setMinSize(VariableControlls.APP_WIDTH,VariableControlls.INFO_CENTER_HEIGHT);
        message.setFont(Font.font(24));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20);
        pane.getChildren().add(message);

        //start button
        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(135,30);
        startGameButton.setTranslateY(20);
        pane.getChildren().add(startGameButton);
    }

    public StackPane getStackPane(){
        return pane;
    }

    public void updateMessage(String message){
        this.message.setText(message);
    }

    public void showStartButton(){
        startGameButton.setVisible(true);
    }

    public void hideStartButton(){
        startGameButton.setVisible(false);
    }

    public void setStartButtononAction(EventHandler<ActionEvent> onAction){
        startGameButton.setOnAction(onAction);
    }
}
