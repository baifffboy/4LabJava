package org.example.lab4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class appMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        MainController controller = new MainController();
        FXMLLoader fxmlLoader = new FXMLLoader(appMain.class.getResource("main.fxml"));
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Lab4");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

// init 8 7 - память 8, значение 7
// ld a 8 - в регистр a записываем значение memory[8]
// st a 9 - из регистра а загружаем значения в memory[9]
// mv b a - из регистра a записываем значение в регистр b
// print - выводит на консоль 7 7 0 0
// add - сложение регистра а(7) и регистра b(7) -> регистр d = 14
// print - выводит на консоль 7 7 0 14
// sub - вычитание из регистра а(7) регистра b(7) -> регистр d = 0
// print - выводит на консоль 7 7 0 0
// mult - сложение регистра а(7) и регистра b(7) -> регистр d = 49
// print - выводит на консоль 7 7 0 49

