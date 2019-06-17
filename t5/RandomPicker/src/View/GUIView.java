package View;

import Controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class GUIView extends Application implements View {

    private NameListController controller;
    private TextArea textArea;
    private Button shuffle;
    private Button next;
    private Label label;

    @Override
    public void start(Stage primaryStage) throws Exception {

        controller = new NameListController(new OfflinePickController());

        BorderPane root = new BorderPane();
        initMenu(primaryStage, root);
        initTextArea(primaryStage, root);

        shuffle = new Button("Shuffle");
        shuffle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                controller.updateNames(textArea.getText());
                controller.shuffle();
                next.setDisable(false);
            }
        });

        next = new Button("Next");
        next.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String str = controller.pick();
                if (str == null) {
                    next.setDisable(true);
                    label.setText("Item: Lista Vazia");
                } else label.setText("Item: " + str);
            }
        });
        next.setDisable(true);
        root.setLeft(shuffle);
        root.setRight(next);
        primaryStage.setTitle("Random Picker");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }

    private void initMenu(Stage primaryStage, BorderPane borderPane) {
        final Menu menu1 = new Menu("File");
        MenuItem menuItem1 = new MenuItem("Open");
        menuItem1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Escolha o arquivo .txt");
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    String path = file.getPath();
                    if (controller.initNameList(path))
                        textArea.setText(controller.getNames());
                    else
                        System.err.println("Não foi possível carregar o arquivo");
                }
            }
        });
        MenuItem menuItem2 = new MenuItem("Exit");
        menuItem2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        menu1.getItems().add(menuItem1);
        menu1.getItems().add(menuItem2);

        final Menu menu2 = new Menu("Help");
        MenuItem menuItem3 = new MenuItem("About");
        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText("RandomPicker");
                alert.setContentText("Autor: Roberto Nisxota Menegais");
                alert.showAndWait();
            }
        });

        menu2.getItems().add(menuItem3);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2);
        borderPane.setTop(menuBar);
    }

    private void initTextArea(Stage primaryStage, BorderPane borderPane) {
        VBox vBox = new VBox();
        this.textArea = new TextArea();
        textArea.setPromptText("Dados do arquivo ou usuário");
        textArea.setMaxWidth(200);
        textArea.setMaxHeight(200);
        label = new Label("Item: Lista não embaralhada");
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label);
        vBox.getChildren().add(textArea);
        borderPane.setCenter(vBox);
    }

    @Override
    public void init(String[] args) {
        launch(args);
    }
}
