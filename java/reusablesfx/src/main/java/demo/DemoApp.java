package demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import lib.Slugifier;
import lib.ValidatedTextField;

import java.util.regex.Pattern;

public class DemoApp extends Application {

    @Override
    public void start(Stage stage) {

        Label titulo = new Label("Componentes reutilizables");

        titulo.setStyle(
                "-fx-font-size: 20px;" +
                "-fx-font-weight: bold;"
        );

        // COMPONENTE VISUAL
        Label lblEmail =
                new Label("Componente visual - Validación de correo:");

        ValidatedTextField email =
                new ValidatedTextField();

        email.setPromptText("ejemplo@correo.com");

        Pattern emailRegex = Pattern.compile(
                "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"
        );

        email.setValidator(
                texto -> emailRegex.matcher(texto).matches()
        );

        Label lblEstado =
                new Label("Estado: INVÁLIDO");

        lblEstado.setStyle(
                "-fx-text-fill: #b91c1c;"
        );

        email.validProperty().addListener(
                (observable, anterior, valido) -> {

                    if (valido) {
                        lblEstado.setText("Estado: VÁLIDO");
                        lblEstado.setStyle(
                                "-fx-text-fill: #15803d;"
                        );
                    } else {
                        lblEstado.setText("Estado: INVÁLIDO");
                        lblEstado.setStyle(
                                "-fx-text-fill: #b91c1c;"
                        );
                    }
                }
        );

        // COMPONENTE NO VISUAL
        Label lblTexto =
                new Label("Componente no visual - Generador de slug:");

        ValidatedTextField entrada =
                new ValidatedTextField();

        entrada.setPromptText("Texto para convertir");

        entrada.setValidator(
                texto -> !texto.isBlank()
        );

        Label lblSlug =
                new Label("Slug generado: —");

        entrada.textProperty().addListener(
                (observable, anterior, nuevo) -> {

                    String resultado =
                            Slugifier.slug(nuevo);

                    if (resultado.isBlank()) {
                        lblSlug.setText(
                                "Slug generado: —"
                        );
                    } else {
                        lblSlug.setText(
                                "Slug generado: " + resultado
                        );
                    }
                }
        );

        VBox root = new VBox(
                12,
                titulo,
                lblEmail,
                email,
                lblEstado,
                lblTexto,
                entrada,
                lblSlug
        );

        root.setPadding(new Insets(25));

        Scene scene = new Scene(
                root,
                560,
                330
        );

        scene.getStylesheets().add(
                getClass()
                        .getResource("/style.css")
                        .toExternalForm()
        );

        stage.setTitle("Actividad 204 - JavaFX");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}