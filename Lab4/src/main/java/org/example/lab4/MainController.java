package org.example.lab4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 10; i++){ // инициализация данных в гриде памяти (RAM)
            for (int j = 0; j < 5; j++){
                Label t = new Label(((j+1)+5*i) +  " : 0");
                RAM.add(t, j, i);
            }
        }

        registr.add(new Label("A"), 1, 0);
        registr.add(new Label("B"), 2, 0);
        registr.add(new Label("C"), 3, 0);
        registr.add(new Label("D"), 4, 0);

        registr.add(new Label("0"), 1, 1);
        registr.add(new Label("0"), 2, 1);
        registr.add(new Label("0"), 3, 1);
        registr.add(new Label("0"), 4, 1);

    }

    @FXML
    void AddInstr(){ //кнопка "Добавить инструкцию"
        AddInstructionController p = new AddInstructionController();
        FXMLLoader fxmlLoader = new FXMLLoader(appMain.class.getResource("addInstruction.fxml"));
        fxmlLoader.setController(p);
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Add Instruction");
        dialogStage.setScene(scene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        AddInstructionController controller =  fxmlLoader.getController();
        controller.setParentController(this);
        dialogStage.show();

    }

    @FXML
    GridPane instructions;

    public void addgrid(String a, int b){
        frequency.addColumn(0, new Label(a + ": " + Integer.toString(b)));
    }

    public void addDataToGrid(Node age) {
        instructions.addColumn(0, age);
    }

    @FXML
    void RemoveProg(){ // кнопка "Сбросить выполнение программы"
        instructions.getChildren().clear();
        frequency.getChildren().clear();
        //Program.commands.clear();
        //Program.counter.clear();
    }

    @FXML
    void Next(){ // кнопка "Выполнить очередную инструкцию"

    }

    @FXML
    ScrollPane scrollPane;

    @FXML
    ScrollPane scrollPane1;

    @FXML
    GridPane registr;

    @FXML
    GridPane RAM;

    @FXML
    GridPane frequency;

    public GridPane getGridregistr() {
        return registr;
    }

    public GridPane getGridRAM() {
        return RAM;
    }

    public void modifyGrid(GridPane gridPane, int row, int col, String newText, Paint color) {
        if (gridPane.getChildren().size() > (row * 5 + col)) {
            Label cell = (Label) gridPane.getChildren().get(row * 5 + col);
            cell.setText(newText);
            cell.setTextFill(color);
        }
    }
}
