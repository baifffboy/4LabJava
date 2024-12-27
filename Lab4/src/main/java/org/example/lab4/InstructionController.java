package org.example.lab4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class InstructionController implements Initializable {
    StackPane parent;
    Pane currentPane;
    @FXML
    private Button button;

    @FXML
    void Del(){

    }

    @FXML
    void Up(){

    }

    @FXML
    void Down(){

    }

    @FXML
    GridPane instructionGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        instructionGrid.add(new Label(Program.commands.get(Program.commands.size()-1).getcommands(0)), 0, 0);
        instructionGrid.add(new Label(Program.commands.get(Program.commands.size()-1).getcommands(1)), 1, 0);
        instructionGrid.add(new Label(Program.commands.get(Program.commands.size()-1).getcommands(2)), 2, 0);
    }
}
