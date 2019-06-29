package View;

import Controller.CsvLoaderController;
import Model.EnadeRow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUIView extends Application implements View {


    CsvLoaderController csvLoaderController;

    @Override
    public void start(Stage primaryStage) throws Exception {

        BorderPane root = new BorderPane();
        initMenu(primaryStage, root);
        initTableView(primaryStage, root);
        primaryStage.setTitle("Enade Ufsm Explorer");
        primaryStage.setScene(new Scene(root, 1280, 700));
        primaryStage.show();
    }

    private void initMenu(Stage primaryStage, BorderPane borderPane) {
        final Menu menu1 = new Menu("File");
        MenuItem reload = new MenuItem("Reload");
        reload.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String error = csvLoaderController.loadCsv(true);
                if (error != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erro");
                    alert.setHeaderText("An error has occurred");
                    alert.setContentText(error);
                    alert.showAndWait();
                }

            }
        });
        MenuItem source = new MenuItem("Source");
        source.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog(CsvLoaderController.DEFAULT_URL);
                dialog.setTitle("Selecione a URL");
                dialog.setHeaderText("Source for data table");
                dialog.setContentText("Please enter with a new source:");

                Optional<String> result = dialog.showAndWait();
                result.ifPresent(name -> csvLoaderController.setUrl(name));
            }
        });

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        menu1.getItems().add(reload);
        menu1.getItems().add(source);
        menu1.getItems().add(exit);

        final Menu menu2 = new Menu("Help");
        MenuItem about = new MenuItem("About");
        about.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText("Enade Ufsm Explorer");
                alert.setContentText("Autor: Roberto Nisxota Menegais");
                alert.showAndWait();
            }
        });

        menu2.getItems().add(about);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu1, menu2);
        borderPane.setTop(menuBar);
    }

    private void initTableView(Stage primaryStage, BorderPane borderPane) {

        TableView<EnadeRow> table = new TableView<>();
        csvLoaderController = new CsvLoaderController();
        String error = csvLoaderController.loadCsv(false);

        if (error != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("An error has occurred");
            alert.setContentText(error);
            alert.showAndWait();
        }

        List<TableColumn<EnadeRow, String>> tableColumns = new ArrayList<>();
        List<String> prettyNames = csvLoaderController.getFieldListPrettyNames();
        List<String> fieldNames = csvLoaderController.getFieldListNames();
        for (int i = 0; i < fieldNames.size(); i++) {
            TableColumn<EnadeRow, String> tableColumn = new TableColumn(prettyNames.get(i));
            tableColumn.setCellValueFactory(new PropertyValueFactory<EnadeRow, String>(fieldNames.get(i)));
            tableColumns.add(tableColumn);
        }

        table.getColumns().addAll(tableColumns);
        table.setItems(csvLoaderController.getRows());
        table.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;

            initModalWindow(primaryStage, newValue);
        });
        borderPane.setCenter(table);
    }

    private void initModalWindow(Stage primaryStage, EnadeRow row) {
        Stage modal = new Stage();
        ScrollPane scrollPane = new ScrollPane();
        VBox vBox = new VBox();

        List<String> prettyNames = csvLoaderController.getFieldListPrettyNames();

        for (String prettyName : prettyNames) {
            Label name = new Label(prettyName + ":");
            name.setPadding(new Insets(10, 10, 10, 10));
            name.setFont(new Font(20));
            Label value = new Label(csvLoaderController.getFieldValueByName(row, prettyName));
            value.setPadding(new Insets(0, 0, 0, 20));
            value.setFont(new Font(16));
            vBox.getChildren().addAll(name, value);
        }

        Label name = new Label("Imagem:");
        name.setPadding(new Insets(10, 10, 10, 10));
        name.setFont(new Font(20));
        vBox.getChildren().add(name);
        if (row.getUrlCrop() != null) {
            try {
                Image image = new Image(new URL(row.getUrlCrop()).openStream());
                ImageView imageView = new ImageView(image);
                vBox.getChildren().add(imageView);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        scrollPane.setContent(vBox);
        modal.setTitle("Informações adicionais");
        modal.setScene(new Scene(scrollPane, 800, 600));
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.show();

    }

    @Override
    public void init(String[] args) {
        launch(args);
    }
}
