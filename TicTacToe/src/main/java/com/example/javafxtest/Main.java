package com.example.javafxtest;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private InfoCenter InfoCenter;
    private TileBoard tileBoard;
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,VariableControlls.APP_WIDTH,VariableControlls.APP_HEIGHT);
        intitLayout(root);
        stage.setTitle("Tic-Tac-Toe");
        stage.setScene(scene);
        stage.show();
    }

    private void intitLayout(BorderPane root) {
        intitInfoCenter(root);
        intitTileBoard(root);
    }

    private void intitTileBoard(BorderPane root) {
        tileBoard = new TileBoard(InfoCenter);
        root.getChildren().add(tileBoard.getStackPane());
    }

    private void intitInfoCenter(BorderPane root) {
        InfoCenter = new InfoCenter();
        InfoCenter.setStartButtononAction(startNewGame());
        root.getChildren().add(InfoCenter.getStackPane());
    }

    private EventHandler<ActionEvent> startNewGame(){
return new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent e){
        InfoCenter.hideStartButton();
        InfoCenter.updateMessage("Player X's Turn");
        tileBoard.startNewGame();
        }
    };
}

    public static void main(String[] args) {
        launch();
    }
}