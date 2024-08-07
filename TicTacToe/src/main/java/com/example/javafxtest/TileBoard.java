package com.example.javafxtest;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

import java.util.Stack;

public class TileBoard {
    private StackPane pane;
    private InfoCenter infoCenter;
    private Tile[][] allTiles = new Tile[3][3];
    private char playerTurn = 'X';
    private boolean isEndOfgame = false;
    public TileBoard(InfoCenter infoCenter){
        this.infoCenter = infoCenter;
        pane = new StackPane();
        pane.setMinSize(VariableControlls.APP_WIDTH,VariableControlls.TILE_BOARD_HEIGHT);
        pane.setTranslateX(VariableControlls.APP_WIDTH/2);
        pane.setTranslateY((VariableControlls.TILE_BOARD_HEIGHT/2) + VariableControlls.INFO_CENTER_HEIGHT);

        addAllTiles();
    }

    private void addAllTiles() {
        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((col*100)-100);
                tile.getStackPane().setTranslateY((row*100)-100);
                pane.getChildren().add(tile.getStackPane());
                allTiles[row][col] = tile;
            }
        }
    }

    public void startNewGame(){
        isEndOfgame = false;
        playerTurn = 'X';
        for(int row = 0; row < 3; row++){
            for(int col = 0; col <3; col++){
               allTiles[row][col].setValue("");
            }
        }
    }

    public void changePlayerTurn(){
        if (playerTurn == 'X')
            playerTurn = 'O';
        else
            playerTurn = 'X';
        infoCenter.updateMessage("Player " + playerTurn + "'s Turn");
    }

    public String getPlayerTurn(){
        return String.valueOf(playerTurn);
    }

    public StackPane getStackPane() {
        return pane;
    }

    public void checkForWinner(){
        checkRowsForWinner();
        checkColsForWinner();
        checkTopLeftToBottomRight();
        checkTopRightToBottomLeft();
        checkForStalement();
    }

    private void checkForStalement() {
        if(!isEndOfgame) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (allTiles[i][j].getValue().isEmpty()) {
                        return;
                    }
                }
            }
            isEndOfgame = true;
            infoCenter.updateMessage("Stalemate...");
            infoCenter.showStartButton();
        }
    }

    private void checkTopRightToBottomLeft() {
        if (!isEndOfgame){
            if (allTiles[0][2].getValue().equals(allTiles[1][1].getValue()) && allTiles [0][2].getValue() .equals (allTiles [2][0].getValue ()) && !allTiles [0] [2].getValue().isEmpty()){
                String winner = allTiles[0][2].getValue();
                endGame(winner);
                return;
            }
        }
    }

    private void checkTopLeftToBottomRight() {
        if(!isEndOfgame){
            if(allTiles[0][0].getValue().equals(allTiles[1][1].getValue()) && allTiles[0][0].getValue().equals(allTiles[2][2].getValue()) && !allTiles[0][0].getValue().isEmpty()){
                String winner = allTiles[0][0].getValue();
                endGame(winner);
                return;
            }
        }
    }

    private void checkColsForWinner() {
        if(!isEndOfgame) {
            for (int col = 0; col < 3; col++) {
                if (allTiles[0][col].getValue().equals(allTiles[1][col].getValue()) && allTiles[0][col].getValue().equals(allTiles[2][col].getValue()) && !allTiles[0][col].getValue().isEmpty()) {
                    String winner = allTiles[0][col].getValue();
                    endGame(winner);
                    return;
                }
            }
        }
    }

    private void checkRowsForWinner() {
        if (!isEndOfgame) {
            for (int row = 0; row < 3; row++) {
                if (allTiles[row][0].getValue().equals(allTiles[row][1].getValue()) && allTiles[row][0].getValue().equals(allTiles[row][2].getValue()) && !allTiles[row][0].getValue().isEmpty()) {
                    String winner = allTiles[row][0].getValue();
                    endGame(winner);
                    return;
                }
            }
        }
    }

    private void endGame(String winner){
        isEndOfgame = true;
        infoCenter.updateMessage("Player " + winner + " Won");
        infoCenter.showStartButton();
    }

    private class Tile{
        private StackPane pane;
        private Label label;
        public Tile(){
            pane = new StackPane();
            pane.setMinSize(100,100);

            Rectangle border = new Rectangle(100,100);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label("");
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event -> {
                if (label.getText().isEmpty() && !isEndOfgame){
                    label.setText(getPlayerTurn());
                    changePlayerTurn();
                    checkForWinner();
                }
            });
        }

        public StackPane getStackPane(){
            return pane;
        }
        public String getValue(){
            return label.getText();
        }
        public void setValue(String value){
            label.setText(value);
        }
    }
}
