package ttt.game;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Random;

public class HelloController {

    @FXML
    private Label turn;

    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8, b9;

    Random random = new Random();
    boolean isPlayerTurn = random.nextInt(2) == 1;
    Button[] buttons;
    boolean isOver = false;

    @FXML
    public void initialize() {
        buttons = new Button[]{b1, b2, b3, b4, b5, b6, b7, b8, b9};
        turn.setText(isPlayerTurn ? "X Turn" : "O Turn");

        for (Button b : buttons) {
            b.setOnAction(_ -> {
                if (isPlayerTurn) {
                    playerMark(b);
                } else {
                    cpuMark(b);
                }
                checkWin();
                whoseTurn();
            });
        }
    }

    public void playerMark(Button button) {

        button.setText("X");
        button.setDisable(true);
        isPlayerTurn = !isPlayerTurn;
    }

    public void cpuMark(Button button) {
        button.setText("O");
        button.setStyle("-fx-text-fill: green; -fx-background-color: gray");
        button.setDisable(true);
        isPlayerTurn = !isPlayerTurn;
    }

    public void checkWin() {

        if (!isOver) {
            for (int i = 0; i < 3; i++) {

                if (!buttons[i].getText().isBlank()) {
                    if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText())) {
                        declareWinner(buttons[i].getText());

                        String color = buttons[i].getText().equals("X") ? "-fx-text-fill: red" : "-fx-text-fill: green";

                        buttons[i].setStyle("-fx-background-color: whitesmoke;" + color);
                        buttons[i+3].setStyle("-fx-background-color: whitesmoke;" + color);
                        buttons[i+6].setStyle("-fx-background-color: whitesmoke;" + color);

                        isOver = true;
                        return;
                    }
                }
            }

            for (int i = 0; i < 7; i += 3) {
                if (!buttons[i].getText().isBlank()) {
                    if (buttons[i].getText().equals(buttons[i + 1].getText()) && buttons[i].getText().equals(buttons[i + 2].getText())) {
                        declareWinner(buttons[i].getText());

                        String color = buttons[i].getText().equals("X") ? "-fx-text-fill: red;" : "-fx-text-fill: green;";

                        buttons[i].setStyle("-fx-background-color: whitesmoke;" + color);
                        buttons[i+1].setStyle("-fx-background-color: whitesmoke;" + color);
                        buttons[i+2].setStyle("-fx-background-color: whitesmoke;" + color);

                        isOver = true;
                        return;
                    }
                }
            }

            if (!buttons[0].getText().isBlank() && !buttons[4].getText().isBlank() && !buttons[8].getText().isBlank()) {
                if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText())) {
                    declareWinner(buttons[0].getText());

                    String color = buttons[0].getText().equals("X") ? "-fx-text-fill: red;" : "-fx-text-fill: green;";

                    buttons[0].setStyle("-fx-background-color: whitesmoke;" + color);
                    buttons[4].setStyle("-fx-background-color: whitesmoke;" + color);
                    buttons[8].setStyle("-fx-background-color: whitesmoke;" + color);

                    isOver = true;
                    return;
                }
            }

            if (!buttons[2].getText().isBlank() && !buttons[4].getText().isBlank() && !buttons[6].getText().isBlank()) {
                if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText())) {
                    declareWinner(buttons[2].getText());

                    String color = buttons[2].getText().equals("X") ? "-fx-text-fill: red;" : "-fx-text-fill: green;";

                    buttons[2].setStyle("-fx-background-color: whitesmoke;" + color);
                    buttons[4].setStyle("-fx-background-color: whitesmoke;" + color);
                    buttons[6].setStyle("-fx-background-color: whitesmoke;" + color);

                    isOver = true;
                    return;
                }
            }
        }

        boolean isAllMarked = true;

        for (Button b : buttons) {
            if (b.getText().isBlank()) {
                isAllMarked = false;
            }
        }

        if (isAllMarked && !isOver) {
            turn.setText("Draw!");

            for (Button b : buttons) {
                b.setDisable(true);
            }
        }

    }

    public void declareWinner(String X_or_O) {
        String text = X_or_O + " Wins!";
        Platform.runLater(() -> turn.setText(text));

        for (Button b : buttons) {
            b.setDisable(true);
        }
    }

    public void whoseTurn() {
        if (isPlayerTurn) {
            turn.setText("X Turn");
        } else {
            turn.setText("O Turn");
        }
    }

    public void resetGame(){

        for (Button b : buttons){
            b.setDisable(false);
            b.setText("");
            b.setStyle("-fx-background-color: gray");
        }

        whoseTurn();
        isOver = false;
    }
}