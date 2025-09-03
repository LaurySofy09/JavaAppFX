import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SegundaVentana {
    public void mostrar() {
        Stage stage = new Stage();

        // ===== Título =====
        Label titulo = new Label("Info EPD");
        titulo.setStyle("-fx-font-size: 18px; -fx-underline: true;");
        VBox contenedorPrincipal = new VBox(15);
        contenedorPrincipal.setPadding(new Insets(20));
        contenedorPrincipal.setAlignment(Pos.TOP_CENTER);

        // ===== Sección "Consulta" =====
        Label lblCedula = new Label("Cédula:");
        TextField campo1 = new TextField();
        TextField campo2 = new TextField();
        TextField campo3 = new TextField();
        Button btnConsultar = new Button("Consultar");

// ... aquí define primero los TextField que usas dentro del evento:
        TextField nombres = new TextField();
        TextField apellidos = new TextField();
        TextField banco = new TextField();
        TextField cuenta = new TextField();
        TextField tipo = new TextField();
        TextField estatus1 = new TextField();
        TextField estatus2 = new TextField();
        TextField fechaInicio = new TextField();

// Luego pon el evento aquí:
        btnConsultar.setOnAction(e -> {
            String ced1 = campo1.getText().trim();
            String ced2 = campo2.getText().trim();
            String ced3 = campo3.getText().trim();

            ResultadoConsulta resultado = ConsultaCedulaOracle.consultarCedula(ced1, ced2, ced3);

            if (resultado.isEncontrado()) {
                nombres.setText(resultado.getNombres());
                apellidos.setText(resultado.getApellidos());
                banco.setText(resultado.getBanco());
                cuenta.setText(resultado.getCuenta());
                tipo.setText(resultado.getDescripcionTipo());
                fechaInicio.setText(resultado.getFechaInicio().toString());
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No se encontró la cédula o error: " + resultado.getError());
                alert.showAndWait();
            }
        });

        HBox filaCedula = new HBox(10, lblCedula, campo1, campo2, campo3, btnConsultar);
        filaCedula.setAlignment(Pos.CENTER_LEFT);

        VBox seccionConsulta = new VBox(10, filaCedula);
        seccionConsulta.setPadding(new Insets(10));
        seccionConsulta.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5;");

        // ===== Sección "Información" =====
        GridPane formulario = new GridPane();
        formulario.setVgap(10);
        formulario.setHgap(10);
        formulario.setPadding(new Insets(10));

        formulario.add(new Label("Nombres:"), 0, 0);
        formulario.add(nombres, 1, 0);
        formulario.add(new Label("Banco:"), 3, 0);
        formulario.add(banco, 4, 0);

        formulario.add(new Label("Apellidos:"), 0, 1);
        formulario.add(apellidos, 1, 1);
        formulario.add(new Label("Cuenta:"), 3, 1);
        formulario.add(cuenta, 4, 1);

        formulario.add(estatus1, 1, 2);
        formulario.add(estatus2, 2, 2);
        formulario.add(new Label("Tipo:"), 3, 2);
        formulario.add(tipo, 4, 2);

        formulario.add(new Label("Estatus:"), 0, 2);
        formulario.add(new Label("Fecha inicio:"), 0, 3);
        formulario.add(fechaInicio, 1, 3);

        // Botón Limpiar
        Button btnLimpiar = new Button("Limpiar");

        // Evento del botón Limpiar
        btnLimpiar.setOnAction(e -> {
            campo1.clear();
            campo2.clear();
            campo3.clear();
            nombres.clear();
            apellidos.clear();
            banco.clear();
            cuenta.clear();
            tipo.clear();
            estatus1.clear();
            estatus2.clear();
            fechaInicio.clear();

            campo1.requestFocus(); // opcional
        });

        HBox contenedorBoton = new HBox(btnLimpiar);
        contenedorBoton.setAlignment(Pos.BOTTOM_RIGHT);
        contenedorBoton.setPadding(new Insets(10, 0, 0, 0));

        VBox seccionFormulario = new VBox(10, formulario, contenedorBoton);
        seccionFormulario.setPadding(new Insets(10));
        seccionFormulario.setStyle("-fx-border-color: lightgray; -fx-border-radius: 5;");

        // Configurar columnas para mejor distribución
        ColumnConstraints col0 = new ColumnConstraints();
        col0.setPercentWidth(15); // Etiquetas

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25); // Campos de texto grandes

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(15); // Segunda etiqueta

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25); // Segundo campo de texto

        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(20); // Para campos opcionales como tipo

        formulario.getColumnConstraints().addAll(col0, col1, col2, col3, col4);

        // ===== Footer =====
        Label footerIzq = new Label("Label10");
        Label footerDer = new Label("Usuario: Label11");
        HBox footer = new HBox(footerIzq, new Region(), footerDer);
        HBox.setHgrow(footer.getChildren().get(1), Priority.ALWAYS);
        footer.setPadding(new Insets(10));

        // Agregar todo al contenedor principal
        contenedorPrincipal.getChildren().addAll(titulo, seccionConsulta, seccionFormulario, footer);

        // Crear escena
        Scene scene = new Scene(contenedorPrincipal, 800, 400);
        stage.setScene(scene);
        stage.setTitle("Formulario JavaFX");
        stage.show();
    }

}
