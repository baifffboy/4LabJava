package org.example.lab4;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class AddInstructionController implements Initializable  {

    public MainController parentController;
    public void setParentController(MainController parentController) {
        this.parentController = parentController;
    }
/*
    String mostPopularInstruction(Map<String, Integer> counterRef){
        Map<String, Integer> da = new HashMap<>();
        for (Command command : Program.commands) {
            String cmdName = command.getcommands(0);
            da.put(cmdName, da.getOrDefault(cmdName, 0) + 1);
        }
        return Collections.max(counterRef.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    Map<String, Integer> Vporyadkeubivaniya(Map<String, Integer> counterRef){
        String g = mostPopularInstruction(counterRef);
        String key = g;
        Integer value = counterRef.get(g);
        counterRef.remove(g);
        if (parentController != null) {
            parentController.addgrid(key, value);
        }
        return counterRef;
    }
*/
    @FXML
    TextField instruction1;

    @FXML
    TextField instruction2;

    @FXML
    ChoiceBox<String> choiceBox;

    @FXML
    void ok(){

        InstructionController p = new InstructionController();
        Command command = new Command(choiceBox.getValue(), instruction1.getText(), instruction2.getText());
        Program.addCommand(command);

        List<Map.Entry<String, Integer>> sortedList = Program.counter.entrySet() //Получаем Set<Map.Entry<String,Integer>>
                .stream() //Преобразуем Set в Stream
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Сортируем Stream, используя компаратор по значениям
                .collect(Collectors.toList()); //Собираем отсортированный Stream в List

        parentController.frequency.getChildren().clear();

        sortedList.stream().forEach(entry -> {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (parentController != null) {
                parentController.addgrid(key, value);
            }
        });
/*
        Map<String, Integer> da = Program.counter;
        int y = Program.counter.size();
        for (int i = 0; i < y; i++) {
            da = Vporyadkeubivaniya(da);
        }

*/

        iCPU CPU = BCPU.build(parentController); // создание класса CPU при помощи фабрики BCPU
        Executor executor = new Executor(CPU); // передаем в Executor наш класс CPU
        executor.run(Program.commands);

        FXMLLoader fxmlLoader = new FXMLLoader(AddInstructionController.class.getResource("Instruction.fxml"));
        fxmlLoader.setController(p);
        try {
            Pane pane = fxmlLoader.load();
            if (parentController != null) {
                parentController.addDataToGrid(pane);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add("init");
        choiceBox.getItems().add("ld");
        choiceBox.getItems().add("add");
        choiceBox.getItems().add("print");
        choiceBox.getItems().add("sub");
        choiceBox.getItems().add("mult");
        choiceBox.getItems().add("mv");
        choiceBox.getItems().add("st");
    }
}
