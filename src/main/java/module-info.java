module ttt.game {
    requires javafx.controls;
    requires javafx.fxml;


    opens ttt.game to javafx.fxml;
    exports ttt.game;
}