package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Ejercicio 14 - Número de Teléfono");

        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        //Label, TextField y Button
        Label     lblTelefono = new Label("Teléfono (10 dígitos):");
        TextField txtTelefono = new TextField();
        txtTelefono.setPromptText("Ej: 6123456789");

        Label lblContador = new Label("0 / 10 dígitos");
        Button btnValidar  = new Button("Validar número");
        Label  lblMensaje  = new Label();

        grid.add(lblTelefono, 0, 0);
        grid.add(txtTelefono, 1, 0);
        grid.add(lblContador, 1, 1);
        grid.add(btnValidar,  1, 2);
        grid.add(lblMensaje,  1, 3);

        //filtra letras y actualiza contador
        txtTelefono.textProperty().addListener((observable, oldValue, newValue) -> {
            // Solo permitir dígitos
            String soloDigitos = newValue.replaceAll("\\D", "");
            // Limitar a 10 caracteres
            if (soloDigitos.length() > 10) {
                soloDigitos = soloDigitos.substring(0, 10);
            }
            // Actualizar el campo solo si cambio
            if (!newValue.equals(soloDigitos)) {
                txtTelefono.setText(soloDigitos);
            }
            int len = soloDigitos.length();
            lblContador.setText(len + " / 10 dígitos");
            lblContador.setTextFill(len == 10 ? Color.GREEN : Color.GRAY);
            lblMensaje.setText(""); // Limpiar mensaje al escribir
        });

        // Manejo de evento con ActionEvent
        btnValidar.setOnAction(event -> {
            String telefono = txtTelefono.getText().trim();
            if (telefono.isEmpty()) {
                lblMensaje.setTextFill(Color.RED);
                lblMensaje.setText("⚠ El campo no puede estar vacío.");
            } else if (telefono.length() != 10) {
                lblMensaje.setTextFill(Color.RED);
                lblMensaje.setText("⚠ Debe tener 10 dígitos. Tienes " + telefono.length() + ".");
            } else {
                lblMensaje.setTextFill(Color.GREEN);
                lblMensaje.setText("✓ Número válido: (" + telefono.substring(0, 3) + ") "
                        + telefono.substring(3, 6) + "-" + telefono.substring(6));
            }
        });

        Scene scene = new Scene(grid, 400, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}