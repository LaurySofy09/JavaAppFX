import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class VentanaPrincipal {
    public void mostrar() {
        Stage stage = new Stage();
        Button btnAbrir = new Button("Abrir segunda ventana");

        btnAbrir.setOnAction(e -> new SegundaVentana().mostrar());

        StackPane root = new StackPane(btnAbrir);
        Scene scene = new Scene(root, 300, 200);

        // Cargar el archivo CSS
        File archivoCSS = new File("resources/estilos.css");
        scene.getStylesheets().add("file:///" + archivoCSS.getAbsolutePath().replace("\\", "/"));

        stage.setTitle("Ventana Principal");
        stage.setScene(scene);
        stage.show();
    }
}
